package com.southwind.entity;

import com.mysql.cj.xdevapi.TableImpl;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Order {
    private long id;
    private User user;
    private Movie movie;
    private Timestamp addtime;
    private int status;
    private int address;
}
