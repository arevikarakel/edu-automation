package com.epam.helpers;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public final class SharedTestData {

    private static final Logger logger = LoggerFactory.getLogger(SharedTestData.class);
    private static String generatedPassword;
    private static String lastEmail;
    private static String nameField;
    private static String surnameField;
    private static String selectedValueOfPopup;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static String valueOfItem;
    private static String lastCreatedItemName;
    private static String lastInputtedTeacherName;
    private static int listSize;
    public static int resultOfMatchedSearch;
    private static int countLinkedToItem;
    private static int selectedItemsCountInTheBox;
    private static List<WebElement> listOfOptions;

    private SharedTestData() {
    }

    public static String getLastGeneratedPassword() {
        logger.info("Get last generated password {} from SharedTestData", generatedPassword);
        return generatedPassword;
    }

    public static void setLastGeneratedPassword(String password) {
        logger.info("Save value of last generated password {} into SharedTestData", password);
        generatedPassword = password;
    }

    public static String getLastEmail() {
        logger.info("Get last email {} from SharedTestData", lastEmail);
        return lastEmail;
    }

    public static void setLastEmail(String email) {
        logger.info("Save value of last email {} into SharedTestData", email);
        lastEmail = email;
    }

    public static String getNameField() {
        logger.info("Get name field {} from SharedTestData", nameField);
        return nameField;
    }

    public static void setNameField(String name) {
        logger.info("Save value of name fields {} into SharedTestData", name);
        nameField = name;
    }

    public static String getSurnameField() {
        logger.info("Get surname fields {} from SharedTestData", surnameField);
        return surnameField;
    }

    public static void setSurnameField(String surname) {
        logger.info("Save value of surname fields {} into SharedTestData", surname);
        surnameField = surname;
    }

    public static String getSelectedValueOfPopup() {
        logger.info("Get selected value of student popup field {} from SharedTestData", selectedValueOfPopup);
        return selectedValueOfPopup;
    }

    public static void setSelectedValueOfPopup(String selectedValue) {
        logger.info("Save selected value of student popup fields {} into SharedTestData", selectedValue);
        selectedValueOfPopup = selectedValue;
    }

    public static String getLastCreatedItemName() {
        logger.info("Get name of the created item name {}", lastCreatedItemName);
        return lastCreatedItemName;
    }

    public static void setLastCreatedItemName(String lastCreatedItemName) {
        logger.info("Save value of new created item name {} into SharedTestData", lastCreatedItemName);
        SharedTestData.lastCreatedItemName = lastCreatedItemName;
    }

    public static String getLastInputtedTeacherName() {
        return lastInputtedTeacherName;
    }

    public static void setLastInputtedTeacherName(String lastInputtedTeacherName) {
        SharedTestData.lastInputtedTeacherName = lastInputtedTeacherName;
    }


    public static LocalDate getStartDate() {
        return startDate;
    }

    public static void setStartDate(LocalDate startDate) {
        SharedTestData.startDate = startDate;
    }

    public static LocalDate getEndDate() {
        return endDate;
    }

    public static void setEndDate(LocalDate endDate) {
        SharedTestData.endDate = endDate;
    }

    public static String getValueOfItem() {
        return valueOfItem;
    }

    public static void setValueOfItem(String valueOfItem) {
        SharedTestData.valueOfItem = valueOfItem;
    }

    public static int getListSize() {
        return listSize;
    }

    public static void setListSize(int listSize) {
        SharedTestData.listSize = listSize;
    }

    public static int getResultOfMatchedSearch() {
        return resultOfMatchedSearch;
    }

    public static void setResultOfMatchedSearch(int resultOfMatchedSearch) {
        SharedTestData.resultOfMatchedSearch = resultOfMatchedSearch;
    }

    public static int getCountLinkedToItem() {
        return countLinkedToItem;
    }

    public static void setCountLinkedToItem(int countLinkedToItem) {
        SharedTestData.countLinkedToItem = countLinkedToItem;
    }

    public static int getSelectedItemsCountInTheBox() {
        return selectedItemsCountInTheBox;
    }

    public static void setSelectedItemsCountInTheBox(int selectedItemsCountInTheBox) {
        SharedTestData.selectedItemsCountInTheBox = selectedItemsCountInTheBox;
    }

    public static List<WebElement> getListOfOptions() {
        return listOfOptions;
    }

    public static void setListOfOptions(List<WebElement> listOfOptions) {
        SharedTestData.listOfOptions = listOfOptions;
    }
}
