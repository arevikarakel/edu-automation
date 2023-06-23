package com.epam.jdbc.service;

public interface UserService<T> {

    T findUserByEmail(String email);

    T findUserByID(int id);
}
