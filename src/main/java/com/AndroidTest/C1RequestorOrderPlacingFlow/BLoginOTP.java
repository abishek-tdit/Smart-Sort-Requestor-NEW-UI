package com.AndroidTest.C1RequestorOrderPlacingFlow;

import Base.ExtentTestListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BLoginOTP {

    // =========================================================
    // DATABASE CONFIGURATION
    // =========================================================

    private static final String URL =
            "jdbc:sqlserver://10.0.2.52:1433;"
                    + "databaseName=ABQAIQDB_QA;"
                    + "encrypt=false";

    private static final String USER =
            "Testing_Team";

    private static final String PASSWORD =
            "Testing@194";


    // =========================================================
    // GET OTP
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

                otp = rs.getString("OTP");
            }

        } catch (Exception e) {

            e.printStackTrace();

            ExtentTestListener.logStep(
                    "Failed to retrieve OTP : "
                            + e.getMessage()
            );
        }

        return otp;
    }
}