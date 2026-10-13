package com.AndroidTest.ASmartSortRegistrationFlow;

import Base.ExtentTestListener;

import java.sql.*;
import java.util.Random;

public class BMobileNumber {

    // =========================================================
    // DATABASE CONFIGURATION
    // =========================================================

    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;databaseName=ABQAIQDB_QA;encrypt=false";

    private static final String USER = "Testing_Team";

    private static final String PASSWORD = "Testing@194";

    private final Random random = new Random();


    // =========================================================
    // GENERATE RANDOM MOBILE NUMBER BASED ON CITY
    // =========================================================

    public String generateRandomMobileNumber(String city) {

        String cityNormalized =
                city.trim().toLowerCase();

        String prefix;
        int totalLength;

        switch (cityNormalized) {

            // =================================================
            // SAUDI ARABIA
            // =================================================

            case "bqaiq":
            case "al-qatif":
            case "sakaka":
            case "riyadh":
            case "jeddah":

                prefix = "05";
                totalLength = 10;

                break;


            // =================================================
            // EGYPT
            // =================================================

            case "asyut":

                prefix = "01";
                totalLength = 11;

                break;


            // =================================================
            // INDIA
            // =================================================

            case "chennai":
            case "vellore":

                int[] startDigits = {
                        6, 7, 8, 9
                };

                prefix = String.valueOf(
                        startDigits[
                                random.nextInt(
                                        startDigits.length
                                )
                                ]
                );

                totalLength = 10;

                break;


            // =================================================
            // UNSUPPORTED CITY
            // =================================================

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
    // CHECK WHETHER MOBILE NUMBER EXISTS IN DATABASE
    // =========================================================

    public boolean mobileExists(String mobile) {

        String sql =
                "SELECT COUNT(*) FROM Users WHERE Phone = ?";


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
    // GENERATE UNIQUE MOBILE NUMBER
    // =========================================================

    public String getUniqueMobileNumber(String city) {

        String mobile;


        do {

            mobile =
                    generateRandomMobileNumber(city);


            ExtentTestListener.logStep(
                    "Checking mobile : " + mobile
            );


        } while (mobileExists(mobile));


        return mobile;
    }


    // =========================================================
    // GENERATE RANDOM FIRST NAME
    // =========================================================

    public String generateRandomFirstName() {

        String[] firstNames = {

                "Nirmal",
                "Arun",
                "Karthik",
                "Rahul",
                "Vijay",
                "Ajay",
                "Prakash",
                "Suresh",
                "Rakesh",
                "Manoj"

        };


        return firstNames[
                random.nextInt(
                        firstNames.length
                )
                ];
    }


    // =========================================================
    // GENERATE RANDOM LAST NAME
    // =========================================================

    public String generateRandomLastName() {

        String[] lastNames = {

                "Kumar",
                "Raj",
                "Sharma",
                "Reddy",
                "Patel",
                "Singh",
                "Verma",
                "Das",
                "Nair",
                "Menon"

        };


        return lastNames[
                random.nextInt(
                        lastNames.length
                )
                ];
    }


    // =========================================================
    // GENERATE EMAIL BASED ON FIRST NAME + LAST NAME
    // =========================================================

    public String generateEmail(
            String firstName,
            String lastName) {


        String first =
                firstName
                        .trim()
                        .toLowerCase()
                        .replaceAll(
                                "[^a-z]",
                                ""
                        );


        String last =
                lastName
                        .trim()
                        .toLowerCase()
                        .replaceAll(
                                "[^a-z]",
                                ""
                        );


        // Generate 5-digit random number
        int randomNumber =
                10000 + random.nextInt(90000);


        return first
                + "."
                + last
                + randomNumber
                + "@gmail.com";
    }


    // =========================================================
    // GET OTP FROM DATABASE
    // =========================================================

    public String getOTP(String mobile) {

        String sql =
                "SELECT OTP FROM otpverification WHERE phno = ?";


        String otp = null;


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

                otp =
                        rs.getString("OTP");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }


        return otp;
    }
}