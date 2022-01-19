package com.molecode.astronomyutils.coordinates;

import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.DEGREE_ANGLE;

public class HorizontalCoordinate {

    private final Angle altitude;

    private final Angle azimuth;

    public HorizontalCoordinate(double altitudeValue, double azimuthValue) {
        validateAltitude(altitudeValue);
        validateAzimuth(azimuthValue);
        this.altitude = new Angle(DEGREE_ANGLE, altitudeValue);
        this.azimuth = new Angle(DEGREE_ANGLE, azimuthValue);
    }

    public HorizontalCoordinate(int altDegree, int altMinute, double altSecond, int azDegree, int azMinute, double azSecond) {
        this.altitude = new Angle(DEGREE_ANGLE, altDegree, altMinute, altSecond);
        validateAltitude(altitude.getDegreeValue());
        this.azimuth = new Angle(DEGREE_ANGLE, azDegree, azMinute, azSecond);
        validateAzimuth(azimuth.getDegreeValue());
    }

    private void validateAzimuth(double azimuthValue) {
        if (azimuthValue <0 || azimuthValue > 360) {
            throw new IllegalArgumentException("Value of azimuth should be between 0 to -360º.");
        }
    }

    private void validateAltitude(double altitudeValue) {
        if (altitudeValue > 90 || altitudeValue < -90) {
            throw new IllegalArgumentException("Value of altitude should be between 90 to -90º.");
        }
    }

    public int getAltDegree() {
        return altitude.getDegree();
    }

    public int getAltMinute() {
        return altitude.getArcMinute();
    }

    public double getAltSecond() {
        return altitude.getArcSecond();
    }

    public double getAltDegreeValue() {
        return altitude.getDegreeValue();
    }

    public int getAzDegree() {
        return azimuth.getDegree();
    }

    public int getAzMinute() {
        return azimuth.getArcMinute();
    }

    public double getAzSecond() {
        return azimuth.getArcSecond();
    }

    public double getAzDegreeValue() {
        return azimuth.getDegreeValue();
    }

    @Override
    public String toString() {
        return String.format(
                "HorizontalCoordinate{altitude: %dº%d'%.4f\"(%.4f), azimuth: %dº%d'%.4f\"(%.4f)}",
                getAltDegree(), getAltMinute(), getAltSecond(), getAltDegreeValue(),
                getAzDegree(), getAzMinute(), getAzSecond(), getAzDegreeValue()
        );
    }
}
