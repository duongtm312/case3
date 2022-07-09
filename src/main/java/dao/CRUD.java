package dao;

import java.util.List;

public interface CRUD<E> {
    public List<E> getAll();
    public boolean create(E e);
    public boolean edit(String id,E e);
    public boolean delete(String id);
    public E findById(String id);
}
