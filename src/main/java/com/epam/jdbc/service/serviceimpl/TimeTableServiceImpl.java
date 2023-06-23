package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.service.TimetableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeTableServiceImpl implements TimetableService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(AcademicYearsServiceImpl.class);
    @Override
    public int findCountOfCoursesAddedToTimetableByAcademicClassId(int academicClassId) {
        logger.info("Find count of courses added to timetable for academic class with {} id, " +
                "when timetable is being created.", academicClassId);
        int countOfCourses = 0;
        String query = "SELECT COUNT(id) as total " +
                "FROM courses_table " +
                "WHERE academic_class_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, academicClassId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countOfCourses = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Count of courses added to timetable for academic class with {} id," +
                "when timetable is being created in the DB is {}",academicClassId, countOfCourses);
        return countOfCourses;
    }

    @Override
    public void deleteTimetableByAcademicClassId(int academicClassId) {
        logger.info("Delete timetable by academic class id {}", academicClassId);
        String query = "DELETE FROM public.timetable_table WHERE academic_class_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, academicClassId);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Timetable was deleted successfully");
    }
}
