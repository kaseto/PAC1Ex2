package edu.uoc.pac1;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PAC1Ex2Test {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    @Order(1)
    void testMortgageMonthlyFee() {

        assertEquals(-1, PAC1Ex2.mortgageMonthlyFee(150000, 0, 10));
        assertEquals(-1, PAC1Ex2.mortgageMonthlyFee(-10000, 0.89, 12));
        assertEquals(-1, PAC1Ex2.mortgageMonthlyFee(10000, 0.89, 0));
        assertEquals(-1, PAC1Ex2.mortgageMonthlyFee(10000, -0.89, -12));

        assertEquals(8373.56, PAC1Ex2.mortgageMonthlyFee(100000, 0.89, 12), 2);
        assertEquals(100083.33, PAC1Ex2.mortgageMonthlyFee(100000, 1, 1), 2);
        assertEquals(4192.75, PAC1Ex2.mortgageMonthlyFee(100000, 0.60, 24), 2);

        assertEquals(598.49, PAC1Ex2.mortgageMonthlyFee(100000, 1, 180), 2);
        assertEquals(932.44, PAC1Ex2.mortgageMonthlyFee(255000, 0.75, 300), 2);
        assertEquals(662.89, PAC1Ex2.mortgageMonthlyFee(144000, 1.01, 240), 2);
        assertEquals(349.53, PAC1Ex2.mortgageMonthlyFee(65000, 0.88, 200), 2);

    }

    @Test
    @Order(2)
    void testMortgageAmortizationTableError1() {
        assertEquals(-1, PAC1Ex2.mortgageAmortizationSchedule(100000, 0, 0.60), 2);
        assertEquals("The duration of the mortgage must be equal to or greater than two years", outContent.toString().trim());
    }

    @Test
    @Order(3)
    void testMortgageAmortizationTableError2() {
        assertEquals(-1, PAC1Ex2.mortgageAmortizationSchedule(100000, 1, 0.60), 2);
        assertEquals("The duration of the mortgage must be equal to or greater than two years", outContent.toString().trim());
    }

    @Test
    @Order(4)
    void testMortgageAmortizationTableError3() {
        assertEquals(-1, PAC1Ex2.mortgageAmortizationSchedule(100000, 2, 2.60), 2);
        assertEquals("The interest applied must be less than the fixed interest of the first year", outContent.toString().trim());
    }

   @Test
    @Order(5)
    void testMortgageAmortizationTable1() {

        assertEquals(489.23, PAC1Ex2.mortgageAmortizationSchedule(28000, 2, 0.87), 2);

        assertEquals("Month: 1, Fee: 1190.51, Interest: 45.50, Amortization: 1145.01, Debt: 26854.99" + System.lineSeparator() +
                "Month: 2, Fee: 1190.51, Interest: 43.64, Amortization: 1146.87, Debt: 25708.12" + System.lineSeparator() +
                "Month: 3, Fee: 1190.51, Interest: 41.78, Amortization: 1148.74, Debt: 24559.38" + System.lineSeparator() +
                "Month: 4, Fee: 1190.51, Interest: 39.91, Amortization: 1150.60, Debt: 23408.78" + System.lineSeparator() +
                "Month: 5, Fee: 1190.51, Interest: 38.04, Amortization: 1152.47, Debt: 22256.30" + System.lineSeparator() +
                "Month: 6, Fee: 1190.51, Interest: 36.17, Amortization: 1154.35, Debt: 21101.96" + System.lineSeparator() +
                "Month: 7, Fee: 1190.51, Interest: 34.29, Amortization: 1156.22, Debt: 19945.74" + System.lineSeparator() +
                "Month: 8, Fee: 1190.51, Interest: 32.41, Amortization: 1158.10, Debt: 18787.64" + System.lineSeparator() +
                "Month: 9, Fee: 1190.51, Interest: 30.53, Amortization: 1159.98, Debt: 17627.65" + System.lineSeparator() +
                "Month: 10, Fee: 1190.51, Interest: 28.64, Amortization: 1161.87, Debt: 16465.79" + System.lineSeparator() +
                "Month: 11, Fee: 1190.51, Interest: 26.76, Amortization: 1163.76, Debt: 15302.03" + System.lineSeparator() +
                "Month: 12, Fee: 1190.51, Interest: 24.87, Amortization: 1165.65, Debt: 14136.38" + System.lineSeparator() +
                "Month: 13, Fee: 1183.59, Interest: 10.25, Amortization: 1173.34, Debt: 12963.04" + System.lineSeparator() +
                "Month: 14, Fee: 1183.59, Interest: 9.40, Amortization: 1174.19, Debt: 11788.85" + System.lineSeparator() +
                "Month: 15, Fee: 1183.59, Interest: 8.55, Amortization: 1175.04, Debt: 10613.81" + System.lineSeparator() +
                "Month: 16, Fee: 1183.59, Interest: 7.70, Amortization: 1175.90, Debt: 9437.91" + System.lineSeparator() +
                "Month: 17, Fee: 1183.59, Interest: 6.84, Amortization: 1176.75, Debt: 8261.16" + System.lineSeparator() +
                "Month: 18, Fee: 1183.59, Interest: 5.99, Amortization: 1177.60, Debt: 7083.56" + System.lineSeparator() +
                "Month: 19, Fee: 1183.59, Interest: 5.14, Amortization: 1178.46, Debt: 5905.10" + System.lineSeparator() +
                "Month: 20, Fee: 1183.59, Interest: 4.28, Amortization: 1179.31, Debt: 4725.80" + System.lineSeparator() +
                "Month: 21, Fee: 1183.59, Interest: 3.43, Amortization: 1180.16, Debt: 3545.63" + System.lineSeparator() +
                "Month: 22, Fee: 1183.59, Interest: 2.57, Amortization: 1181.02, Debt: 2364.61" + System.lineSeparator() +
                "Month: 23, Fee: 1183.59, Interest: 1.71, Amortization: 1181.88, Debt: 1182.73" + System.lineSeparator() +
                "Month: 24, Fee: 1183.59, Interest: 0.86, Amortization: 1182.73, Debt: 0.00", outContent.toString().trim());
    }

    @Test
    @Order(6)
    void testMortgageAmortizationTable2() {

        assertEquals(8779.03, PAC1Ex2.mortgageAmortizationSchedule(120000, 20, 0.58), 2);

        assertEquals("Month: 1, Fee: 604.22, Interest: 195.00, Amortization: 409.22, Debt: 119590.78" + System.lineSeparator() +
                "Month: 2, Fee: 604.22, Interest: 194.34, Amortization: 409.89, Debt: 119180.89" + System.lineSeparator() +
                "Month: 3, Fee: 604.22, Interest: 193.67, Amortization: 410.55, Debt: 118770.34" + System.lineSeparator() +
                "Month: 4, Fee: 604.22, Interest: 193.00, Amortization: 411.22, Debt: 118359.12" + System.lineSeparator() +
                "Month: 5, Fee: 604.22, Interest: 192.33, Amortization: 411.89, Debt: 117947.23" + System.lineSeparator() +
                "Month: 6, Fee: 604.22, Interest: 191.66, Amortization: 412.56, Debt: 117534.67" + System.lineSeparator() +
                "Month: 7, Fee: 604.22, Interest: 190.99, Amortization: 413.23, Debt: 117121.44" + System.lineSeparator() +
                "Month: 8, Fee: 604.22, Interest: 190.32, Amortization: 413.90, Debt: 116707.54" + System.lineSeparator() +
                "Month: 9, Fee: 604.22, Interest: 189.65, Amortization: 414.57, Debt: 116292.97" + System.lineSeparator() +
                "Month: 10, Fee: 604.22, Interest: 188.98, Amortization: 415.25, Debt: 115877.72" + System.lineSeparator() +
                "Month: 11, Fee: 604.22, Interest: 188.30, Amortization: 415.92, Debt: 115461.80" + System.lineSeparator() +
                "Month: 12, Fee: 604.22, Interest: 187.63, Amortization: 416.60, Debt: 115045.20" + System.lineSeparator() +
                "Month: 13, Fee: 533.02, Interest: 55.61, Amortization: 477.41, Debt: 114567.79" + System.lineSeparator() +
                "Month: 14, Fee: 533.02, Interest: 55.37, Amortization: 477.64, Debt: 114090.14" + System.lineSeparator() +
                "Month: 15, Fee: 533.02, Interest: 55.14, Amortization: 477.88, Debt: 113612.27" + System.lineSeparator() +
                "Month: 16, Fee: 533.02, Interest: 54.91, Amortization: 478.11, Debt: 113134.16" + System.lineSeparator() +
                "Month: 17, Fee: 533.02, Interest: 54.68, Amortization: 478.34, Debt: 112655.82" + System.lineSeparator() +
                "Month: 18, Fee: 533.02, Interest: 54.45, Amortization: 478.57, Debt: 112177.25" + System.lineSeparator() +
                "Month: 19, Fee: 533.02, Interest: 54.22, Amortization: 478.80, Debt: 111698.45" + System.lineSeparator() +
                "Month: 20, Fee: 533.02, Interest: 53.99, Amortization: 479.03, Debt: 111219.42" + System.lineSeparator() +
                "Month: 21, Fee: 533.02, Interest: 53.76, Amortization: 479.26, Debt: 110740.16" + System.lineSeparator() +
                "Month: 22, Fee: 533.02, Interest: 53.52, Amortization: 479.49, Debt: 110260.67" + System.lineSeparator() +
                "Month: 23, Fee: 533.02, Interest: 53.29, Amortization: 479.73, Debt: 109780.94" + System.lineSeparator() +
                "Month: 24, Fee: 533.02, Interest: 53.06, Amortization: 479.96, Debt: 109300.98" + System.lineSeparator() +
                "Month: 25, Fee: 533.02, Interest: 52.83, Amortization: 480.19, Debt: 108820.79" + System.lineSeparator() +
                "Month: 26, Fee: 533.02, Interest: 52.60, Amortization: 480.42, Debt: 108340.37" + System.lineSeparator() +
                "Month: 27, Fee: 533.02, Interest: 52.36, Amortization: 480.65, Debt: 107859.71" + System.lineSeparator() +
                "Month: 28, Fee: 533.02, Interest: 52.13, Amortization: 480.89, Debt: 107378.83" + System.lineSeparator() +
                "Month: 29, Fee: 533.02, Interest: 51.90, Amortization: 481.12, Debt: 106897.71" + System.lineSeparator() +
                "Month: 30, Fee: 533.02, Interest: 51.67, Amortization: 481.35, Debt: 106416.35" + System.lineSeparator() +
                "Month: 31, Fee: 533.02, Interest: 51.43, Amortization: 481.58, Debt: 105934.77" + System.lineSeparator() +
                "Month: 32, Fee: 533.02, Interest: 51.20, Amortization: 481.82, Debt: 105452.95" + System.lineSeparator() +
                "Month: 33, Fee: 533.02, Interest: 50.97, Amortization: 482.05, Debt: 104970.90" + System.lineSeparator() +
                "Month: 34, Fee: 533.02, Interest: 50.74, Amortization: 482.28, Debt: 104488.62" + System.lineSeparator() +
                "Month: 35, Fee: 533.02, Interest: 50.50, Amortization: 482.52, Debt: 104006.10" + System.lineSeparator() +
                "Month: 36, Fee: 533.02, Interest: 50.27, Amortization: 482.75, Debt: 103523.35" + System.lineSeparator() +
                "Month: 37, Fee: 533.02, Interest: 50.04, Amortization: 482.98, Debt: 103040.37" + System.lineSeparator() +
                "Month: 38, Fee: 533.02, Interest: 49.80, Amortization: 483.22, Debt: 102557.15" + System.lineSeparator() +
                "Month: 39, Fee: 533.02, Interest: 49.57, Amortization: 483.45, Debt: 102073.70" + System.lineSeparator() +
                "Month: 40, Fee: 533.02, Interest: 49.34, Amortization: 483.68, Debt: 101590.02" + System.lineSeparator() +
                "Month: 41, Fee: 533.02, Interest: 49.10, Amortization: 483.92, Debt: 101106.10" + System.lineSeparator() +
                "Month: 42, Fee: 533.02, Interest: 48.87, Amortization: 484.15, Debt: 100621.95" + System.lineSeparator() +
                "Month: 43, Fee: 533.02, Interest: 48.63, Amortization: 484.39, Debt: 100137.57" + System.lineSeparator() +
                "Month: 44, Fee: 533.02, Interest: 48.40, Amortization: 484.62, Debt: 99652.95" + System.lineSeparator() +
                "Month: 45, Fee: 533.02, Interest: 48.17, Amortization: 484.85, Debt: 99168.09" + System.lineSeparator() +
                "Month: 46, Fee: 533.02, Interest: 47.93, Amortization: 485.09, Debt: 98683.01" + System.lineSeparator() +
                "Month: 47, Fee: 533.02, Interest: 47.70, Amortization: 485.32, Debt: 98197.68" + System.lineSeparator() +
                "Month: 48, Fee: 533.02, Interest: 47.46, Amortization: 485.56, Debt: 97712.13" + System.lineSeparator() +
                "Month: 49, Fee: 533.02, Interest: 47.23, Amortization: 485.79, Debt: 97226.34" + System.lineSeparator() +
                "Month: 50, Fee: 533.02, Interest: 46.99, Amortization: 486.03, Debt: 96740.31" + System.lineSeparator() +
                "Month: 51, Fee: 533.02, Interest: 46.76, Amortization: 486.26, Debt: 96254.05" + System.lineSeparator() +
                "Month: 52, Fee: 533.02, Interest: 46.52, Amortization: 486.50, Debt: 95767.55" + System.lineSeparator() +
                "Month: 53, Fee: 533.02, Interest: 46.29, Amortization: 486.73, Debt: 95280.82" + System.lineSeparator() +
                "Month: 54, Fee: 533.02, Interest: 46.05, Amortization: 486.97, Debt: 94793.85" + System.lineSeparator() +
                "Month: 55, Fee: 533.02, Interest: 45.82, Amortization: 487.20, Debt: 94306.65" + System.lineSeparator() +
                "Month: 56, Fee: 533.02, Interest: 45.58, Amortization: 487.44, Debt: 93819.21" + System.lineSeparator() +
                "Month: 57, Fee: 533.02, Interest: 45.35, Amortization: 487.67, Debt: 93331.54" + System.lineSeparator() +
                "Month: 58, Fee: 533.02, Interest: 45.11, Amortization: 487.91, Debt: 92843.63" + System.lineSeparator() +
                "Month: 59, Fee: 533.02, Interest: 44.87, Amortization: 488.14, Debt: 92355.49" + System.lineSeparator() +
                "Month: 60, Fee: 533.02, Interest: 44.64, Amortization: 488.38, Debt: 91867.11" + System.lineSeparator() +
                "Month: 61, Fee: 533.02, Interest: 44.40, Amortization: 488.62, Debt: 91378.49" + System.lineSeparator() +
                "Month: 62, Fee: 533.02, Interest: 44.17, Amortization: 488.85, Debt: 90889.64" + System.lineSeparator() +
                "Month: 63, Fee: 533.02, Interest: 43.93, Amortization: 489.09, Debt: 90400.55" + System.lineSeparator() +
                "Month: 64, Fee: 533.02, Interest: 43.69, Amortization: 489.33, Debt: 89911.22" + System.lineSeparator() +
                "Month: 65, Fee: 533.02, Interest: 43.46, Amortization: 489.56, Debt: 89421.66" + System.lineSeparator() +
                "Month: 66, Fee: 533.02, Interest: 43.22, Amortization: 489.80, Debt: 88931.86" + System.lineSeparator() +
                "Month: 67, Fee: 533.02, Interest: 42.98, Amortization: 490.04, Debt: 88441.83" + System.lineSeparator() +
                "Month: 68, Fee: 533.02, Interest: 42.75, Amortization: 490.27, Debt: 87951.55" + System.lineSeparator() +
                "Month: 69, Fee: 533.02, Interest: 42.51, Amortization: 490.51, Debt: 87461.04" + System.lineSeparator() +
                "Month: 70, Fee: 533.02, Interest: 42.27, Amortization: 490.75, Debt: 86970.30" + System.lineSeparator() +
                "Month: 71, Fee: 533.02, Interest: 42.04, Amortization: 490.98, Debt: 86479.31" + System.lineSeparator() +
                "Month: 72, Fee: 533.02, Interest: 41.80, Amortization: 491.22, Debt: 85988.09" + System.lineSeparator() +
                "Month: 73, Fee: 533.02, Interest: 41.56, Amortization: 491.46, Debt: 85496.63" + System.lineSeparator() +
                "Month: 74, Fee: 533.02, Interest: 41.32, Amortization: 491.70, Debt: 85004.94" + System.lineSeparator() +
                "Month: 75, Fee: 533.02, Interest: 41.09, Amortization: 491.93, Debt: 84513.01" + System.lineSeparator() +
                "Month: 76, Fee: 533.02, Interest: 40.85, Amortization: 492.17, Debt: 84020.83" + System.lineSeparator() +
                "Month: 77, Fee: 533.02, Interest: 40.61, Amortization: 492.41, Debt: 83528.43" + System.lineSeparator() +
                "Month: 78, Fee: 533.02, Interest: 40.37, Amortization: 492.65, Debt: 83035.78" + System.lineSeparator() +
                "Month: 79, Fee: 533.02, Interest: 40.13, Amortization: 492.89, Debt: 82542.89" + System.lineSeparator() +
                "Month: 80, Fee: 533.02, Interest: 39.90, Amortization: 493.12, Debt: 82049.77" + System.lineSeparator() +
                "Month: 81, Fee: 533.02, Interest: 39.66, Amortization: 493.36, Debt: 81556.41" + System.lineSeparator() +
                "Month: 82, Fee: 533.02, Interest: 39.42, Amortization: 493.60, Debt: 81062.81" + System.lineSeparator() +
                "Month: 83, Fee: 533.02, Interest: 39.18, Amortization: 493.84, Debt: 80568.97" + System.lineSeparator() +
                "Month: 84, Fee: 533.02, Interest: 38.94, Amortization: 494.08, Debt: 80074.89" + System.lineSeparator() +
                "Month: 85, Fee: 533.02, Interest: 38.70, Amortization: 494.32, Debt: 79580.57" + System.lineSeparator() +
                "Month: 86, Fee: 533.02, Interest: 38.46, Amortization: 494.56, Debt: 79086.02" + System.lineSeparator() +
                "Month: 87, Fee: 533.02, Interest: 38.22, Amortization: 494.79, Debt: 78591.23" + System.lineSeparator() +
                "Month: 88, Fee: 533.02, Interest: 37.99, Amortization: 495.03, Debt: 78096.19" + System.lineSeparator() +
                "Month: 89, Fee: 533.02, Interest: 37.75, Amortization: 495.27, Debt: 77600.92" + System.lineSeparator() +
                "Month: 90, Fee: 533.02, Interest: 37.51, Amortization: 495.51, Debt: 77105.41" + System.lineSeparator() +
                "Month: 91, Fee: 533.02, Interest: 37.27, Amortization: 495.75, Debt: 76609.66" + System.lineSeparator() +
                "Month: 92, Fee: 533.02, Interest: 37.03, Amortization: 495.99, Debt: 76113.66" + System.lineSeparator() +
                "Month: 93, Fee: 533.02, Interest: 36.79, Amortization: 496.23, Debt: 75617.43" + System.lineSeparator() +
                "Month: 94, Fee: 533.02, Interest: 36.55, Amortization: 496.47, Debt: 75120.96" + System.lineSeparator() +
                "Month: 95, Fee: 533.02, Interest: 36.31, Amortization: 496.71, Debt: 74624.25" + System.lineSeparator() +
                "Month: 96, Fee: 533.02, Interest: 36.07, Amortization: 496.95, Debt: 74127.30" + System.lineSeparator() +
                "Month: 97, Fee: 533.02, Interest: 35.83, Amortization: 497.19, Debt: 73630.11" + System.lineSeparator() +
                "Month: 98, Fee: 533.02, Interest: 35.59, Amortization: 497.43, Debt: 73132.68" + System.lineSeparator() +
                "Month: 99, Fee: 533.02, Interest: 35.35, Amortization: 497.67, Debt: 72635.01" + System.lineSeparator() +
                "Month: 100, Fee: 533.02, Interest: 35.11, Amortization: 497.91, Debt: 72137.10" + System.lineSeparator() +
                "Month: 101, Fee: 533.02, Interest: 34.87, Amortization: 498.15, Debt: 71638.94" + System.lineSeparator() +
                "Month: 102, Fee: 533.02, Interest: 34.63, Amortization: 498.39, Debt: 71140.55" + System.lineSeparator() +
                "Month: 103, Fee: 533.02, Interest: 34.38, Amortization: 498.63, Debt: 70641.91" + System.lineSeparator() +
                "Month: 104, Fee: 533.02, Interest: 34.14, Amortization: 498.88, Debt: 70143.04" + System.lineSeparator() +
                "Month: 105, Fee: 533.02, Interest: 33.90, Amortization: 499.12, Debt: 69643.92" + System.lineSeparator() +
                "Month: 106, Fee: 533.02, Interest: 33.66, Amortization: 499.36, Debt: 69144.56" + System.lineSeparator() +
                "Month: 107, Fee: 533.02, Interest: 33.42, Amortization: 499.60, Debt: 68644.96" + System.lineSeparator() +
                "Month: 108, Fee: 533.02, Interest: 33.18, Amortization: 499.84, Debt: 68145.12" + System.lineSeparator() +
                "Month: 109, Fee: 533.02, Interest: 32.94, Amortization: 500.08, Debt: 67645.04" + System.lineSeparator() +
                "Month: 110, Fee: 533.02, Interest: 32.70, Amortization: 500.32, Debt: 67144.72" + System.lineSeparator() +
                "Month: 111, Fee: 533.02, Interest: 32.45, Amortization: 500.57, Debt: 66644.15" + System.lineSeparator() +
                "Month: 112, Fee: 533.02, Interest: 32.21, Amortization: 500.81, Debt: 66143.34" + System.lineSeparator() +
                "Month: 113, Fee: 533.02, Interest: 31.97, Amortization: 501.05, Debt: 65642.29" + System.lineSeparator() +
                "Month: 114, Fee: 533.02, Interest: 31.73, Amortization: 501.29, Debt: 65141.00" + System.lineSeparator() +
                "Month: 115, Fee: 533.02, Interest: 31.48, Amortization: 501.53, Debt: 64639.47" + System.lineSeparator() +
                "Month: 116, Fee: 533.02, Interest: 31.24, Amortization: 501.78, Debt: 64137.69" + System.lineSeparator() +
                "Month: 117, Fee: 533.02, Interest: 31.00, Amortization: 502.02, Debt: 63635.67" + System.lineSeparator() +
                "Month: 118, Fee: 533.02, Interest: 30.76, Amortization: 502.26, Debt: 63133.41" + System.lineSeparator() +
                "Month: 119, Fee: 533.02, Interest: 30.51, Amortization: 502.50, Debt: 62630.91" + System.lineSeparator() +
                "Month: 120, Fee: 533.02, Interest: 30.27, Amortization: 502.75, Debt: 62128.16" + System.lineSeparator() +
                "Month: 121, Fee: 533.02, Interest: 30.03, Amortization: 502.99, Debt: 61625.17" + System.lineSeparator() +
                "Month: 122, Fee: 533.02, Interest: 29.79, Amortization: 503.23, Debt: 61121.93" + System.lineSeparator() +
                "Month: 123, Fee: 533.02, Interest: 29.54, Amortization: 503.48, Debt: 60618.46" + System.lineSeparator() +
                "Month: 124, Fee: 533.02, Interest: 29.30, Amortization: 503.72, Debt: 60114.74" + System.lineSeparator() +
                "Month: 125, Fee: 533.02, Interest: 29.06, Amortization: 503.96, Debt: 59610.77" + System.lineSeparator() +
                "Month: 126, Fee: 533.02, Interest: 28.81, Amortization: 504.21, Debt: 59106.57" + System.lineSeparator() +
                "Month: 127, Fee: 533.02, Interest: 28.57, Amortization: 504.45, Debt: 58602.11" + System.lineSeparator() +
                "Month: 128, Fee: 533.02, Interest: 28.32, Amortization: 504.69, Debt: 58097.42" + System.lineSeparator() +
                "Month: 129, Fee: 533.02, Interest: 28.08, Amortization: 504.94, Debt: 57592.48" + System.lineSeparator() +
                "Month: 130, Fee: 533.02, Interest: 27.84, Amortization: 505.18, Debt: 57087.30" + System.lineSeparator() +
                "Month: 131, Fee: 533.02, Interest: 27.59, Amortization: 505.43, Debt: 56581.87" + System.lineSeparator() +
                "Month: 132, Fee: 533.02, Interest: 27.35, Amortization: 505.67, Debt: 56076.20" + System.lineSeparator() +
                "Month: 133, Fee: 533.02, Interest: 27.10, Amortization: 505.92, Debt: 55570.28" + System.lineSeparator() +
                "Month: 134, Fee: 533.02, Interest: 26.86, Amortization: 506.16, Debt: 55064.12" + System.lineSeparator() +
                "Month: 135, Fee: 533.02, Interest: 26.61, Amortization: 506.40, Debt: 54557.72" + System.lineSeparator() +
                "Month: 136, Fee: 533.02, Interest: 26.37, Amortization: 506.65, Debt: 54051.07" + System.lineSeparator() +
                "Month: 137, Fee: 533.02, Interest: 26.12, Amortization: 506.89, Debt: 53544.17" + System.lineSeparator() +
                "Month: 138, Fee: 533.02, Interest: 25.88, Amortization: 507.14, Debt: 53037.04" + System.lineSeparator() +
                "Month: 139, Fee: 533.02, Interest: 25.63, Amortization: 507.38, Debt: 52529.65" + System.lineSeparator() +
                "Month: 140, Fee: 533.02, Interest: 25.39, Amortization: 507.63, Debt: 52022.02" + System.lineSeparator() +
                "Month: 141, Fee: 533.02, Interest: 25.14, Amortization: 507.88, Debt: 51514.15" + System.lineSeparator() +
                "Month: 142, Fee: 533.02, Interest: 24.90, Amortization: 508.12, Debt: 51006.03" + System.lineSeparator() +
                "Month: 143, Fee: 533.02, Interest: 24.65, Amortization: 508.37, Debt: 50497.66" + System.lineSeparator() +
                "Month: 144, Fee: 533.02, Interest: 24.41, Amortization: 508.61, Debt: 49989.05" + System.lineSeparator() +
                "Month: 145, Fee: 533.02, Interest: 24.16, Amortization: 508.86, Debt: 49480.19" + System.lineSeparator() +
                "Month: 146, Fee: 533.02, Interest: 23.92, Amortization: 509.10, Debt: 48971.09" + System.lineSeparator() +
                "Month: 147, Fee: 533.02, Interest: 23.67, Amortization: 509.35, Debt: 48461.74" + System.lineSeparator() +
                "Month: 148, Fee: 533.02, Interest: 23.42, Amortization: 509.60, Debt: 47952.14" + System.lineSeparator() +
                "Month: 149, Fee: 533.02, Interest: 23.18, Amortization: 509.84, Debt: 47442.30" + System.lineSeparator() +
                "Month: 150, Fee: 533.02, Interest: 22.93, Amortization: 510.09, Debt: 46932.21" + System.lineSeparator() +
                "Month: 151, Fee: 533.02, Interest: 22.68, Amortization: 510.34, Debt: 46421.87" + System.lineSeparator() +
                "Month: 152, Fee: 533.02, Interest: 22.44, Amortization: 510.58, Debt: 45911.29" + System.lineSeparator() +
                "Month: 153, Fee: 533.02, Interest: 22.19, Amortization: 510.83, Debt: 45400.46" + System.lineSeparator() +
                "Month: 154, Fee: 533.02, Interest: 21.94, Amortization: 511.08, Debt: 44889.39" + System.lineSeparator() +
                "Month: 155, Fee: 533.02, Interest: 21.70, Amortization: 511.32, Debt: 44378.06" + System.lineSeparator() +
                "Month: 156, Fee: 533.02, Interest: 21.45, Amortization: 511.57, Debt: 43866.49" + System.lineSeparator() +
                "Month: 157, Fee: 533.02, Interest: 21.20, Amortization: 511.82, Debt: 43354.68" + System.lineSeparator() +
                "Month: 158, Fee: 533.02, Interest: 20.95, Amortization: 512.06, Debt: 42842.61" + System.lineSeparator() +
                "Month: 159, Fee: 533.02, Interest: 20.71, Amortization: 512.31, Debt: 42330.30" + System.lineSeparator() +
                "Month: 160, Fee: 533.02, Interest: 20.46, Amortization: 512.56, Debt: 41817.74" + System.lineSeparator() +
                "Month: 161, Fee: 533.02, Interest: 20.21, Amortization: 512.81, Debt: 41304.93" + System.lineSeparator() +
                "Month: 162, Fee: 533.02, Interest: 19.96, Amortization: 513.06, Debt: 40791.88" + System.lineSeparator() +
                "Month: 163, Fee: 533.02, Interest: 19.72, Amortization: 513.30, Debt: 40278.58" + System.lineSeparator() +
                "Month: 164, Fee: 533.02, Interest: 19.47, Amortization: 513.55, Debt: 39765.03" + System.lineSeparator() +
                "Month: 165, Fee: 533.02, Interest: 19.22, Amortization: 513.80, Debt: 39251.23" + System.lineSeparator() +
                "Month: 166, Fee: 533.02, Interest: 18.97, Amortization: 514.05, Debt: 38737.18" + System.lineSeparator() +
                "Month: 167, Fee: 533.02, Interest: 18.72, Amortization: 514.30, Debt: 38222.88" + System.lineSeparator() +
                "Month: 168, Fee: 533.02, Interest: 18.47, Amortization: 514.54, Debt: 37708.34" + System.lineSeparator() +
                "Month: 169, Fee: 533.02, Interest: 18.23, Amortization: 514.79, Debt: 37193.54" + System.lineSeparator() +
                "Month: 170, Fee: 533.02, Interest: 17.98, Amortization: 515.04, Debt: 36678.50" + System.lineSeparator() +
                "Month: 171, Fee: 533.02, Interest: 17.73, Amortization: 515.29, Debt: 36163.21" + System.lineSeparator() +
                "Month: 172, Fee: 533.02, Interest: 17.48, Amortization: 515.54, Debt: 35647.67" + System.lineSeparator() +
                "Month: 173, Fee: 533.02, Interest: 17.23, Amortization: 515.79, Debt: 35131.88" + System.lineSeparator() +
                "Month: 174, Fee: 533.02, Interest: 16.98, Amortization: 516.04, Debt: 34615.84" + System.lineSeparator() +
                "Month: 175, Fee: 533.02, Interest: 16.73, Amortization: 516.29, Debt: 34099.55" + System.lineSeparator() +
                "Month: 176, Fee: 533.02, Interest: 16.48, Amortization: 516.54, Debt: 33583.02" + System.lineSeparator() +
                "Month: 177, Fee: 533.02, Interest: 16.23, Amortization: 516.79, Debt: 33066.23" + System.lineSeparator() +
                "Month: 178, Fee: 533.02, Interest: 15.98, Amortization: 517.04, Debt: 32549.19" + System.lineSeparator() +
                "Month: 179, Fee: 533.02, Interest: 15.73, Amortization: 517.29, Debt: 32031.90" + System.lineSeparator() +
                "Month: 180, Fee: 533.02, Interest: 15.48, Amortization: 517.54, Debt: 31514.37" + System.lineSeparator() +
                "Month: 181, Fee: 533.02, Interest: 15.23, Amortization: 517.79, Debt: 30996.58" + System.lineSeparator() +
                "Month: 182, Fee: 533.02, Interest: 14.98, Amortization: 518.04, Debt: 30478.54" + System.lineSeparator() +
                "Month: 183, Fee: 533.02, Interest: 14.73, Amortization: 518.29, Debt: 29960.26" + System.lineSeparator() +
                "Month: 184, Fee: 533.02, Interest: 14.48, Amortization: 518.54, Debt: 29441.72" + System.lineSeparator() +
                "Month: 185, Fee: 533.02, Interest: 14.23, Amortization: 518.79, Debt: 28922.93" + System.lineSeparator() +
                "Month: 186, Fee: 533.02, Interest: 13.98, Amortization: 519.04, Debt: 28403.89" + System.lineSeparator() +
                "Month: 187, Fee: 533.02, Interest: 13.73, Amortization: 519.29, Debt: 27884.60" + System.lineSeparator() +
                "Month: 188, Fee: 533.02, Interest: 13.48, Amortization: 519.54, Debt: 27365.06" + System.lineSeparator() +
                "Month: 189, Fee: 533.02, Interest: 13.23, Amortization: 519.79, Debt: 26845.26" + System.lineSeparator() +
                "Month: 190, Fee: 533.02, Interest: 12.98, Amortization: 520.04, Debt: 26325.22" + System.lineSeparator() +
                "Month: 191, Fee: 533.02, Interest: 12.72, Amortization: 520.30, Debt: 25804.92" + System.lineSeparator() +
                "Month: 192, Fee: 533.02, Interest: 12.47, Amortization: 520.55, Debt: 25284.38" + System.lineSeparator() +
                "Month: 193, Fee: 533.02, Interest: 12.22, Amortization: 520.80, Debt: 24763.58" + System.lineSeparator() +
                "Month: 194, Fee: 533.02, Interest: 11.97, Amortization: 521.05, Debt: 24242.53" + System.lineSeparator() +
                "Month: 195, Fee: 533.02, Interest: 11.72, Amortization: 521.30, Debt: 23721.23" + System.lineSeparator() +
                "Month: 196, Fee: 533.02, Interest: 11.47, Amortization: 521.55, Debt: 23199.67" + System.lineSeparator() +
                "Month: 197, Fee: 533.02, Interest: 11.21, Amortization: 521.81, Debt: 22677.87" + System.lineSeparator() +
                "Month: 198, Fee: 533.02, Interest: 10.96, Amortization: 522.06, Debt: 22155.81" + System.lineSeparator() +
                "Month: 199, Fee: 533.02, Interest: 10.71, Amortization: 522.31, Debt: 21633.50" + System.lineSeparator() +
                "Month: 200, Fee: 533.02, Interest: 10.46, Amortization: 522.56, Debt: 21110.94" + System.lineSeparator() +
                "Month: 201, Fee: 533.02, Interest: 10.20, Amortization: 522.82, Debt: 20588.12" + System.lineSeparator() +
                "Month: 202, Fee: 533.02, Interest: 9.95, Amortization: 523.07, Debt: 20065.05" + System.lineSeparator() +
                "Month: 203, Fee: 533.02, Interest: 9.70, Amortization: 523.32, Debt: 19541.73" + System.lineSeparator() +
                "Month: 204, Fee: 533.02, Interest: 9.45, Amortization: 523.57, Debt: 19018.16" + System.lineSeparator() +
                "Month: 205, Fee: 533.02, Interest: 9.19, Amortization: 523.83, Debt: 18494.33" + System.lineSeparator() +
                "Month: 206, Fee: 533.02, Interest: 8.94, Amortization: 524.08, Debt: 17970.25" + System.lineSeparator() +
                "Month: 207, Fee: 533.02, Interest: 8.69, Amortization: 524.33, Debt: 17445.92" + System.lineSeparator() +
                "Month: 208, Fee: 533.02, Interest: 8.43, Amortization: 524.59, Debt: 16921.33" + System.lineSeparator() +
                "Month: 209, Fee: 533.02, Interest: 8.18, Amortization: 524.84, Debt: 16396.49" + System.lineSeparator() +
                "Month: 210, Fee: 533.02, Interest: 7.92, Amortization: 525.09, Debt: 15871.39" + System.lineSeparator() +
                "Month: 211, Fee: 533.02, Interest: 7.67, Amortization: 525.35, Debt: 15346.05" + System.lineSeparator() +
                "Month: 212, Fee: 533.02, Interest: 7.42, Amortization: 525.60, Debt: 14820.44" + System.lineSeparator() +
                "Month: 213, Fee: 533.02, Interest: 7.16, Amortization: 525.86, Debt: 14294.59" + System.lineSeparator() +
                "Month: 214, Fee: 533.02, Interest: 6.91, Amortization: 526.11, Debt: 13768.48" + System.lineSeparator() +
                "Month: 215, Fee: 533.02, Interest: 6.65, Amortization: 526.36, Debt: 13242.11" + System.lineSeparator() +
                "Month: 216, Fee: 533.02, Interest: 6.40, Amortization: 526.62, Debt: 12715.49" + System.lineSeparator() +
                "Month: 217, Fee: 533.02, Interest: 6.15, Amortization: 526.87, Debt: 12188.62" + System.lineSeparator() +
                "Month: 218, Fee: 533.02, Interest: 5.89, Amortization: 527.13, Debt: 11661.49" + System.lineSeparator() +
                "Month: 219, Fee: 533.02, Interest: 5.64, Amortization: 527.38, Debt: 11134.11" + System.lineSeparator() +
                "Month: 220, Fee: 533.02, Interest: 5.38, Amortization: 527.64, Debt: 10606.47" + System.lineSeparator() +
                "Month: 221, Fee: 533.02, Interest: 5.13, Amortization: 527.89, Debt: 10078.58" + System.lineSeparator() +
                "Month: 222, Fee: 533.02, Interest: 4.87, Amortization: 528.15, Debt: 9550.43" + System.lineSeparator() +
                "Month: 223, Fee: 533.02, Interest: 4.62, Amortization: 528.40, Debt: 9022.03" + System.lineSeparator() +
                "Month: 224, Fee: 533.02, Interest: 4.36, Amortization: 528.66, Debt: 8493.37" + System.lineSeparator() +
                "Month: 225, Fee: 533.02, Interest: 4.11, Amortization: 528.91, Debt: 7964.46" + System.lineSeparator() +
                "Month: 226, Fee: 533.02, Interest: 3.85, Amortization: 529.17, Debt: 7435.29" + System.lineSeparator() +
                "Month: 227, Fee: 533.02, Interest: 3.59, Amortization: 529.43, Debt: 6905.86" + System.lineSeparator() +
                "Month: 228, Fee: 533.02, Interest: 3.34, Amortization: 529.68, Debt: 6376.18" + System.lineSeparator() +
                "Month: 229, Fee: 533.02, Interest: 3.08, Amortization: 529.94, Debt: 5846.24" + System.lineSeparator() +
                "Month: 230, Fee: 533.02, Interest: 2.83, Amortization: 530.19, Debt: 5316.05" + System.lineSeparator() +
                "Month: 231, Fee: 533.02, Interest: 2.57, Amortization: 530.45, Debt: 4785.60" + System.lineSeparator() +
                "Month: 232, Fee: 533.02, Interest: 2.31, Amortization: 530.71, Debt: 4254.89" + System.lineSeparator() +
                "Month: 233, Fee: 533.02, Interest: 2.06, Amortization: 530.96, Debt: 3723.93" + System.lineSeparator() +
                "Month: 234, Fee: 533.02, Interest: 1.80, Amortization: 531.22, Debt: 3192.71" + System.lineSeparator() +
                "Month: 235, Fee: 533.02, Interest: 1.54, Amortization: 531.48, Debt: 2661.24" + System.lineSeparator() +
                "Month: 236, Fee: 533.02, Interest: 1.29, Amortization: 531.73, Debt: 2129.50" + System.lineSeparator() +
                "Month: 237, Fee: 533.02, Interest: 1.03, Amortization: 531.99, Debt: 1597.51" + System.lineSeparator() +
                "Month: 238, Fee: 533.02, Interest: 0.77, Amortization: 532.25, Debt: 1065.27" + System.lineSeparator() +
                "Month: 239, Fee: 533.02, Interest: 0.51, Amortization: 532.50, Debt: 532.76" + System.lineSeparator() +
                "Month: 240, Fee: 533.02, Interest: 0.26, Amortization: 532.76, Debt: 0.00", outContent.toString().trim());
    }



   @Test
    @Order(7)
    void testFixedRate() {
        assertEquals(1.06, PAC1Ex2.fixedRate(10000));
        assertEquals(1.06, PAC1Ex2.fixedRate(100000));
        assertEquals(1.15, PAC1Ex2.fixedRate(100001));
        assertEquals(1.15, PAC1Ex2.fixedRate(149000));
        assertEquals(1.15, PAC1Ex2.fixedRate(150000));
        assertEquals(1.25, PAC1Ex2.fixedRate(150001));
        assertEquals(1.25, PAC1Ex2.fixedRate(300000));
    }


   @Test
    @Order(8)
    void testMortgageAmortizationTableFixedRateError() {
        assertEquals(-1, PAC1Ex2.mortgageAmortizationSchedule(100000, 1), 2);
        assertEquals("The duration of the mortgage must be equal to or greater than two years", outContent.toString().trim());
    }


    @Test
    @Order(9)
    void testMortgageAmortizationTableFixedRate1() {
        assertEquals(1295.55, PAC1Ex2.mortgageAmortizationSchedule(72000, 2), 2);
        assertEquals("Month: 1, Fee: 3061.32, Interest: 117.00, Amortization: 2944.32, Debt: 69055.68" +System.lineSeparator()+
                "Month: 2, Fee: 3061.32, Interest: 112.22, Amortization: 2949.10, Debt: 66106.58" +System.lineSeparator()+
                "Month: 3, Fee: 3061.32, Interest: 107.42, Amortization: 2953.89, Debt: 63152.69" +System.lineSeparator()+
                "Month: 4, Fee: 3061.32, Interest: 102.62, Amortization: 2958.69, Debt: 60193.99" +System.lineSeparator()+
                "Month: 5, Fee: 3061.32, Interest: 97.82, Amortization: 2963.50, Debt: 57230.49" +System.lineSeparator()+
                "Month: 6, Fee: 3061.32, Interest: 93.00, Amortization: 2968.32, Debt: 54262.18" +System.lineSeparator()+
                "Month: 7, Fee: 3061.32, Interest: 88.18, Amortization: 2973.14, Debt: 51289.04" +System.lineSeparator()+
                "Month: 8, Fee: 3061.32, Interest: 83.34, Amortization: 2977.97, Debt: 48311.06" +System.lineSeparator()+
                "Month: 9, Fee: 3061.32, Interest: 78.51, Amortization: 2982.81, Debt: 45328.25" +System.lineSeparator()+
                "Month: 10, Fee: 3061.32, Interest: 73.66, Amortization: 2987.66, Debt: 42340.59" +System.lineSeparator()+
                "Month: 11, Fee: 3061.32, Interest: 68.80, Amortization: 2992.51, Debt: 39348.08" +System.lineSeparator()+
                "Month: 12, Fee: 3061.32, Interest: 63.94, Amortization: 2997.38, Debt: 36350.70" +System.lineSeparator()+
                "Month: 13, Fee: 3046.65, Interest: 32.11, Amortization: 3014.54, Debt: 33336.17" +System.lineSeparator()+
                "Month: 14, Fee: 3046.65, Interest: 29.45, Amortization: 3017.20, Debt: 30318.97" +System.lineSeparator()+
                "Month: 15, Fee: 3046.65, Interest: 26.78, Amortization: 3019.86, Debt: 27299.10" +System.lineSeparator()+
                "Month: 16, Fee: 3046.65, Interest: 24.11, Amortization: 3022.53, Debt: 24276.57" +System.lineSeparator()+
                "Month: 17, Fee: 3046.65, Interest: 21.44, Amortization: 3025.20, Debt: 21251.37" +System.lineSeparator()+
                "Month: 18, Fee: 3046.65, Interest: 18.77, Amortization: 3027.87, Debt: 18223.50" +System.lineSeparator()+
                "Month: 19, Fee: 3046.65, Interest: 16.10, Amortization: 3030.55, Debt: 15192.95" +System.lineSeparator()+
                "Month: 20, Fee: 3046.65, Interest: 13.42, Amortization: 3033.23, Debt: 12159.72" +System.lineSeparator()+
                "Month: 21, Fee: 3046.65, Interest: 10.74, Amortization: 3035.91, Debt: 9123.82" +System.lineSeparator()+
                "Month: 22, Fee: 3046.65, Interest: 8.06, Amortization: 3038.59, Debt: 6085.23" +System.lineSeparator()+
                "Month: 23, Fee: 3046.65, Interest: 5.38, Amortization: 3041.27, Debt: 3043.96" +System.lineSeparator()+
                "Month: 24, Fee: 3046.65, Interest: 2.69, Amortization: 3043.96, Debt: 0.00", outContent.toString().trim());
    }

    @Test
    @Order(10)
    void testMortgageAmortizationTableFixedRate2() {
        assertEquals(7413.68, PAC1Ex2.mortgageAmortizationSchedule(135000, 8), 2);
        assertEquals("Month: 1, Fee: 1519.93, Interest: 219.38, Amortization: 1300.55, Debt: 133699.45" +System.lineSeparator()+
                "Month: 2, Fee: 1519.93, Interest: 217.26, Amortization: 1302.67, Debt: 132396.78" +System.lineSeparator()+
                "Month: 3, Fee: 1519.93, Interest: 215.14, Amortization: 1304.78, Debt: 131092.00" +System.lineSeparator()+
                "Month: 4, Fee: 1519.93, Interest: 213.02, Amortization: 1306.90, Debt: 129785.09" +System.lineSeparator()+
                "Month: 5, Fee: 1519.93, Interest: 210.90, Amortization: 1309.03, Debt: 128476.07" +System.lineSeparator()+
                "Month: 6, Fee: 1519.93, Interest: 208.77, Amortization: 1311.15, Debt: 127164.91" +System.lineSeparator()+
                "Month: 7, Fee: 1519.93, Interest: 206.64, Amortization: 1313.29, Debt: 125851.63" +System.lineSeparator()+
                "Month: 8, Fee: 1519.93, Interest: 204.51, Amortization: 1315.42, Debt: 124536.21" +System.lineSeparator()+
                "Month: 9, Fee: 1519.93, Interest: 202.37, Amortization: 1317.56, Debt: 123218.65" +System.lineSeparator()+
                "Month: 10, Fee: 1519.93, Interest: 200.23, Amortization: 1319.70, Debt: 121898.95" +System.lineSeparator()+
                "Month: 11, Fee: 1519.93, Interest: 198.09, Amortization: 1321.84, Debt: 120577.11" +System.lineSeparator()+
                "Month: 12, Fee: 1519.93, Interest: 195.94, Amortization: 1323.99, Debt: 119253.12" +System.lineSeparator()+
                "Month: 13, Fee: 1478.27, Interest: 114.28, Amortization: 1363.98, Debt: 117889.14" +System.lineSeparator()+
                "Month: 14, Fee: 1478.27, Interest: 112.98, Amortization: 1365.29, Debt: 116523.84" +System.lineSeparator()+
                "Month: 15, Fee: 1478.27, Interest: 111.67, Amortization: 1366.60, Debt: 115157.24" +System.lineSeparator()+
                "Month: 16, Fee: 1478.27, Interest: 110.36, Amortization: 1367.91, Debt: 113789.33" +System.lineSeparator()+
                "Month: 17, Fee: 1478.27, Interest: 109.05, Amortization: 1369.22, Debt: 112420.11" +System.lineSeparator()+
                "Month: 18, Fee: 1478.27, Interest: 107.74, Amortization: 1370.53, Debt: 111049.58" +System.lineSeparator()+
                "Month: 19, Fee: 1478.27, Interest: 106.42, Amortization: 1371.85, Debt: 109677.74" +System.lineSeparator()+
                "Month: 20, Fee: 1478.27, Interest: 105.11, Amortization: 1373.16, Debt: 108304.57" +System.lineSeparator()+
                "Month: 21, Fee: 1478.27, Interest: 103.79, Amortization: 1374.48, Debt: 106930.10" +System.lineSeparator()+
                "Month: 22, Fee: 1478.27, Interest: 102.47, Amortization: 1375.79, Debt: 105554.30" +System.lineSeparator()+
                "Month: 23, Fee: 1478.27, Interest: 101.16, Amortization: 1377.11, Debt: 104177.19" +System.lineSeparator()+
                "Month: 24, Fee: 1478.27, Interest: 99.84, Amortization: 1378.43, Debt: 102798.76" +System.lineSeparator()+
                "Month: 25, Fee: 1478.27, Interest: 98.52, Amortization: 1379.75, Debt: 101419.01" +System.lineSeparator()+
                "Month: 26, Fee: 1478.27, Interest: 97.19, Amortization: 1381.08, Debt: 100037.93" +System.lineSeparator()+
                "Month: 27, Fee: 1478.27, Interest: 95.87, Amortization: 1382.40, Debt: 98655.53" +System.lineSeparator()+
                "Month: 28, Fee: 1478.27, Interest: 94.54, Amortization: 1383.72, Debt: 97271.81" +System.lineSeparator()+
                "Month: 29, Fee: 1478.27, Interest: 93.22, Amortization: 1385.05, Debt: 95886.76" +System.lineSeparator()+
                "Month: 30, Fee: 1478.27, Interest: 91.89, Amortization: 1386.38, Debt: 94500.38" +System.lineSeparator()+
                "Month: 31, Fee: 1478.27, Interest: 90.56, Amortization: 1387.71, Debt: 93112.68" +System.lineSeparator()+
                "Month: 32, Fee: 1478.27, Interest: 89.23, Amortization: 1389.04, Debt: 91723.64" +System.lineSeparator()+
                "Month: 33, Fee: 1478.27, Interest: 87.90, Amortization: 1390.37, Debt: 90333.28" +System.lineSeparator()+
                "Month: 34, Fee: 1478.27, Interest: 86.57, Amortization: 1391.70, Debt: 88941.58" +System.lineSeparator()+
                "Month: 35, Fee: 1478.27, Interest: 85.24, Amortization: 1393.03, Debt: 87548.54" +System.lineSeparator()+
                "Month: 36, Fee: 1478.27, Interest: 83.90, Amortization: 1394.37, Debt: 86154.18" +System.lineSeparator()+
                "Month: 37, Fee: 1478.27, Interest: 82.56, Amortization: 1395.70, Debt: 84758.47" +System.lineSeparator()+
                "Month: 38, Fee: 1478.27, Interest: 81.23, Amortization: 1397.04, Debt: 83361.43" +System.lineSeparator()+
                "Month: 39, Fee: 1478.27, Interest: 79.89, Amortization: 1398.38, Debt: 81963.05" +System.lineSeparator()+
                "Month: 40, Fee: 1478.27, Interest: 78.55, Amortization: 1399.72, Debt: 80563.33" +System.lineSeparator()+
                "Month: 41, Fee: 1478.27, Interest: 77.21, Amortization: 1401.06, Debt: 79162.27" +System.lineSeparator()+
                "Month: 42, Fee: 1478.27, Interest: 75.86, Amortization: 1402.40, Debt: 77759.86" +System.lineSeparator()+
                "Month: 43, Fee: 1478.27, Interest: 74.52, Amortization: 1403.75, Debt: 76356.11" +System.lineSeparator()+
                "Month: 44, Fee: 1478.27, Interest: 73.17, Amortization: 1405.09, Debt: 74951.02" +System.lineSeparator()+
                "Month: 45, Fee: 1478.27, Interest: 71.83, Amortization: 1406.44, Debt: 73544.58" +System.lineSeparator()+
                "Month: 46, Fee: 1478.27, Interest: 70.48, Amortization: 1407.79, Debt: 72136.79" +System.lineSeparator()+
                "Month: 47, Fee: 1478.27, Interest: 69.13, Amortization: 1409.14, Debt: 70727.65" +System.lineSeparator()+
                "Month: 48, Fee: 1478.27, Interest: 67.78, Amortization: 1410.49, Debt: 69317.17" +System.lineSeparator()+
                "Month: 49, Fee: 1478.27, Interest: 66.43, Amortization: 1411.84, Debt: 67905.33" +System.lineSeparator()+
                "Month: 50, Fee: 1478.27, Interest: 65.08, Amortization: 1413.19, Debt: 66492.13" +System.lineSeparator()+
                "Month: 51, Fee: 1478.27, Interest: 63.72, Amortization: 1414.55, Debt: 65077.59" +System.lineSeparator()+
                "Month: 52, Fee: 1478.27, Interest: 62.37, Amortization: 1415.90, Debt: 63661.68" +System.lineSeparator()+
                "Month: 53, Fee: 1478.27, Interest: 61.01, Amortization: 1417.26, Debt: 62244.43" +System.lineSeparator()+
                "Month: 54, Fee: 1478.27, Interest: 59.65, Amortization: 1418.62, Debt: 60825.81" +System.lineSeparator()+
                "Month: 55, Fee: 1478.27, Interest: 58.29, Amortization: 1419.98, Debt: 59405.83" +System.lineSeparator()+
                "Month: 56, Fee: 1478.27, Interest: 56.93, Amortization: 1421.34, Debt: 57984.49" +System.lineSeparator()+
                "Month: 57, Fee: 1478.27, Interest: 55.57, Amortization: 1422.70, Debt: 56561.79" +System.lineSeparator()+
                "Month: 58, Fee: 1478.27, Interest: 54.21, Amortization: 1424.06, Debt: 55137.73" +System.lineSeparator()+
                "Month: 59, Fee: 1478.27, Interest: 52.84, Amortization: 1425.43, Debt: 53712.30" +System.lineSeparator()+
                "Month: 60, Fee: 1478.27, Interest: 51.47, Amortization: 1426.79, Debt: 52285.51" +System.lineSeparator()+
                "Month: 61, Fee: 1478.27, Interest: 50.11, Amortization: 1428.16, Debt: 50857.35" +System.lineSeparator()+
                "Month: 62, Fee: 1478.27, Interest: 48.74, Amortization: 1429.53, Debt: 49427.82" +System.lineSeparator()+
                "Month: 63, Fee: 1478.27, Interest: 47.37, Amortization: 1430.90, Debt: 47996.92" +System.lineSeparator()+
                "Month: 64, Fee: 1478.27, Interest: 46.00, Amortization: 1432.27, Debt: 46564.64" +System.lineSeparator()+
                "Month: 65, Fee: 1478.27, Interest: 44.62, Amortization: 1433.64, Debt: 45131.00" +System.lineSeparator()+
                "Month: 66, Fee: 1478.27, Interest: 43.25, Amortization: 1435.02, Debt: 43695.98" +System.lineSeparator()+
                "Month: 67, Fee: 1478.27, Interest: 41.88, Amortization: 1436.39, Debt: 42259.59" +System.lineSeparator()+
                "Month: 68, Fee: 1478.27, Interest: 40.50, Amortization: 1437.77, Debt: 40821.82" +System.lineSeparator()+
                "Month: 69, Fee: 1478.27, Interest: 39.12, Amortization: 1439.15, Debt: 39382.67" +System.lineSeparator()+
                "Month: 70, Fee: 1478.27, Interest: 37.74, Amortization: 1440.53, Debt: 37942.14" +System.lineSeparator()+
                "Month: 71, Fee: 1478.27, Interest: 36.36, Amortization: 1441.91, Debt: 36500.24" +System.lineSeparator()+
                "Month: 72, Fee: 1478.27, Interest: 34.98, Amortization: 1443.29, Debt: 35056.95" +System.lineSeparator()+
                "Month: 73, Fee: 1478.27, Interest: 33.60, Amortization: 1444.67, Debt: 33612.28" +System.lineSeparator()+
                "Month: 74, Fee: 1478.27, Interest: 32.21, Amortization: 1446.06, Debt: 32166.22" +System.lineSeparator()+
                "Month: 75, Fee: 1478.27, Interest: 30.83, Amortization: 1447.44, Debt: 30718.78" +System.lineSeparator()+
                "Month: 76, Fee: 1478.27, Interest: 29.44, Amortization: 1448.83, Debt: 29269.95" +System.lineSeparator()+
                "Month: 77, Fee: 1478.27, Interest: 28.05, Amortization: 1450.22, Debt: 27819.73" +System.lineSeparator()+
                "Month: 78, Fee: 1478.27, Interest: 26.66, Amortization: 1451.61, Debt: 26368.12" +System.lineSeparator()+
                "Month: 79, Fee: 1478.27, Interest: 25.27, Amortization: 1453.00, Debt: 24915.12" +System.lineSeparator()+
                "Month: 80, Fee: 1478.27, Interest: 23.88, Amortization: 1454.39, Debt: 23460.73" +System.lineSeparator()+
                "Month: 81, Fee: 1478.27, Interest: 22.48, Amortization: 1455.79, Debt: 22004.95" +System.lineSeparator()+
                "Month: 82, Fee: 1478.27, Interest: 21.09, Amortization: 1457.18, Debt: 20547.77" +System.lineSeparator()+
                "Month: 83, Fee: 1478.27, Interest: 19.69, Amortization: 1458.58, Debt: 19089.19" +System.lineSeparator()+
                "Month: 84, Fee: 1478.27, Interest: 18.29, Amortization: 1459.97, Debt: 17629.21" +System.lineSeparator()+
                "Month: 85, Fee: 1478.27, Interest: 16.89, Amortization: 1461.37, Debt: 16167.84" +System.lineSeparator()+
                "Month: 86, Fee: 1478.27, Interest: 15.49, Amortization: 1462.77, Debt: 14705.07" +System.lineSeparator()+
                "Month: 87, Fee: 1478.27, Interest: 14.09, Amortization: 1464.18, Debt: 13240.89" +System.lineSeparator()+
                "Month: 88, Fee: 1478.27, Interest: 12.69, Amortization: 1465.58, Debt: 11775.31" +System.lineSeparator()+
                "Month: 89, Fee: 1478.27, Interest: 11.28, Amortization: 1466.98, Debt: 10308.33" +System.lineSeparator()+
                "Month: 90, Fee: 1478.27, Interest: 9.88, Amortization: 1468.39, Debt: 8839.94" +System.lineSeparator()+
                "Month: 91, Fee: 1478.27, Interest: 8.47, Amortization: 1469.80, Debt: 7370.14" +System.lineSeparator()+
                "Month: 92, Fee: 1478.27, Interest: 7.06, Amortization: 1471.21, Debt: 5898.93" +System.lineSeparator()+
                "Month: 93, Fee: 1478.27, Interest: 5.65, Amortization: 1472.62, Debt: 4426.32" +System.lineSeparator()+
                "Month: 94, Fee: 1478.27, Interest: 4.24, Amortization: 1474.03, Debt: 2952.29" +System.lineSeparator()+
                "Month: 95, Fee: 1478.27, Interest: 2.83, Amortization: 1475.44, Debt: 1476.85" +System.lineSeparator()+
                "Month: 96, Fee: 1478.27, Interest: 1.42, Amortization: 1476.85, Debt: 0.00", outContent.toString().trim());
    }
}


