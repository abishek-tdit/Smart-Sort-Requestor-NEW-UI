package com.AndroidTest.BSmartSortRegistrationArabicFlow;

import Base.ExtentTestListener;

import java.sql.*;
import java.util.Random;

public class BMobileNumber {

    // =========================================================
    // DATABASE CONFIGURATION
    // =========================================================

    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;"
                    + "databaseName=ABQAIQDB_QA;"
                    + "encrypt=false";

    private static final String USER = "Testing_Team";
    private static final String PASSWORD = "Testing@194";

    private final Random random = new Random();

    // =========================================================
    // GENERATE RANDOM MOBILE
    // =========================================================

    public String generateRandomMobileNumber(String city) {

        String cityNormalized =
                city.trim().toLowerCase();

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

                int[] startDigits = {
                        6, 7, 8, 9
                };

                prefix =
                        String.valueOf(
                                startDigits[
                                        random.nextInt(
                                                startDigits.length
                                        )
                                        ]
                        );

                totalLength = 10;
                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported city : " + city
                );
        }

        StringBuilder mobile =
                new StringBuilder(prefix);

        while (mobile.length() < totalLength) {

            mobile.append(
                    random.nextInt(10)
            );
        }

        return mobile.toString();
    }

    // =========================================================
    // CHECK MOBILE EXISTS
    // =========================================================

    public boolean mobileExists(String mobile) {

        String sql =
                "SELECT COUNT(*) "
                        + "FROM Users "
                        + "WHERE Phone = ?";

        try (
                Connection con =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, mobile);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // GENERATE UNIQUE MOBILE
    // =========================================================

    public String getUniqueMobileNumber(String city) {

        String mobile;

        do {

            mobile =
                    generateRandomMobileNumber(city);

            ExtentTestListener.logStep(
                    "Checking Mobile Number : "
                            + mobile
            );

        } while (mobileExists(mobile));

        return mobile;
    }

    // =========================================================
    // GET OTP
    // =========================================================

    public String getOTP(String mobile) {

        String sql =
                "SELECT TOP 1 OTP "
                        + "FROM otpverification "
                        + "WHERE phno = ? "
                        + "ORDER BY 1 DESC";

        // 15 attempts x 2 seconds = 30 seconds
        for (int attempt = 1; attempt <= 15; attempt++) {

            try (
                    Connection con =
                            DriverManager.getConnection(
                                    URL,
                                    USER,
                                    PASSWORD
                            );

                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setString(1, mobile);

                ResultSet rs =
                        ps.executeQuery();

                if (rs.next()) {

                    String otp =
                            rs.getString("OTP");

                    if (otp != null
                            && !otp.trim().isEmpty()) {

                        ExtentTestListener.logStep(
                                "OTP found for mobile : "
                                        + mobile
                        );

                        return otp;
                    }
                }

            } catch (SQLException e) {

                ExtentTestListener.logStep(
                        "OTP database check failed - Attempt "
                                + attempt
                );
            }

            // Small polling wait
            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                throw new RuntimeException(
                        "OTP wait interrupted",
                        e
                );
            }
        }

        throw new RuntimeException(
                "OTP not found for mobile : "
                        + mobile
                        + " within 30 seconds"
        );
    }
}
