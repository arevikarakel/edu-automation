package com.epam.jdbc.service;

import java.time.LocalDate;

public interface YearsService {
    int findIDByStartAndEndDays(LocalDate startDate, LocalDate endDate);
}
