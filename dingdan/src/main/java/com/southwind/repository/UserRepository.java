package com.southwind.repository;

import com.southwind.entity.User;

public interface UserRepository {
    public User findById(long id);
}
