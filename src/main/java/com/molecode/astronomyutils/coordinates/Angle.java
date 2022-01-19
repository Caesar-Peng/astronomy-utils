package com.molecode.astronomyutils.coordinates;

import org.apache.commons.lang3.tuple.Triple;

public class Angle {

	public enum Descriptor {
		HOUR_ANGLE,
		DEGREE_ANGLE
	}

	private final double degreeValue;
	private final int degree;
	private final int arcMinute;
	private final double arcSecond;

	private final double hourValue;
	private final int hour;
	private final int minute;
	private final double second;

	public Angle(Descriptor descriptor, double value) {
		switch (descriptor) {
			case DEGREE_ANGLE:
				degreeValue = value;
				hourValue = value / 15;
				break;
			case HOUR_ANGLE:
				hourValue = value;
				degreeValue = hourValue * 15;
				break;
			default:
				throw new IllegalArgumentException("Unsupported descriptor: " + descriptor);
		}
		Triple<Integer, Integer, Double> degreeSubdivisions = valueToSubdivisions(degreeValue);
		degree = degreeSubdivisions.getLeft();
		arcMinute = degreeSubdivisions.getMiddle();
		arcSecond = degreeSubdivisions.getRight();

		Triple<Integer, Integer, Double> hourSubdivisions = valueToSubdivisions(hourValue);
		hour = hourSubdivisions.getLeft();
		minute = hourSubdivisions.getMiddle();
		second = hourSubdivisions.getRight();
	}

	public Angle(Descriptor descriptor, int firstSubdivision, int secondSubdivision, double thirdSubdivision) {
		validateSubdivisions(firstSubdivision, secondSubdivision, thirdSubdivision);
		double value = subdivisionsToValue(firstSubdivision, secondSubdivision, thirdSubdivision);
		switch (descriptor) {
			case DEGREE_ANGLE:
				degreeValue = value;

				degree = firstSubdivision;
				arcMinute = secondSubdivision;
				arcSecond = thirdSubdivision;

				hourValue = degreeValue / 15;

				Triple<Integer, Integer, Double> hourSubdivisions = valueToSubdivisions(hourValue);
				hour = hourSubdivisions.getLeft();
				minute = hourSubdivisions.getMiddle();
				second = hourSubdivisions.getRight();
				break;

			case HOUR_ANGLE:
				hourValue = value;
				hour = firstSubdivision;
				minute = secondSubdivision;
				second = thirdSubdivision;

				degreeValue = hourValue * 15;

				Triple<Integer, Integer, Double> degreeSubdivisions = valueToSubdivisions(degreeValue);
				degree = degreeSubdivisions.getLeft();
				arcMinute = degreeSubdivisions.getMiddle();
				arcSecond = degreeSubdivisions.getRight();
				break;

			default:
				throw new IllegalArgumentException("Unsupported descriptor: " + descriptor);

		}
	}

	private static void validateSubdivisions(int firstSubdivision, int secondSubdivision, double thirdSubdivision) {
		if (!((firstSubdivision >= 0 && secondSubdivision >= 0 && thirdSubdivision >= 0)
				|| (firstSubdivision <= 0 && secondSubdivision <= 0 && thirdSubdivision <= 0))) {
			throw new IllegalArgumentException(String.format("Sign of subdivisions are not consistent: %d, %d, %f.", firstSubdivision, secondSubdivision, thirdSubdivision));
		}
	}

	private static Triple<Integer, Integer, Double> valueToSubdivisions(double value) {
		int firstSubdivision = (int) value;
		int secondSubdivision = (int) ((value - firstSubdivision) * 60);
		double thirdSubdivision = ((value - firstSubdivision) * 60 - secondSubdivision) * 60;
		return Triple.of(firstSubdivision, secondSubdivision, thirdSubdivision);
	}

	private static double subdivisionsToValue(int firstSubdivision, int secondSubdivision, double thirdSubdivision) {
		return firstSubdivision + secondSubdivision / 60.0 + thirdSubdivision / 3600.0;
	}

	public double getDegreeValue() {
		return degreeValue;
	}

	public int getDegree() {
		return degree;
	}

	public int getArcMinute() {
		return arcMinute;
	}

	public double getArcSecond() {
		return arcSecond;
	}

	public double getHourValue() {
		return hourValue;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public double getSecond() {
		return second;
	}

	public double toRadians() {
		return Math.toRadians(degreeValue);
	}

	@Override
	public String toString() {
		return String.format(
				"Angle{degree: %dº%d'%.4f\"(%.4f), hour: %dh%dm%.4f(%.4f)}",
				degree, arcMinute, arcSecond, degreeValue,
				hour, minute, second, hourValue
		);
	}
}
