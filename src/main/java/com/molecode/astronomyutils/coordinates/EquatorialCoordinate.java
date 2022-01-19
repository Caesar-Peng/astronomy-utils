package com.molecode.astronomyutils.coordinates;

import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.DEGREE_ANGLE;
import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.HOUR_ANGLE;

public class EquatorialCoordinate {

    private final Angle rightAscension;
    private final Angle declination;

    public EquatorialCoordinate(int raHour, int raMinute, double raSecond, int decDegree, int decMinute, double decSecond) {
        this.rightAscension = new Angle(HOUR_ANGLE, raHour, raMinute, raSecond);
        if (rightAscension.getHourValue() < 0 || rightAscension.getHourValue() > 24) {
            throw new IllegalArgumentException("Value of right ascension should be between 0 and 24 hour.");
        }
        this.declination = new Angle(DEGREE_ANGLE, decDegree, decMinute, decSecond);
        if (declination.getDegreeValue() < -90 || declination.getDegreeValue() > 90) {
            throw new IllegalArgumentException("Value of declination should be between -90 and 90 degree.");
        }

    }

    public double getRaDegreeValue() {
        return rightAscension.getDegreeValue();
    }

    public double getRaHourValue() {
        return rightAscension.getHourValue();
    }

    public Angle getDeclination() {
        return declination;
    }

    public int getRaHour() {
        return rightAscension.getHour();
    }

    public int getRaMinute() {
        return rightAscension.getMinute();
    }

    public double getRaSecond() {
        return rightAscension.getSecond();
    }

    public int getDecDegree() {
        return declination.getDegree();
    }

    public int getDecMinute() {
        return declination.getArcMinute();
    }

    public double getDecSecond() {
        return declination.getArcSecond();
    }

    public double getDecDegreeValue() {
        return declination.getDegreeValue();
    }

    @Override
    public String toString() {
        return String.format(
                "EquatorialCoordinate{ra: %dh%dm%.4f(%.4f), dec: %dº%d'%.4f\"(%.4f)}",
                getRaHour(), getRaMinute(), getRaSecond(), getRaHourValue(),
                getDecDegree(), getDecMinute(), getDecSecond(), getDecDegreeValue()

        );
    }
}
