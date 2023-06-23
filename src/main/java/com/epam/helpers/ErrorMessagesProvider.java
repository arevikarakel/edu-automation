package com.epam.helpers;

import com.epam.config.PropertiesReader;

public final class ErrorMessagesProvider {

    private static final PropertiesReader PROPERTIES_READER = PropertiesReader
            .getInstance("error_messages.properties");

    private ErrorMessagesProvider() {
    }

    public static String getInvalidEmailErrMessage() {
        return PROPERTIES_READER.getProperty("invalidEmail");
    }

    public static String getMoreThan50SymbolsErrMessage() {
        return PROPERTIES_READER.getProperty("moreThan50Symbols");
    }

    public static String getNotAllowedSymbolsErrMessage() {
        return PROPERTIES_READER.getProperty("notAllowedSymbols");
    }

    public static String getBlankInputFieldsErrMessage() {
        return PROPERTIES_READER.getProperty("blankInputFields");
    }
    public static String getBlankSelectFieldsErrMessage() {
        return PROPERTIES_READER.getProperty("blankSelectFields");
    }

    public static String getIncorrectLoginOrPasswordErrMessage() {
        return PROPERTIES_READER.getProperty("incorrectLoginOrPassword");
    }

    public static String getExistedEmailErrMessage() {
        return PROPERTIES_READER.getProperty("existedEmailErrMsg");
    }

    public static String getExistedAcademicClassErrMessage() {
        return PROPERTIES_READER.getProperty("existedAcademicClass");
    }

    public static String getWrongSelectedDatesErrMessage() {
        return PROPERTIES_READER.getProperty("wrongSelectedDates");
    }

    public static String getLessThan30DaysErrMessage() {
        return PROPERTIES_READER.getProperty("lessThan30Days");
    }

    public static String getExistedSubjectNameErrMessage() {
        return PROPERTIES_READER.getProperty("existedSubjectName");
    }

    public static String getSelectionErrMessage() {
        return PROPERTIES_READER.getProperty("selectionError");
    }

    public static String getExistedAcademicCourseErrMessage() {
        return PROPERTIES_READER.getProperty("existedAcademicCourse");
    }

    public static String getErrMessageForNotSelectingCourses() {
        return PROPERTIES_READER.getProperty("noCoursesSelected");
    }

    public static String getErrMessageOfBlankDates() {
        return PROPERTIES_READER.getProperty("blankDates");
    }
}
