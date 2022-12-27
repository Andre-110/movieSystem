package com.southwind.repository;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;

import java.util.List;

public interface MenuRepository {
    //实现基本的增删改查
    public List<Menu> findAll(int index, int limit);
    public Menu findById(long id);
    public void save(Menu menu);
    public void update(Menu menu);
    public void deleteById(long id);
    public int count();
}
