package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.service.YearsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

public class VacationServiceImpl implements YearsService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public int findIDByStartAndEndDays(LocalDate startDate, LocalDate endDate) {
        int id = -1;
        logger.info("Get ID of Vacation by start date and end date values.");
        String query = "SELECT id FROM public.vacation WHERE start_date=? and end_date=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Start date and/or end date is incorrect.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        logger.info("ID of vacation in the DB is: {}", id);
        return id;
    }
}
