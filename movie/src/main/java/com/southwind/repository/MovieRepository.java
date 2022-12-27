package com.southwind.repository;

import com.southwind.entity.Movie;

import java.util.List;

public interface MovieRepository {
    //实现基本的增删改查
    public List<Movie> findAll(int index,int limit);
    public Movie findById(long id);
    public void save(Movie Movie);
    public void update(Movie Movie);
    public void deleteById(long id);
    public int count();
}
