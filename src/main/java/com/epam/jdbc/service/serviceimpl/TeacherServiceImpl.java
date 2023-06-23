package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Admin;
import com.epam.jdbc.model.Teacher;
import com.epam.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements UserService<Teacher> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Override
    public Teacher findUserByEmail(String email) {
        logger.info("Find user by {} email", email);
        Teacher teacher = new Teacher();
        String query = "SELECT public.teacher.id, password, name, surname, user_id " +
                "FROM public.teacher " +
                "INNER JOIN public.user_table " +
                "ON public.teacher.user_id=public.user_table.id " +
                "WHERE public.user_table.email=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return teacher;
    }

    public List<String> findTeacherNameAndSurnameById(List<Integer> idList) {
        logger.info("Find teacher's names and surnames by their IDs using List of teacher's id and add them to list.");
        List<String> nameList = new ArrayList<>();
        String query = "SELECT name, surname FROM public.teacher WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Integer id : idList) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                nameList.add(String.format("%s %s", resultSet.getString("name"),
                        resultSet.getString("surname")));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return nameList;
    }

    @Override
    public Teacher findUserByID(int id) {
        logger.info("Find teacher by {} id", id);
        Teacher teacher = new Teacher();
        String query = "SELECT * FROM teacher INNER JOIN user_table on " +
                "teacher.user_id=user_table.id WHERE teacher.id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setUserId(resultSet.getInt("user_id"));
                teacher.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return teacher;
    }
}
