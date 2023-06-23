package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Subject;
import com.epam.jdbc.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectServiceImpl implements SubjectService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public Subject findByName(String name) {
        Subject subject = new Subject();
        String query = "select * from subject where name=?";
        logger.info("Find subject by {} name", name);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return subject;
    }

    @Override
    public int findSubjectIdBySubjectName(String subjectName) {
        logger.info("Find ID of the given subject.");
        int id = -1;
        String query = "SELECT id FROM public.subject WHERE public.subject.name=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("ID of {} subject in the DB is: {}", subjectName, id);
        return id;
    }

    @Override
    public int findTeachersCountByConnectedSubjectId(int subjectID) {
        logger.info("Find count of teachers linked to the subject by the given subject id.");
        int countOfTeachers = 0;
        String query = "SELECT COUNT(teacher_id) as total FROM public.subject_teacher_mapping WHERE subject_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countOfTeachers = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Count of teachers linked to subject in the DB is {}", countOfTeachers);
        return countOfTeachers;
    }

    public List<Integer> findTeachersIdByConnectedSubjectId(int subjectID) {
        logger.info("Find teachers linked to the subject by the given subject id.");
        List<Integer> idList = new ArrayList<>();
        String query = "SELECT teacher_id FROM public.subject_teacher_mapping WHERE subject_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("teacher_id") != 0) {
                   idList.add(resultSet.getInt("teacher_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        return idList;
    }
}
