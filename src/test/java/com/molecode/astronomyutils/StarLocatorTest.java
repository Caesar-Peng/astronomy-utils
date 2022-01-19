package com.molecode.astronomyutils;

import com.molecode.astronomyutils.coordinates.EquatorialCoordinate;
import com.molecode.astronomyutils.coordinates.GeographicCoordinate;
import com.molecode.astronomyutils.coordinates.HorizontalCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.NORTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LatitudeDirection.SOUTH;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.EAST;
import static com.molecode.astronomyutils.coordinates.GeographicCoordinate.LongitudeDirection.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StarLocatorTest {

    private StarLocator tokyoStarLocator;
    private StarLocator sydneyStarLocator;
    private StarLocator nycStarLocator;
    private StarLocator saoPauloStarLocator;
    private StarLocator northPoleStarLocator;
    private StarLocator nullIslandStarLocator;

    private static final LocalDateTime LOCAL_DATE_TIME_JAN_15 = LocalDateTime.of(2021, Month.JANUARY, 15, 22, 30, 10);
    private static final LocalDateTime LOCAL_DATE_TIME_JUL_15 = LocalDateTime.of(2021, Month.JULY, 15, 22, 30, 15);

    private static final EquatorialCoordinate BETELGEUSE_EQ_COORDINATE_JAN_15 =
            new EquatorialCoordinate(5, 56, 19, 7, 24, 33);
//    Tokyo: alt: 60º40'21", az: 197º15'46", peaks: 21:56(62º)
//    Sydney: alt: 47º35'52", az: 15º11'19", peaks: 23:11(49º)
//    New York City: alt: 56º22'12", az: 189º25'42", peaks: 22:09(57º)
//    São Paulo: alt: 58º55'59", az: 355º8'54", peaks: 22:20:(59º)
//    North pole: alt: 7º24'33", az: 185º15'11", peaks: 22:09(7º)
//    Null Island: alt: 81º33'30", az: 331º27'2", peaks: 22:13(83º)

    private static final EquatorialCoordinate CAPELLA_EQ_COORDINATE_JAN_15 =
            new EquatorialCoordinate(5, 18, 16, 46, 1, 23);
//    Tokyo: alt: 73º01'01", az: 312º54'25", peaks: 21:17(80º)
//    Sydney: alt: 10º6'47", az: 0º31'34", peaks: 22:32(10º)
//    New York City: alt: 78º02'55", az: 301º16'48", peaks: 21:30(85º)
//    São Paulo: alt: 19º33'50", az: 351º9'29", peaks: 21:41(20º)
//    North pole: alt: 46º1'23", az: 194º45'56", peaks: 21:30(46º)
//    Null Island: alt: 42º27'10", az: 347º14'22", peaks: 21:35(44º)

    private static final EquatorialCoordinate ANTARES_EQ_COORDINATE_JAN_15 =
            new EquatorialCoordinate(16, 30, 40, -26, -28, -37);
//    Tokyo: alt: -62º57'33", az: 78º26'34", peaks: 08:30(28º)
//    Sydney: alt: -28º44'31", az: 168º36'50", peaks: 09:45(83º)
//    New York City: alt: -63º47'08", az: 65º24'26", peaks: 08:43(23º)
//    São Paulo: alt: -34º52'21", az: 153º43'40", peaks: 08:54(87º)
//    North pole: alt: -26º28'37", az: 26º40'34", peaks: 08:43(-26º)
//    Null Island: alt: -53º54'50", az: 139º11'31", peaks: 08:48(64º)

    private static final EquatorialCoordinate ALTAIR_EQ_COORDINATE_JAN_15 =
            new EquatorialCoordinate(19, 51, 45, 8, 55, 17);
//    Tokyo: alt: -41º23'52", az: 332º37'49", peaks: 11:51(63º)
//    Sydney: alt: -46º17'15", az: 244º25'40", peaks: 13:06(47º)
//    New York City: alt: -35º48'16", az: 330º47'58", peaks: 12:04(58º)
//    São Paulo: alt: -60º52'19", az: 244º12'45", peaks: 12:15(58º)
//    North pole: alt: 08º55'17", az: 336º23'41", peaks: 12:04(9º)
//    Null Island: alt: -63º44'17", az: 290º30'47", peaks: 12:09(81º)

    private static final EquatorialCoordinate BETELGEUSE_EQ_COORDINATE_JUL_15 =
            new EquatorialCoordinate(5, 56, 18, 7, 24, 39);
//    Tokyo: alt: -46º24'23", az: 9º53'4", peaks: 10:02(62º)
//    Sydney: alt: -63º23'1", az: 172º53'11", peaks: 10:17(49º)
//    New York City: alt: -40º45'12", az: 345º02'58", peaks: 11:15(57º)
//    São Paulo: alt: -73º49'30", az: 176º36'54", peaks: 10:26(59º)
//    North pole: alt: 7º24'39", az: 3º40'45", peaks: 10:15(7º)
//    Null Island: alt: -82º11'13", az: 18º24'52", peaks: 10:20(83º)

    private static final EquatorialCoordinate CAPELLA_EQ_COORDINATE_JUL_15 =
            new EquatorialCoordinate(5, 18, 15, 46, 1, 15);
//    Tokyo: alt: -6º57'19", az: 11º22'23", peaks: 09:24(80º)
//    Sydney: alt: -74º27'7", az: 34º47'39", peaks: 09:38(10º)
//    New York City: alt: -3º15'10", az: 358º42'52", peaks: 10:37(85º)
//    São Paulo: alt: -66º0'8", az: 18º3'49", peaks: 09:47(20º)
//    North pole: alt: 46º1'15", az: 13º11'47", peaks: 09:36(46º)
//    Null Island: alt: -42º47'1", az: 11º20'40", peaks: 09:41(44º)

    private static final EquatorialCoordinate ANTARES_EQ_COORDINATE_JUL_15 =
            new EquatorialCoordinate(16, 30, 44, -26, -28, -49);
//    Tokyo: alt: 22º20'25", az: 207º15'34", peaks: 20:37(28º)
//    Sydney: alt: 67º33'18", az: 282º35'29", peaks: 20:51(83º)
//    New York City: alt: 22º10'2", az: 189º41'17", peaks: 21:49(23º)
//    São Paulo: alt: 69º34'29", az: 257º7'24", peaks: 21:00(87º)
//    North pole: alt: -26º28'49", az: 205º4'18", peaks: 20:49(-26º)
//    Null Island: alt: 54º56'8", az: 219º5'41", peaks: 20:54(64º)

    private static final EquatorialCoordinate ALTAIR_EQ_COORDINATE_JUL_15 =
            new EquatorialCoordinate(19, 51, 49, 8, 55, 27);
//    Tokyo: alt: 56º31'57", az: 137º47'39", peaks: 23:58(63º)
//    Sydney: alt: 40º45'47", az: 34º24'26", peaks: 00:13(47º)
//    New York City: alt: 42º16'47", az: 120º23'19", peaks: 01:11(58º)
//    São Paulo: alt: 47º34'4", az: 43º17'56", peaks: 00:22(58º)
//    North pole: alt: 8º55'27", az: 154º47'55", peaks: 00:11(9º)
//    Null Island: alt: 62º14'15", az: 70º33'3", peaks: 00:15(81º)

    @BeforeEach
    void setUp() {
//      Tokyo, Japan
        tokyoStarLocator = new StarLocator(
                ZoneId.of("Asia/Tokyo"),
                new GeographicCoordinate(NORTH, 35, 42, 9, EAST, 139, 44, 42));
//      Sydney, Australia
        sydneyStarLocator = new StarLocator(
                ZoneId.of("Australia/Sydney"),
                new GeographicCoordinate(SOUTH, 33, 51, 42, EAST, 151, 8, 10));
//      New York City, US
        nycStarLocator = new StarLocator(
                ZoneId.of("America/New_York"),
                new GeographicCoordinate(NORTH, 40, 42, 41, WEST, 74, 0, 29));
//      São Paulo, Brazil
        saoPauloStarLocator = new StarLocator(
                ZoneId.of("America/Sao_Paulo"),
                new GeographicCoordinate(SOUTH, 23, 33, 36, WEST, 46, 39, 15));
//      North Pole
        northPoleStarLocator = new StarLocator(
                ZoneId.ofOffset("", ZoneOffset.ofHours(-5)),
                new GeographicCoordinate(NORTH, 90, 0, 0, WEST, 74, 0, 24)
        );
//      Null Island
        nullIslandStarLocator = new StarLocator(
                ZoneId.ofOffset("", ZoneOffset.UTC),
                new GeographicCoordinate(NORTH, 0, 0, 0, EAST, 0, 0, 0)
        );

    }

    @Test
    void testLocatingStarInJanuary() {
        verifyStarLocation(new HorizontalCoordinate(60, 40, 21, 197, 15, 46), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(47, 35, 52, 15, 11, 19), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in Sydney");
        verifyStarLocation(new HorizontalCoordinate(56, 22, 12, 189, 25, 42), nycStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in New York City");
        verifyStarLocation(new HorizontalCoordinate(58, 55, 59, 355, 8, 54), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(7, 24, 33, 185, 15, 11), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in North Pole");
        verifyStarLocation(new HorizontalCoordinate(81, 33, 33, 331, 27, 36), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, BETELGEUSE_EQ_COORDINATE_JAN_15), "Betelgeuse in Null Island");

        verifyStarLocation(new HorizontalCoordinate(73, 1, 1, 312, 54, 25), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(10, 6, 47, 0, 31, 34), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in Sydney");
        verifyStarLocation(new HorizontalCoordinate(78, 2, 55, 301, 16, 48), nycStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in New York City");
        verifyStarLocation(new HorizontalCoordinate(19, 33, 50, 351, 9, 29), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(46, 1, 23, 194, 45, 56), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in NorthPole");
        verifyStarLocation(new HorizontalCoordinate(42, 27, 10, 347, 14, 22), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, CAPELLA_EQ_COORDINATE_JAN_15), "Capella in Null Island");

        verifyStarLocation(new HorizontalCoordinate(-62, -57, -33, 78, 26, 34), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(-28, -44, -31, 168, 36, 50), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in Sydney");
        verifyStarLocation(new HorizontalCoordinate(-63, -47, -8, 65, 24, 26), nycStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in New York City");
        verifyStarLocation(new HorizontalCoordinate(-34, -52, -21, 153, 43, 40), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(-26, -28, -49, 26, 40, 0), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in NorthPole");
        verifyStarLocation(new HorizontalCoordinate(-53, -54, -50, 139, 11, 31), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ANTARES_EQ_COORDINATE_JAN_15), "Antares in Null Island");

        verifyStarLocation(new HorizontalCoordinate(-41, -23, -52, 332, 37, 49), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(-46, -17, -15, 244, 25, 40), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in Sydney");
        verifyStarLocation(new HorizontalCoordinate(-35, -48, -16, 330, 47, 58), nycStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in New York City");
        verifyStarLocation(new HorizontalCoordinate(-60, -52, -19, 244, 12, 45), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(8, 55, 27, 336, 23, 41), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in NorthPole");
        verifyStarLocation(new HorizontalCoordinate(-63, -44, -17, 290, 30, 47), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JAN_15, ALTAIR_EQ_COORDINATE_JAN_15), "Altair in Null Island");
    }

    @Test
    void testLocatingStarInJuly() {
        verifyStarLocation(new HorizontalCoordinate(-46, -24, -23, 9, 53, 4), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(-63, -23, -1, 172, 53, 11), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in Sydney");
        verifyStarLocation(new HorizontalCoordinate(-40, -45, -12, 345, 2, 58), nycStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in New York City");
        verifyStarLocation(new HorizontalCoordinate(-73, -49, -30, 176, 36, 54), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(7, 24, 39, 3, 40, 45), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in North Pole");
        verifyStarLocation(new HorizontalCoordinate(-82, -11, -13, 18, 24, 52), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, BETELGEUSE_EQ_COORDINATE_JUL_15), "Betelgeuse in Null Island");

        verifyStarLocation(new HorizontalCoordinate(-6, -57, -19, 11, 22, 23), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(-74, -27, -11, 34, 47, 23), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in Sydney");
        verifyStarLocation(new HorizontalCoordinate(-3, -15, -10, 358, 42, 52), nycStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in New York City");
        verifyStarLocation(new HorizontalCoordinate(-66, 0, -8, 18, 3, 49), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(46, 1, 15, 13, 11, 47), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in North Pole");
        verifyStarLocation(new HorizontalCoordinate(-42, -47, -1, 11, 20, 40), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, CAPELLA_EQ_COORDINATE_JUL_15), "Capella in Null Island");

        verifyStarLocation(new HorizontalCoordinate(22, 20, 25, 207, 15, 34), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(67, 33, 18, 282, 35, 29), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in Sydney");
        verifyStarLocation(new HorizontalCoordinate(22, 10, 2, 189, 41, 17), nycStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in New York City");
        verifyStarLocation(new HorizontalCoordinate(69, 34, 29, 257, 7, 24), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(-26, -28, -49, 205, 4, 18), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in North Pole");
        verifyStarLocation(new HorizontalCoordinate(54, 56, 8, 219, 5, 41), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ANTARES_EQ_COORDINATE_JUL_15), "Antares in Null Island");

        verifyStarLocation(new HorizontalCoordinate(56, 31, 57, 137, 47, 39), tokyoStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in Tokyo");
        verifyStarLocation(new HorizontalCoordinate(40, 45, 47, 34, 24, 26), sydneyStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in Sydney");
        verifyStarLocation(new HorizontalCoordinate(42, 16, 47, 120, 23, 19), nycStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in New York City");
        verifyStarLocation(new HorizontalCoordinate(47, 34, 4, 43, 17, 56), saoPauloStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in São Paulo");
        verifyStarLocation(new HorizontalCoordinate(8, 55, 27, 154, 47, 55), northPoleStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in North Pole");
        verifyStarLocation(new HorizontalCoordinate(62, 14, 15, 70, 33, 3), nullIslandStarLocator.locateStar(LOCAL_DATE_TIME_JUL_15, ALTAIR_EQ_COORDINATE_JUL_15), "Altair in Null Island");
    }

    @Test
    void testCalculateMeridianTime() {
        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JAN_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JAN_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ANTARES_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ANTARES_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ANTARES_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ANTARES_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ANTARES_EQ_COORDINATE_JAN_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JAN_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JAN_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JAN_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), BETELGEUSE_EQ_COORDINATE_JUL_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), CAPELLA_EQ_COORDINATE_JUL_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ANTARES_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ANTARES_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ANTARES_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ANTARES_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ANTARES_EQ_COORDINATE_JUL_15);

        testCalculateMeridianTime(tokyoStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(sydneyStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nycStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(saoPauloStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JUL_15);
        testCalculateMeridianTime(nullIslandStarLocator, LOCAL_DATE_TIME_JUL_15.toLocalDate(), ALTAIR_EQ_COORDINATE_JUL_15);
    }

    private void testCalculateMeridianTime(StarLocator starLocator, LocalDate localDate, EquatorialCoordinate starEqCoordinate) {
        LocalDateTime meridianTime = starLocator.calculateMeridianTime(localDate, starEqCoordinate);

        LocalDateTime beforeMeridianTime = meridianTime.minusSeconds(2);
        LocalDateTime afterMeridianTime = meridianTime.plusSeconds(2);

        HorizontalCoordinate locationBeforeMeridian = starLocator.locateStar(beforeMeridianTime, starEqCoordinate);
        HorizontalCoordinate locationAtMeridian = starLocator.locateStar(meridianTime, starEqCoordinate);
        HorizontalCoordinate locationAfterMeridian = starLocator.locateStar(afterMeridianTime, starEqCoordinate);

        assertTrue(locationAtMeridian.getAltDegreeValue() > locationBeforeMeridian.getAltDegreeValue());
        assertTrue(locationAtMeridian.getAltDegreeValue() > locationAfterMeridian.getAltDegreeValue());
    }

    private void verifyStarLocation(
            HorizontalCoordinate expectedLocation,
            HorizontalCoordinate actualLocation,
            String message) {
        assertEquals(expectedLocation.getAltDegreeValue(), actualLocation.getAltDegreeValue(), 0.004, String.format(message + " altitude, expected: %s, actual: %s", expectedLocation, actualLocation));
        assertEquals(expectedLocation.getAzDegreeValue(), actualLocation.getAzDegreeValue(), 0.0068, String.format(message + " azimuth, expected: %s, actual: %s", expectedLocation, actualLocation));
    }
}