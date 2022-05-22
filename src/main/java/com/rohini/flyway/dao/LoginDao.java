package com.rohini.flyway.dao;

import com.rohini.flyway.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
public class LoginDao extends DBBase {
    public User doLogin(final String email, final String pwd) {
        try {
            User user = null;

            final String sql = "SELECT userId, firstName, lastName, email, userType " + " FROM users"
                + " WHERE email=? AND password=?";

            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pwd);

            System.out.println(stmt.toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setUserType(rs.getInt("userType"));
            }

            return user;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
