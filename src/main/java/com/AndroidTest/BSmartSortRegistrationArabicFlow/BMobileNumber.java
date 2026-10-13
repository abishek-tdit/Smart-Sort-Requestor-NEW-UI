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
                        6,
                        7,
                        8,
                        9
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

        }
        catch (Exception e) {

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
    // GENERATE RANDOM FIRST NAME
    // =========================================================

    public String generateRandomArabicFirstName() {

        String[] firstNames = {

                "محمد",
                "أحمد",
                "علي",
                "خالد",
                "سعيد",
                "عبدالله",
                "يوسف",
                "عمر",
                "حسن",
                "إبراهيم"

        };

        return firstNames[
                random.nextInt(
                        firstNames.length
                )
                ];
    }


// =========================================================
// GENERATE RANDOM ARABIC MIDDLE NAME
// =========================================================

    public String generateRandomArabicMiddleName() {

        String[] middleNames = {

                "محمد",
                "أحمد",
                "علي",
                "خالد",
                "سلمان",
                "عبدالرحمن",
                "يوسف",
                "عمر",
                "حسين",
                "ناصر"

        };

        return middleNames[
                random.nextInt(
                        middleNames.length
                )
                ];
    }


// =========================================================
// GENERATE RANDOM ARABIC LAST NAME
// =========================================================

    public String generateRandomArabicLastName() {

        String[] lastNames = {

                "العتيبي",
                "القحطاني",
                "الشمري",
                "الحربي",
                "المطيري",
                "الدوسري",
                "الغامدي",
                "الزهراني",
                "السبيعي",
                "العنزي"

        };

        return lastNames[
                random.nextInt(
                        lastNames.length
                )
                ];
    }


    // =========================================================
    // GENERATE EMAIL
    // BASED ON FIRST + MIDDLE + LAST NAME
    // =========================================================

    public String generateEmail(
            String firstName,
            String middleName,
            String lastName) {


        String first =
                firstName
                        .trim()
                        .toLowerCase()
                        .replaceAll(
                                "[^a-z]",
                                ""
                        );


        String middle =
                middleName
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
                10000
                        + random.nextInt(
                        90000
                );


        return first
                + "."
                + middle
                + "."
                + last
                + randomNumber
                + "@gmail.com";
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


        // =====================================================
        // 15 ATTEMPTS
        // 2 SECONDS BETWEEN EACH ATTEMPT
        // TOTAL = 30 SECONDS
        // =====================================================

        for (
                int attempt = 1;
                attempt <= 15;
                attempt++
        ) {

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

                ps.setString(
                        1,
                        mobile
                );


                ResultSet rs =
                        ps.executeQuery();


                if (rs.next()) {

                    String otp =
                            rs.getString("OTP");


                    if (
                            otp != null
                                    && !otp
                                    .trim()
                                    .isEmpty()
                    ) {

                        ExtentTestListener.logStep(
                                "OTP found for mobile : "
                                        + mobile
                        );


                        return otp;
                    }
                }

            }
            catch (SQLException e) {

                ExtentTestListener.logStep(
                        "OTP database check failed - Attempt "
                                + attempt
                );
            }


            // =================================================
            // POLLING WAIT
            // =================================================

            try {

                Thread.sleep(2000);

            }
            catch (InterruptedException e) {

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
