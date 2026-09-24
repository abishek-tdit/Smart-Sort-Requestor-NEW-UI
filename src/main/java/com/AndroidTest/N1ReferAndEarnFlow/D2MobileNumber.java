package com.AndroidTest.N1ReferAndEarnFlow;

import Base.ExtentTestListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class D2MobileNumber {

    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;databaseName=ABQAIQDB_QA;encrypt=false";

    private static final String USER = "Testing_Team";
    private static final String PASSWORD = "Testing@194";

    private final Random random = new Random();

    // Stores the mobile generated during Sign Up
    public static String generatedMobileNumber;


    // ================= GENERATE MOBILE =================
    public String generateRandomMobileNumber(String city) {

        String cityNormalized = city.trim().toLowerCase();

        String prefix;
        int totalLength;

        switch (cityNormalized) {

            case "bqaiq":
            case "al-qatif":
            case "sakaka":
            case "riyadh":
            case "jeddah":
                prefix = "05";
                totalLength = 10;
                break;

            case "asyut":
                prefix = "01";
                totalLength = 11;
                break;

            case "chennai":
            case "vellore":
                int[] startDigits = {6, 7, 8, 9};
                prefix = String.valueOf(
                        startDigits[random.nextInt(startDigits.length)]
                );
                totalLength = 10;
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported city : " + city
                );
        }

        StringBuilder mobile = new StringBuilder(prefix);

        while (mobile.length() < totalLength) {
            mobile.append(random.nextInt(10));
        }

        return mobile.toString();
    }


    // ================= CHECK MOBILE =================
    public boolean mobileExists(String mobile) {

        String sql = "SELECT COUNT(*) FROM Users WHERE Phone = ?";

        try (
                Connection con = DriverManager.getConnection(
                        URL, USER, PASSWORD
                );

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


    // ================= GET UNIQUE MOBILE =================
    public String getUniqueMobileNumber(String city) {

        String mobile;

        do {
            mobile = generateRandomMobileNumber(city);
        } while (mobileExists(mobile));

        generatedMobileNumber = mobile;

        ExtentTestListener.logStep(
                "Generated Mobile Number : " + generatedMobileNumber
        );

        return generatedMobileNumber;
    }


    // ================= GET OTP =================
    public String getOTP(String mobile) {

        String sql =
                "SELECT OTP FROM otpverification WHERE phno = ?";

        try (
                Connection con = DriverManager.getConnection(
                        URL, USER, PASSWORD
                );

                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, mobile);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("OTP");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}