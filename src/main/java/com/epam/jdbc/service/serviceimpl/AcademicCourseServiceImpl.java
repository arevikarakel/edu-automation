package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.AcademicCourse;
import com.epam.jdbc.service.AcademicCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcademicCourseServiceImpl implements AcademicCourseService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public AcademicCourse findByName(String academicCourseName) {
        AcademicCourse academicCourse = new AcademicCourse();
        String query = "SELECT * " +
                "FROM public.\"academic_course\"" +
                "WHERE name=?;";
        logger.info("Find academic course by name");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicCourseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                academicCourse.setId(resultSet.getInt("id"));
                academicCourse.setName(resultSet.getString("name"));
                academicCourse.setSubjectId(resultSet.getInt("subject_id"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return academicCourse;
    }

    @Override
    public List<Integer> findAcademicCourseIdsByLinkedClassId(int academicClassId) {
        logger.info("Find IDs of academic courses linked to academic class with {} id.", academicClassId);
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT academic_course_id " +
                "FROM public.academic_class_academic_course_mapping " +
                "WHERE academic_class_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, academicClassId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("academic_course_id") != 0) {
                    ids.add(resultSet.getInt("academic_course_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("IDs count of academic courses linked to academic class with {} id in the DB is:" +
                "{}", academicClassId, ids.size());
        return ids;
    }

    @Override
    public int findTeachersCountByLinkedCourseId(int academicCourseId) {
        logger.info("Find count of teachers linked to the course by the given course id.");
        int countOfTeachers = 0;
        String query = "SELECT COUNT(teacher_id) as total FROM public.academic_course_teacher_mapping WHERE academic_course_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, academicCourseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countOfTeachers = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Count of teachers linked to academic course in the DB is {}", countOfTeachers);
        return countOfTeachers;
    }

    @Override
    public List<String> findCourseNamesByIds(List<Integer> academicCourseIds) {
        logger.info("Find academic courses names by the given list of course ids.");
        List<String> courseNames = new ArrayList<>();
        String query = "SELECT name FROM academic_course WHERE id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Integer courseId : academicCourseIds) {
                preparedStatement.setInt(1, courseId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    courseNames.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        return courseNames;
    }

    @Override
    public void insertIntoAcademicCourseTeachersByCourseID(int id) {
        logger.info("Insert into academic course a teacher.");
        String query = "INSERT INTO academic_course_teacher_mapping" +
                "(academic_course_id, teacher_id) VALUES(?, 1);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
    }

    @Override
    public int findCountOfAcademicCoursesInTHeDB() {
        logger.info("Find count of academic courses in the DB.");
        int countOfCourses = 0;
        String query = "SELECT COUNT(id) as total FROM academic_course;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countOfCourses = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("Count of courses in the DB is {}", countOfCourses);
        return countOfCourses;
    }

}
