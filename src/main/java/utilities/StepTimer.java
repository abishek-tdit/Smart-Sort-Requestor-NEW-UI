package utilities;

public class StepTimer {

    private static long startTime;

    // =========================================================
    // Start Timer
    // =========================================================

    public static void startStep() {

        startTime =
                System.currentTimeMillis();
    }

    // =========================================================
    // End Timer
    // =========================================================

    public static double endStep() {

        long endTime =
                System.currentTimeMillis();

        return
                (endTime - startTime)
                        / 1000.0;
    }
}