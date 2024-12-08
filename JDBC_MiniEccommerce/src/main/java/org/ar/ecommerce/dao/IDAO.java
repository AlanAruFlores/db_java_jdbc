package org.ar.ecommerce.dao;

import java.util.List;

public interface IDAO <T>{
    public void insert(T object);
    public void update(T object);
    public void delete(T object);
    public T findById(int id);
    public List<T> findAll();
}
