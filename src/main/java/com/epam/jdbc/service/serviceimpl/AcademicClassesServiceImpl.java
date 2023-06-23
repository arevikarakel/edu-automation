package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.service.AcademicClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcademicClassesServiceImpl implements AcademicClassesService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int findIDByAcademicClass(String academicClass) {
        logger.info("Find ID of the given academic class.");
        int id = -1;
        String query = "SELECT id FROM public.academic_class WHERE class_number=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicClass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        logger.info("ID of {} academic class in the DB is: {}", academicClass, id);
        return id;
    }

    @Override
    public int findClassroomTeacherIDByAcademicClass(String academicClass) {
        logger.info("Find classroom teacher ID of the given academic class.");
        int id = -1;
        String query = "SELECT classroom_teacher_id FROM academic_class WHERE class_number =?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicClass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("classroom_teacher_id");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        logger.info("ID of classroom teacher in {} academic class in the DB is: {}", academicClass, id);
        return id;
    }

    @Override
    public int findStudentsCountByLinkedCourseId(int academicClassID) {
        logger.info("Find count of students linked to the academic class by the given class id.");
        int countOfStudents = 0;
        String query = "SELECT COUNT(id) as total FROM public.student WHERE academic_class_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, academicClassID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countOfStudents = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Count of students linked to academic class in the DB is {}", countOfStudents);
        return countOfStudents;
    }
}
