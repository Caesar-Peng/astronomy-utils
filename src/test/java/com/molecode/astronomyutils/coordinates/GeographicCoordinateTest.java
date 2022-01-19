package com.molecode.astronomyutils.coordinates;

import org.junit.jupiter.api.Test;

import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.NORTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.SOUTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.EAST;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.WEST;
import static org.junit.jupiter.api.Assertions.*;

class GeographicCoordinateTest {

	private static final double DELTA = 0.0000000001;

	@Test
	void testConstructGeoCoordinate() {
		GeographicCoordinate tokyoCoordinate = new GeographicCoordinate(NORTH, 35, 42, 9, EAST, 139, 44, 42);
		assertEquals(NORTH, tokyoCoordinate.getLatDirection().orElseThrow());
		assertEquals(35, tokyoCoordinate.getLatDegree());
		assertEquals(42, tokyoCoordinate.getLatMinute());
		assertEquals(9, tokyoCoordinate.getLatSecond(), DELTA);
		assertEquals(35.7025, tokyoCoordinate.getLatDegreeValue(), DELTA);
		assertEquals(EAST, tokyoCoordinate.getLongDirection().orElseThrow());
		assertEquals(139, tokyoCoordinate.getLongDegree());
		assertEquals(44, tokyoCoordinate.getLongMinute());
		assertEquals(42, tokyoCoordinate.getLongSecond(), DELTA);
		assertEquals(139.74499999999998, tokyoCoordinate.getLongDegreeValue(), DELTA);

		GeographicCoordinate saoPauloCoordinate = new GeographicCoordinate(SOUTH, 23, 33, 36, WEST, 46, 39, 15);
		assertEquals(SOUTH, saoPauloCoordinate.getLatDirection().orElseThrow());
		assertEquals(23, saoPauloCoordinate.getLatDegree());
		assertEquals(33, saoPauloCoordinate.getLatMinute());
		assertEquals(36, saoPauloCoordinate.getLatSecond(), DELTA);
		assertEquals(-23.560000000000002, saoPauloCoordinate.getLatDegreeValue());
		assertEquals(WEST, saoPauloCoordinate.getLongDirection().orElseThrow());
		assertEquals(46, saoPauloCoordinate.getLongDegree());
		assertEquals(39, saoPauloCoordinate.getLongMinute());
		assertEquals(15, saoPauloCoordinate.getLongSecond(), DELTA);
		assertEquals(-46.65416666666667, saoPauloCoordinate.getLongDegreeValue(), DELTA);
	}

	@Test
	void testInvalidLatitude() {
		assertThrows(IllegalArgumentException.class, () -> new GeographicCoordinate(NORTH, -1, 0, -1, WEST, 1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new GeographicCoordinate(NORTH, 90, 0, 0.0001, WEST, 1, 1, 1));
		assertDoesNotThrow(() -> new GeographicCoordinate(null, 0, 0, 0, WEST, 1, 1, 1));
	}

	@Test
	void testInvalidLongitude() {
		assertThrows(IllegalArgumentException.class, () -> new GeographicCoordinate(NORTH, 1, 1, 1, WEST, -1, 0, 0));
		assertThrows(IllegalArgumentException.class, () -> new GeographicCoordinate(NORTH, 1, 1, 1, WEST, 180, 0, 0.0001));
		assertDoesNotThrow(() -> new GeographicCoordinate(NORTH, 1, 1, 1, null, 0, 0, 0));
		assertDoesNotThrow(() -> new GeographicCoordinate(NORTH, 1, 1, 1, null, 180, 0, 0));
	}
}