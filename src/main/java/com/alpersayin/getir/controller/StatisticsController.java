package com.alpersayin.getir.controller;

import com.alpersayin.getir.payload.response.StatisticResponse;
import com.alpersayin.getir.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/")
    public ResponseEntity<List<StatisticResponse>> getStatistic() {
        return new ResponseEntity<>(statisticsService.getMonthlyStatistics(), OK);
    }
}
