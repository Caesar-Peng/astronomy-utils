package com.molecode.astronomyutils.coordinates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquatorialCoordinateTest {

	private static final double DELTA = 0.00000000001;

	@Test
	void testConstructEqCoordinate() {
		EquatorialCoordinate eqCoordinate = new EquatorialCoordinate(5, 56, 19, 7, 24, 34);
		assertEquals(5, eqCoordinate.getRaHour());
		assertEquals(56, eqCoordinate.getRaMinute());
		assertEquals(19, eqCoordinate.getRaSecond(), DELTA);
		assertEquals(89.07916666666667, eqCoordinate.getRaDegreeValue(), DELTA);
		assertEquals(5.938611111111111, eqCoordinate.getRaHourValue(), DELTA);

		assertEquals(7, eqCoordinate.getDecDegree());
		assertEquals(24, eqCoordinate.getDecMinute());
		assertEquals(34, eqCoordinate.getDecSecond(), DELTA);
		assertEquals(7.4094444444444445, eqCoordinate.getDecDegreeValue());
	}

	@Test
	void testInvalidRightAscension() {
		assertThrows(IllegalArgumentException.class, () -> new EquatorialCoordinate(24, 0, 0.001, 10, 10, 10.001));
		assertThrows(IllegalArgumentException.class, () -> new EquatorialCoordinate(0, 0, -0.001, 10, 10, 10.001));
	}

	@Test
	void testInvalidDeclination() {
		assertThrows(IllegalArgumentException.class, () -> new EquatorialCoordinate(1, 1, 1, 90, 0, 0.001));
		assertThrows(IllegalArgumentException.class, () -> new EquatorialCoordinate(1, 1, 1, -90, 0, -0.001));
	}
}