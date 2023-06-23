package com.epam.jdbc.service;

public interface AcademicClassesService {

    int findIDByAcademicClass(String academicClass);

    int findClassroomTeacherIDByAcademicClass(String academicClass);

    int findStudentsCountByLinkedCourseId(int academicClassID);
}
