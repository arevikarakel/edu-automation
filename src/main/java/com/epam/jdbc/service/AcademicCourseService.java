package com.epam.jdbc.service;

import com.epam.jdbc.model.AcademicCourse;

import java.util.List;

public interface AcademicCourseService {

    AcademicCourse findByName(String academicCourseName);
    List<Integer> findAcademicCourseIdsByLinkedClassId(int academicClassId);
    int findTeachersCountByLinkedCourseId(int subjectID);
    List<String> findCourseNamesByIds(List<Integer> academicCourseIds);
    void insertIntoAcademicCourseTeachersByCourseID(int id);
    int findCountOfAcademicCoursesInTHeDB();
}
