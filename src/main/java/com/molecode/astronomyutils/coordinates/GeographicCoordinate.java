package com.molecode.astronomyutils.coordinates;

import java.util.Optional;

import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.DEGREE_ANGLE;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.NORTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.SOUTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.EAST;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.WEST;
import static java.lang.Math.abs;

public class GeographicCoordinate {

    public enum LatitudeDirection {

        NORTH("N"),
        SOUTH("S");

        private final String symbol;

        LatitudeDirection(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public enum LongitudeDirection {
        EAST("E"),
        WEST("W");


        private final String symbol;

        LongitudeDirection(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private final Angle latitude;

    private final Angle longitude;

    public GeographicCoordinate(LatitudeDirection latDirection, int latDegree, int latMinute, double latSecond,
            LongitudeDirection longDirection, int longDegree, int longMinute, double longSecond) {
        if (latDegree < 0 || latMinute < 0 || latSecond < 0 || longDegree < 0 || longMinute < 0 || longSecond < 0) {
            throw new IllegalArgumentException("Latitude and Longitude values should not be negative.");
        }
        int latSign = latDirection == NORTH ? 1 : -1;
        this.latitude = new Angle(DEGREE_ANGLE, latDegree * latSign, latMinute * latSign, latSecond * latSign);
        if (abs(latitude.getDegreeValue()) > 90) {
            throw new IllegalArgumentException("Latitude should be between 0º to 90º.");
        }
        int longSign = longDirection == EAST ? 1 : -1;
        this.longitude = new Angle(DEGREE_ANGLE, longDegree * longSign, longMinute * longSign, longSecond * longSign);
        if (abs(longitude.getDegreeValue()) > 180) {
            throw new IllegalArgumentException("Value of longitude should be between 0º to 180º.");
        }
    }

    public Optional<LatitudeDirection> getLatDirection() {
        if (latitude.getDegreeValue() > 0) {
            return Optional.of(NORTH);
        } else if (latitude.getDegreeValue() < 0) {
            return Optional.of(SOUTH);
        } else {
            return Optional.empty();
        }
    }

    public int getLatDegree() {
        return abs(latitude.getDegree());
    }

    public int getLatMinute() {
        return abs(latitude.getArcMinute());
    }

    public double getLatSecond() {
        return abs(latitude.getArcSecond());
    }

    public double getLatDegreeValue() {
        return latitude.getDegreeValue();
    }

    public Optional<LongitudeDirection> getLongDirection() {
        if (longitude.getDegreeValue() > 0) {
            return Optional.of(EAST);
        } else if (longitude.getDegreeValue() < 0) {
            return Optional.of(WEST);
        } else {
            return Optional.empty();
        }
    }

    public int getLongDegree() {
        return abs(longitude.getDegree());
    }

    public int getLongMinute() {
        return abs(longitude.getArcMinute());
    }

    public double getLongSecond() {
        return abs(longitude.getArcSecond());
    }

    public double getLongDegreeValue() {
        return longitude.getDegreeValue();
    }

    @Override
    public String toString() {
        return String.format(
                "GeographicCoordinate{lat: %dº%d'%.4f\"(%.4f) %s, long: %dº%d'%.4f\"(%.4f) %s}",
                getLatDegree(), getLatMinute(), getLatSecond(), getLatDegreeValue(), getLatDirection().map(LatitudeDirection::getSymbol).orElse("-"),
                getLongDegree(), getLongMinute(), getLongSecond(), getLongDegreeValue(), getLongDirection().map(LongitudeDirection::getSymbol).orElse("-")
        );
    }
}
