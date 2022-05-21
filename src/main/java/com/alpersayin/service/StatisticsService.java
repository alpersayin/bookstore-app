package com.alpersayin.service;

import com.alpersayin.payload.response.StatisticResponse;

import java.util.List;

public interface StatisticsService {
    List<StatisticResponse> getMonthlyStatistics();
}
