package com.molecode.astronomyutils.coordinates;

import org.junit.jupiter.api.Test;

import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.DEGREE_ANGLE;
import static com.molecode.astronomyutils.coordinates.Angle.Descriptor.HOUR_ANGLE;
import static org.junit.jupiter.api.Assertions.*;

class AngleTest {

	private static final double DELTA = 0.00000000001;

	@Test
	void testCreatedWithValue() {
		Angle hourAngle = new Angle(HOUR_ANGLE, 2.9584444444444444);
		assertEquals(44.376666666666665, hourAngle.getDegreeValue());
		assertEquals(44, hourAngle.getDegree());
		assertEquals(22, hourAngle.getArcMinute());
		assertEquals(36.0, hourAngle.getArcSecond(), DELTA);

		Angle degreeAngle = new Angle(DEGREE_ANGLE, 44.376666666666665);
		assertEquals(2.9584444444444444, degreeAngle.getHourValue());
		assertEquals(2, degreeAngle.getHour());
		assertEquals(57, degreeAngle.getMinute());
		assertEquals(30.4, degreeAngle.getSecond(), DELTA);
	}

	@Test
	void testCreatedWithSubdivisions() {
		Angle degreeAngle = new Angle(DEGREE_ANGLE, 44, 22, 36);
		assertEquals(2.9584444444444444, degreeAngle.getHourValue(), DELTA);
		assertEquals(2, degreeAngle.getHour());
		assertEquals(57, degreeAngle.getMinute());
		assertEquals(30.4, degreeAngle.getSecond(), DELTA);

		Angle hourAngle = new Angle(HOUR_ANGLE, 2, 57, 30.4);
		assertEquals(44.376666666666665, hourAngle.getDegreeValue(), DELTA);
		assertEquals(44, hourAngle.getDegree());
		assertEquals(22, hourAngle.getArcMinute());
		assertEquals(36, hourAngle.getArcSecond(), DELTA);
	}

	@Test
	void testCreatedWithNegativeValue() {
		Angle hourAngle = new Angle(HOUR_ANGLE, -2.9584444444444444);
		assertEquals(-44.376666666666665, hourAngle.getDegreeValue());
		assertEquals(-44, hourAngle.getDegree());
		assertEquals(-22, hourAngle.getArcMinute());
		assertEquals(-36.0, hourAngle.getArcSecond(), DELTA);

		Angle degreeAngle = new Angle(DEGREE_ANGLE, -44.376666666666665);
		assertEquals(-2.9584444444444444, degreeAngle.getHourValue());
		assertEquals(-2, degreeAngle.getHour());
		assertEquals(-57, degreeAngle.getMinute());
		assertEquals(-30.4, degreeAngle.getSecond(), DELTA);
	}

	@Test
	void testCreatedWithNegativeSubdivisions() {
		Angle degreeAngle = new Angle(DEGREE_ANGLE, -44, -22, -36);
		assertEquals(-2.9584444444444444, degreeAngle.getHourValue(), DELTA);
		assertEquals(-2, degreeAngle.getHour());
		assertEquals(-57, degreeAngle.getMinute());
		assertEquals(-30.4, degreeAngle.getSecond(), DELTA);

		Angle hourAngle = new Angle(HOUR_ANGLE, -2, -57, -30.4);
		assertEquals(-44.376666666666665, hourAngle.getDegreeValue(), DELTA);
		assertEquals(-44, hourAngle.getDegree());
		assertEquals(-22, hourAngle.getArcMinute());
		assertEquals(-36, hourAngle.getArcSecond(), DELTA);
	}

	@Test
	void testInconsistentSubdivisionSign() {
		assertThrows(IllegalArgumentException.class, () -> new Angle(DEGREE_ANGLE, 44, -22, 36));
		assertThrows(IllegalArgumentException.class, () -> new Angle(HOUR_ANGLE, 2, -57, -30.4));
	}

}