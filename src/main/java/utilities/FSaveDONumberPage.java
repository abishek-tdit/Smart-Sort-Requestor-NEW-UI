package utilities;

public class FSaveDONumberPage {

    // =========================================================
    // Saved DO Number
    // =========================================================
    private static String doNumber;


    // =========================================================
    // Save DO Number
    // =========================================================
    public static void saveDONumber(String number) {

        doNumber = number;
    }


    // =========================================================
    // Get Saved DO Number
    // =========================================================
    public static String getDONumber() {

        return doNumber;
    }


    // =========================================================
    // Clear DO Number
    // =========================================================
    public static void clearDONumber() {

        doNumber = null;
    }
}