package com.epam.helpers;

import com.epam.jdbc.model.Parent;
import com.epam.jdbc.model.Student;
import com.epam.jdbc.model.Teacher;
import com.epam.jdbc.service.serviceimpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public final class DBHelper {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final AdminServiceImpl adminService = new AdminServiceImpl();
    private final TeacherServiceImpl teacherService = new TeacherServiceImpl();
    private final StudentServiceImpl studentService = new StudentServiceImpl();
    private final ParentServiceImpl parentService = new ParentServiceImpl();
    private final VacationServiceImpl vacationService = new VacationServiceImpl();
    private final AcademicClassesServiceImpl classesServiceImpl = new AcademicClassesServiceImpl();
    private final AcademicYearsServiceImpl academicYearsService = new AcademicYearsServiceImpl();
    private final SubjectServiceImpl subjectService = new SubjectServiceImpl();
    private final TimeTableServiceImpl timeTableService = new TimeTableServiceImpl();
    private final AcademicCourseServiceImpl academicCourseService = new AcademicCourseServiceImpl();
    private final Logger logger = LoggerFactory.getLogger(DBHelper.class);


    public boolean isUserAddedInTheDB() {
        return userService.findUserByEmail(SharedTestData.getLastEmail()).getEmail() == null;
    }

    public Teacher getTeacherByID(int id) {
        return teacherService.findUserByID(id);
    }

    public Parent getParentByID(int id) {
        return parentService.findUserByID(id);
    }

    public Student getStudentByID(int id) {
        return studentService.findUserByID(id);
    }
    public boolean isSubjectAddedInTheDB() {
        return subjectService.findByName(SharedTestData.getLastCreatedItemName()).getName() == null;
    }

    public boolean isAdminPasswordHashed() {
        logger.info("Check password is encrypted");
        return !adminService.findUserByEmail(SharedTestData.getLastEmail()).getPassword()
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isTeacherPasswordHashed() {
        logger.info("Check password is encrypted");
        return !teacherService.findUserByEmail(
                        SharedTestData.getLastEmail()).getPassword()
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isStudentPasswordHashed() {
        logger.info("Check password is encrypted");
        return !studentService.findUserByEmail(
                        SharedTestData.getLastEmail()).getPassword()
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isParentPasswordHashed() {
        logger.info("Check password is encrypted");
        return !parentService.findUserByEmail(
                        SharedTestData.getLastEmail()).getPassword()
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isVacationAddedToTheDB(LocalDate start, LocalDate end) {
        return vacationService.findIDByStartAndEndDays(start, end) != -1;
    }

    public boolean isAcademicYearAddedToTheDB(LocalDate start, LocalDate end) {
        logger.info("Get id of academic year from the DB.");
        return academicYearsService.findIDByStartAndEndDays(start, end) != -1;
    }

    public boolean isClassAddedToTheDB(String academicClass) {
        return classesServiceImpl.findIDByAcademicClass(academicClass) != -1;
    }

    public boolean isClassroomTeacherAddedToTheDB(String academicClass) {
        return classesServiceImpl.findClassroomTeacherIDByAcademicClass(academicClass) != 0;
    }

    public int findAcademicClassIdByName(String academicClass) {
        return classesServiceImpl.findIDByAcademicClass(academicClass);
    }

    public int findCountOfCoursesForTimetableByAcademicClassName(String academicClass) {
        return timeTableService.findCountOfCoursesAddedToTimetableByAcademicClassId(findAcademicClassIdByName(academicClass));
    }

    public void deleteTimetableByAcademicClassId(String academicClassName) {
        timeTableService.deleteTimetableByAcademicClassId(findAcademicClassIdByName(academicClassName));
    }

    public List<String> findTeachersNameAndSurnameByAcademicCourseName(String academicCourse) {
        logger.info("Find teachers name and surname by their IDs, using connected subject ID and academic course name");
        return teacherService.findTeacherNameAndSurnameById(
                subjectService.findTeachersIdByConnectedSubjectId(
                        academicCourseService.findByName(academicCourse)
                                .getSubjectId()
                )
        );
    }

    public List<String> findCourseNamesByIdsAndClassName(String academicClassName) {
        logger.info("find course names by given list of ids and academic class name");
        return academicCourseService.
                findCourseNamesByIds(
                        academicCourseService
                                .findAcademicCourseIdsByLinkedClassId(
                                        findAcademicClassIdByName(academicClassName)
                                )
                );
    }

    public boolean isAcademicClassAddedToAcademicCourse(String academicClassName) {
        return academicCourseService.findAcademicCourseIdsByLinkedClassId(findAcademicClassIdByName(academicClassName)).size() != 0;
    }
    public void insertIntoAcademicCourseTeachersByCourseID(int id) {
        academicCourseService.insertIntoAcademicCourseTeachersByCourseID(id);
    }

    public int countOfAcademicCoursesInTheDB() {
        return academicCourseService.findCountOfAcademicCoursesInTHeDB();
    }
    public int findCountOfTeachersAddedToTheSubject(String subjectName) {
        return subjectService.findTeachersCountByConnectedSubjectId(getSubjectID(subjectName));
    }

    public int findCountOfTeachersAddedToTheCourse(String courseName) {
        return academicCourseService.findTeachersCountByLinkedCourseId(getCourseID(courseName));
    }

    public int findCountOfStudentsAddedToTheClass(String className) {
        return classesServiceImpl.findStudentsCountByLinkedCourseId(findAcademicClassIdByName(className));
    }

    private int getSubjectID(String subjectName) {
        return subjectService.findSubjectIdBySubjectName(subjectName);
    }

    private int getCourseID(String courseName) {
        return academicCourseService.findByName(courseName).getId();
    }

    public boolean isAcademicCourseIsAddedInTheDB() {
        return academicCourseService.findByName(SharedTestData.getLastCreatedItemName()).getName() != null;
    }
}
