package com.phpimentel.hrpayroll.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double dailyIncome;
}
