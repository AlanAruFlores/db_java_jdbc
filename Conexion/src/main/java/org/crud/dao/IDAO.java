package org.crud.dao;

import java.util.*;

public interface IDAO<T> {

    public void insert(T row);
    public void update(T row);
    public void delete(T row);
    public T find(int id);
    public Collection<T> findAll();
}
