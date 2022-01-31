package com.alpersayin.getir.service;

import com.alpersayin.getir.payload.response.StatisticResponse;

import java.util.List;

public interface StatisticsService {
    List<StatisticResponse> getMonthlyStatistics();
}
