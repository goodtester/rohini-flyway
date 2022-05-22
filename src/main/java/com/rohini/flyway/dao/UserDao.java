package com.rohini.flyway.dao;

import com.rohini.flyway.models.User;

import java.sql.PreparedStatement;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
public class UserDao extends DBBase {
    public boolean saveUser(final User user) {

        final String sql = "INSERT INTO users(firstName, lastName, email, password, phone, address, userType, active) "
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getAddress());
            stmt.setInt(7, user.getUserType());
            stmt.setBoolean(8, user.isActive());

            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public boolean changePassword(final String newPassword, final Long userId) {

        final String sql = "UPDATE users SET password = ? WHERE userId = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setString(1, newPassword);
            stmt.setLong(2, userId);

            int count = stmt.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
}
