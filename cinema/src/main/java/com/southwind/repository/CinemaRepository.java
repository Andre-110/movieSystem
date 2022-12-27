package com.southwind.repository;

import com.southwind.entity.Cinema;

import java.util.List;

public interface CinemaRepository {
    //实现基本的增删改查
    public List<Cinema> findAll(int index,int limit);
    public Cinema findById(long id);
    public void save(Cinema Cinema);
    public void update(Cinema Cinema);
    public void deleteById(long id);
    public int count();
}
