package com.molecode.astronomyutils;

import com.molecode.astronomyutils.coordinates.EquatorialCoordinate;
import com.molecode.astronomyutils.coordinates.GeographicCoordinate;
import com.molecode.astronomyutils.coordinates.HorizontalCoordinate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class StarLocator {

	private final ZoneId zoneId;

	private final GeographicCoordinate observingLocation;

	public StarLocator(ZoneId zoneId, GeographicCoordinate observingLocation) {
		this.zoneId = zoneId;
		this.observingLocation = observingLocation;
	}

	public HorizontalCoordinate locateStar(LocalDateTime localDateTime, EquatorialCoordinate starEqCoordinate) {
		double localSiderealTime = calculateLocalSiderealTime(ZonedDateTime.of(localDateTime, zoneId), observingLocation.getLongDegreeValue());
		double localHourAngle = localSiderealTime - starEqCoordinate.getRaDegreeValue();

		double observingLatitudeValue = observingLocation.getLatDegreeValue();
		double declinationRadians = starEqCoordinate.getDeclination().toRadians();

		double altitudeValue = toDegrees(
				asin(
						sin(declinationRadians) * sin(toRadians(observingLatitudeValue)) + cos(declinationRadians) * cos(toRadians(observingLatitudeValue)) * cos(toRadians(localHourAngle))
				)
		);
		double azimuthValue = toDegrees(
				- atan2(
						cos(declinationRadians) * sin(toRadians(localHourAngle)),
						- sin(toRadians(observingLatitudeValue)) * cos(declinationRadians) * cos(toRadians(localHourAngle)) + cos(toRadians(observingLatitudeValue)) * sin(declinationRadians)
				)
		);
		if (azimuthValue < 0) {
			azimuthValue += 360;
		}

		return new HorizontalCoordinate(altitudeValue, azimuthValue);
	}

	public LocalDateTime calculateMeridianTime(LocalDate localDate, EquatorialCoordinate starEqCoordinate) {
		LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(23, 0, 0));
		double julianDate = calculateJulianDate(ZonedDateTime.of(localDateTime, zoneId));
		double t = calculateT(julianDate);
		double referenceLocalSiderealTime = reduceAngle(calculateLocalSiderealTime(julianDate, t, observingLocation.getLongDegreeValue()));

		double intervalInSiderealTime = (starEqCoordinate.getRaDegreeValue() - referenceLocalSiderealTime) / 15 * 3600 * 1000_000_000;
		double intervalInSolarTime = intervalInSiderealTime / (1.002_737_909_350_795 + 5.900_6 * Math.pow(10, -11) * t - 5.9 * Math.pow(10, -15) * t * t);
		return localDateTime.plusNanos((long) intervalInSolarTime);
	}

	private static double calculateLocalSiderealTime(ZonedDateTime zonedLocalTime, double longitude) {
		double julianDate = calculateJulianDate(zonedLocalTime);
		return calculateLocalSiderealTime(julianDate, calculateT(julianDate), longitude);
	}

	private static double calculateLocalSiderealTime(double julianDate, double t, double longitude) {
		double theta0 = 280.46061837 + 360.98564736629 * (julianDate - 2451545.0) + 0.000387933 * t * t - t * t * t / 38710000.0;
		return theta0 + longitude;
	}

	private static double calculateJulianDate(ZonedDateTime zonedDateTime) {
		ZonedDateTime utc = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
		int year = utc.getYear();
		int month = utc.getMonth().getValue();
		int day = utc.getDayOfMonth();
		double hour = utc.getHour();
		double minute = utc.getMinute();
		double second = utc.getSecond();

		double julianDayNumber = 1461 * (year + 4800 + (month - 14)/12)/4 +
				367 * (month - 2 - 12 * ((month - 14)/12))/12 -
				3 * ((year + 4900 + (month - 14)/12)/100)/4 +
				day - 32075;

		return julianDayNumber + (hour - 12) / 24 + minute / 1440 + second / 86400;
	}

	private static double calculateT(double julianDate) {
		return (julianDate - 2451545.0) / 36525;
	}

	private static double reduceAngle(double originalValue) {
		double reducedValue = originalValue % 360;
		if (reducedValue < 0) {
			reducedValue += 360;
		}
		return reducedValue;
	}

}
