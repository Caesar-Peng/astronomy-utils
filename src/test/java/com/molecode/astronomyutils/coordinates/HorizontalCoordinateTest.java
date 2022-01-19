package com.molecode.astronomyutils.coordinates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalCoordinateTest {

	private final static double DELTA = 0.00000001;

	@Test
	void testConstructHorizontalCoordinate() {
		HorizontalCoordinate horizontalCoordinate1WithValues = new HorizontalCoordinate(61.28275440110314, 168.86507390186668);
		assertEquals(61, horizontalCoordinate1WithValues.getAltDegree());
		assertEquals(16, horizontalCoordinate1WithValues.getAltMinute());
		assertEquals(57.915843971306344, horizontalCoordinate1WithValues.getAltSecond(), DELTA);
		assertEquals(61.28275440110314, horizontalCoordinate1WithValues.getAltDegreeValue(), DELTA);
		assertEquals(168, horizontalCoordinate1WithValues.getAzDegree());
		assertEquals(51, horizontalCoordinate1WithValues.getAzMinute());
		assertEquals(54.26604672004714, horizontalCoordinate1WithValues.getAzSecond(), DELTA);
		assertEquals(168.86507390186668, horizontalCoordinate1WithValues.getAzDegreeValue(), DELTA);

		HorizontalCoordinate horizontalCoordinate1WithSubdivisions = new HorizontalCoordinate(61, 16, 57.915843971306344, 168, 51, 54.26604672004714);
		assertEquals(61, horizontalCoordinate1WithSubdivisions.getAltDegree());
		assertEquals(16, horizontalCoordinate1WithSubdivisions.getAltMinute());
		assertEquals(57.915843971306344, horizontalCoordinate1WithSubdivisions.getAltSecond(), DELTA);
		assertEquals(61.28275440110314, horizontalCoordinate1WithSubdivisions.getAltDegreeValue(), DELTA);
		assertEquals(168, horizontalCoordinate1WithSubdivisions.getAzDegree());
		assertEquals(51, horizontalCoordinate1WithSubdivisions.getAzMinute());
		assertEquals(54.26604672004714, horizontalCoordinate1WithSubdivisions.getAzSecond(), DELTA);
		assertEquals(168.86507390186668, horizontalCoordinate1WithSubdivisions.getAzDegreeValue(), DELTA);

		HorizontalCoordinate horizontalCoordinate2WithValues = new HorizontalCoordinate(-73.48677006604778, 60.496121016882924);
		assertEquals(-73, horizontalCoordinate2WithValues.getAltDegree());
		assertEquals(-29, horizontalCoordinate2WithValues.getAltMinute());
		assertEquals(-12.372237772016206, horizontalCoordinate2WithValues.getAltSecond(), DELTA);
		assertEquals(-73.48677006604778, horizontalCoordinate2WithValues.getAltDegreeValue(), DELTA);
		assertEquals(60, horizontalCoordinate2WithValues.getAzDegree());
		assertEquals(29, horizontalCoordinate2WithValues.getAzMinute());
		assertEquals(46.035660778526335, horizontalCoordinate2WithValues.getAzSecond(), DELTA);
		assertEquals(60.496121016882924, horizontalCoordinate2WithValues.getAzDegreeValue(), DELTA);

		HorizontalCoordinate horizontalCoordinate2WithSubdivisions = new HorizontalCoordinate(-73, -29, -12.372237772016206, 60, 29, 46.035660778526335);
		assertEquals(-73, horizontalCoordinate2WithSubdivisions.getAltDegree());
		assertEquals(-29, horizontalCoordinate2WithSubdivisions.getAltMinute());
		assertEquals(-12.372237772016206, horizontalCoordinate2WithSubdivisions.getAltSecond(), DELTA);
		assertEquals(-73.48677006604778, horizontalCoordinate2WithSubdivisions.getAltDegreeValue(), DELTA);
		assertEquals(60, horizontalCoordinate2WithSubdivisions.getAzDegree());
		assertEquals(29, horizontalCoordinate2WithSubdivisions.getAzMinute());
		assertEquals(46.035660778526335, horizontalCoordinate2WithSubdivisions.getAzSecond(), DELTA);
		assertEquals(60.496121016882924, horizontalCoordinate2WithSubdivisions.getAzDegreeValue(), DELTA);
	}

	@Test
	void testInvalidAltitude() {
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(90.0001, 12));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(-90.0001, 12));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(90, 0, 0.001, 1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(-90, 0, -0.001, 1, 1, 1));
		assertDoesNotThrow(() -> new HorizontalCoordinate(0, 12));
		assertDoesNotThrow(() -> new HorizontalCoordinate(90, 12));
		assertDoesNotThrow(() -> new HorizontalCoordinate(-90, 12));
		assertDoesNotThrow(() -> new HorizontalCoordinate(0, 0, 0, 1, 1, 1));
		assertDoesNotThrow(() -> new HorizontalCoordinate(90, 0, 0, 1, 1, 1));
		assertDoesNotThrow(() -> new HorizontalCoordinate(-90, 0, 0, 1, 1, 1));
	}

	@Test
	void testInvalidAzimuth() {
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(12, 360.0001));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(12, -0.0001));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(1, 1, 1, 360, 0, 0.0001));
		assertThrows(IllegalArgumentException.class, () -> new HorizontalCoordinate(1, 1, 1, 0, 0, -0.0001));
		assertDoesNotThrow(() -> new HorizontalCoordinate(12, 360));
		assertDoesNotThrow(() -> new HorizontalCoordinate(12, 0));
		assertDoesNotThrow(() -> new HorizontalCoordinate(1, 1, 1, 360, 0, 0));
		assertDoesNotThrow(() -> new HorizontalCoordinate(1, 1, 1, 0, 0, 0));
	}
}