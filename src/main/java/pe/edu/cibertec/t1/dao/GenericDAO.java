package pe.edu.cibertec.t1.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(ID id);
}