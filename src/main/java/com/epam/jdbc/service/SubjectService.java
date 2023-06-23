package com.epam.jdbc.service;

import com.epam.jdbc.model.Subject;

public interface SubjectService {

    Subject findByName(String name);

    int findTeachersCountByConnectedSubjectId(int subjectID);

    int findSubjectIdBySubjectName(String subjectName);
}
