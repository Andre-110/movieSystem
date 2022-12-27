package com.southwind.repository;

import com.southwind.entity.Movie;

import java.util.List;

public interface MovieRepository {
    //实现基本的增删改查
    public Movie findById(long id);
}
