package com.AndroidTest.BSmartSortRegistrationArabicFlow;

import Base.ExtentTestListener;

import java.sql.*;
import java.util.Random;

public class BMobileNumber {

    // Database Configuration
    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;databaseName=ABQAIQDB_QA;encrypt=false";

    private static final String USER = "Testing_Team";
    private static final String PASSWORD = "Testing@194";

    private final Random random = new Random();

    /**
     * Generate a random mobile number based on city
     */
    public String generateRandomMobileNumber(String city) {

        String cityNormalized = city.trim().toLowerCase();

        String prefix;
        int totalLength;

        switch (cityNormalized) {

            // Saudi Arabia
            case "bqaiq":
            case "al-qatif":
            case "sakaka":
            case "riyadh":
            case "jeddah":
                prefix = "05";
                totalLength = 10;
                break;

            // Egypt
            case "asyut":
                prefix = "01";
                totalLength = 11;
                break;

            // India
            case "chennai":
            case "vellore":
                int[] startDigits = {6, 7, 8, 9};
                prefix = String.valueOf(startDigits[random.nextInt(startDigits.length)]);
                totalLength = 10;
                break;

            default:
                throw new IllegalArgumentException("Unsupported city : " + city);
        }

        StringBuilder mobile = new StringBuilder(prefix);

        while (mobile.length() < totalLength) {
            mobile.append(random.nextInt(10));
        }

        return mobile.toString();
    }

    /**
     * Check whether mobile number already exists
     */
    public boolean mobileExists(String mobile) {

        String sql = "SELECT COUNT(*) FROM Users WHERE Phone = ?";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, mobile);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Generate unique mobile number
     */
    public String getUniqueMobileNumber(String city) {

        String mobile;

        do {

            mobile = generateRandomMobileNumber(city);

            ExtentTestListener.logStep("Checking Mobile Number : " + mobile);

        } while (mobileExists(mobile));

        return mobile;
    }

    /**
     * Get OTP from database
     */
    public String getOTP(String mobile) {

        String sql = "SELECT OTP FROM otpverification WHERE phno = ?";

        String otp = null;

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, mobile);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                otp = rs.getString("OTP");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return otp;
    }
}