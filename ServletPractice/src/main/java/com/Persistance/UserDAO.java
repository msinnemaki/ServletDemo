package com.Persistance;

import com.Utils.ConnectionManager;
import com.Utils.CurrentUser;
import com.Utils.CustomCRUDInterface;
import com.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO implements CustomCRUDInterface<User>
{
    Connection connection;

    public UserDAO()
    {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Integer create(User user)
    {
        try
        {
            String sql = "INSERT INTO users (user_id, first_name, last_name, email) VALUES (default,?,?,?) ";
            PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, user.getFirst_name());
            pstat.setString(2, user.getLast_name());
            pstat.setString(3, user.getEmail());

            pstat.executeUpdate();

            ResultSet rs = pstat.getGeneratedKeys();

            //the cursor is initially placed before the first element in the Result Set
            //You need to advance it once to access the first element
            rs.next();

            return rs.getInt(1);

        }
        catch (Exception e)
        {
            System.out.println();
        }
        return null;
    }

    @Override
    public User read(Integer id)
    {
        try
        {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);

            pstat.setInt(1,id);

            ResultSet rs = pstat.executeQuery();
            User user = new User();
            while (rs.next())
            {
                user.setUser_id(rs.getInt("user_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        }

        catch (Exception e)
        {
            System.out.println("This is the UserDao" + e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
    public CurrentUser loginUser(String email, String password) {

        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setString(1, email);

            ResultSet rs = pstat.executeQuery();

            if (rs.next() && rs.getString("last_name").equals(password)) {
                return new CurrentUser(rs.getInt("user_ID"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("This is the userDAO" + e.getMessage());
        }
        return CurrentUser.currentUser;
    }
}

