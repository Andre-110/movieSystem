package com.southwind.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private long id;
    private User user;
    private Movie movie;
    private Timestamp addtime;
    private int status;
    private int address;
}
