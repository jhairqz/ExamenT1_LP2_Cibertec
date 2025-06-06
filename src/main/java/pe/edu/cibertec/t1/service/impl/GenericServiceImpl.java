package pe.edu.cibertec.t1.service.impl;
import pe.edu.cibertec.t1.dao.GenericDAO;
import pe.edu.cibertec.t1.service.GenericService;

import java.util.List;

public class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    protected GenericDAO<T, ID> dao;

    public GenericServiceImpl(GenericDAO<T, ID> dao) {
        this.dao = dao;
    }


    @Override
    public T getById(ID id) {
        return dao.findById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public void create(T entity) {
        dao.save(entity);
    }

    @Override
    public void modify(T entity) {
        dao.update(entity);
    }

    @Override
    public void remove(ID id) {
        dao.delete(id);
    }


    @Override
    public void save(T entity) {
        dao.save(entity);
    }

}