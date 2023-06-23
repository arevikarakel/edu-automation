package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Parent;
import com.epam.jdbc.model.User;
import com.epam.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService<User> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findUserByEmail(String email) {
        logger.info("Find user by {} email", email);
        User user = new User();
        String query = "SELECT * " +
                "FROM public.\"user_table\"" +
                "WHERE email=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return user;
    }

    @Override
    public User findUserByID(int id) {
        logger.info("Find user by {} id", id);
        User user = new User();
        String query = "SELECT * FROM user_table WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return user;
    }

}
