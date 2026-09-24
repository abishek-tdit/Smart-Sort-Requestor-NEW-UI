package com.AndroidTest.N1ReferAndEarnFlow;

import Base.ExtentTestListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class E2LoginOTP {

    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;" +
                    "databaseName=ABQAIQDB_QA;" +
                    "encrypt=false";

    private static final String USER =
            "Testing_Team";

    private static final String PASSWORD =
            "Testing@194";

    // =====================================================
    // GET LATEST OTP ID BEFORE LOGIN
    // =====================================================

    public long getLatestOtpId(String mobile) {

        String sql =
                "SELECT ISNULL(MAX(id), 0) AS latestId " +
                        "FROM otpverification " +
                        "WHERE phno = ?";

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

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    long latestId =
                            rs.getLong("latestId");

                    System.out.println(
                            "OTP baseline ID before login : "
                                    + latestId
                    );

                    ExtentTestListener.logStep(
                            "OTP baseline ID captured : "
                                    + latestId
                    );

                    return latestId;
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to get OTP baseline ID for mobile : "
                            + mobile,
                    e
            );
        }

        throw new RuntimeException(
                "Could not get OTP baseline ID for mobile : "
                        + mobile
        );
    }

    // =====================================================
    // GET NEW LOGIN OTP
    // =====================================================

    public String getLoginOTP(
            String mobile,
            long previousOtpId
    ) {

        String sql =
                "SELECT TOP 1 OTP " +
                        "FROM otpverification " +
                        "WHERE phno = ? " +
                        "AND id > ? " +
                        "AND OTP IS NOT NULL " +
                        "AND LTRIM(RTRIM(OTP)) <> '' " +
                        "ORDER BY id DESC";

        // Poll maximum 60 seconds
        for (int i = 1; i <= 60; i++) {

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
                ps.setLong(2, previousOtpId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        String otp =
                                rs.getString("OTP");

                        if (otp != null &&
                                !otp.trim().isEmpty()) {

                            otp = otp.trim();

                            System.out.println();
                            System.out.println(
                                    "====================================="
                            );
                            System.out.println(
                                    "NEW LOGIN OTP FOUND"
                            );
                            System.out.println(
                                    "MOBILE : " + mobile
                            );
                            System.out.println(
                                    "OTP    : " + otp
                            );
                            System.out.println(
                                    "====================================="
                            );
                            System.out.println();

                            ExtentTestListener.logStep(
                                    "New Login OTP retrieved : "
                                            + otp
                            );

                            return otp;
                        }
                    }
                }

            } catch (Exception e) {

                System.out.println(
                        "OTP DB Error : "
                                + e.getMessage()
                );
            }

            // Wait before next DB poll
            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                throw new RuntimeException(
                        "OTP polling interrupted",
                        e
                );
            }
        }

        throw new RuntimeException(
                "NEW LOGIN OTP NOT FOUND FOR MOBILE : "
                        + mobile
                        + " AFTER OTP ID : "
                        + previousOtpId
        );
    }
}