package com.southwind.repository;

import com.southwind.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll(int index, int limit);
    public int count();
    public void save(User user);
    public void deleteById(long id);
    public void update(User user);
    public void update_password(User user);
    public User findById(long id);
    public User findAdminById(long id);
}
