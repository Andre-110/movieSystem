package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUid(int index,int limit,long uid);
    public List<Order> findAllByMid(long mid);
    public int countByUid(long uid);
    public int countByMid(long mid);
    public int count();
//    public void deleteByMid(long mid);
//    public void deleteByUid(long uid);
//    public int countByState(int state);
//    public void updateState(long id);
    public List<Order> findAll(int index,int limit);

}


