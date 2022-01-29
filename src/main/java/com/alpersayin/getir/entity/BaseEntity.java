package com.alpersayin.getir.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {
    private Integer id;

    private void setId(Integer id) {
        this.id = id;
    }
}
