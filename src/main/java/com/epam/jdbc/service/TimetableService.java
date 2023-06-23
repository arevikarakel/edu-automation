package com.epam.jdbc.service;

public interface TimetableService {

    int findCountOfCoursesAddedToTimetableByAcademicClassId(int academicClassId);

    void deleteTimetableByAcademicClassId(int academicClassId);
}
