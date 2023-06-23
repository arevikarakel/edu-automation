package com.epam.helpers;

import com.epam.config.PropertiesReader;

public final class UserDataProvider {

    private static final PropertiesReader PROPERTIES_READER = PropertiesReader
            .getInstance("user_data.properties");

    private UserDataProvider() {
    }

    public static String getSuperAdminEmail() {
        return PROPERTIES_READER.getProperty("superAdminEmail");
    }

    public static String getSuperAdminPassword() {
        return PROPERTIES_READER.getProperty("superAdminPassword");
    }

    public static String getAdminEmail() {
        return PROPERTIES_READER.getProperty("adminEmail");
    }

    public static String getAdminPassword() {
        return PROPERTIES_READER.getProperty("adminPassword");
    }

    public static String getExistedEmail() {
        return PROPERTIES_READER.getProperty("existedEmail");
    }

    public static String getValidName() {
        return PROPERTIES_READER.getProperty("validName");
    }

    public static String getValidSurname() {
        return PROPERTIES_READER.getProperty("validSurname");
    }

    public static String getAddress() {
        return PROPERTIES_READER.getProperty("address");
    }

    public static String getTeacherSearchLinePlaceholder(String itemNAme) {
        return PROPERTIES_READER.getProperty("searchLinePlaceholder") + " " + itemNAme;
    }
    public static String getExistedClass() {
        return PROPERTIES_READER.getProperty("existedClass");
    }

    public static String getExistedAcademicCourse() {
        return PROPERTIES_READER.getProperty("existedAcademicCourse");
    }

    public static String getExistedSubject() {
        return PROPERTIES_READER.getProperty("existedSubject");
    }

    public static String getExistedName() {
        return PROPERTIES_READER.getProperty("existedName");
    }

    public static String getExistedSurname() {
        return PROPERTIES_READER.getProperty("existedSurname");
    }
}
