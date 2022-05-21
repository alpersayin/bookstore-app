package com.alpersayin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse {
    private Integer month;
    private Integer totalOrder;
    private Integer totalBook;
    private Integer totalAmount;
}
