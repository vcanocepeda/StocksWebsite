package com.isaacs.dao;
 
import java.util.List;
 
public interface GenericDao<E, K> {
 
    String save(E entity) throws Exception;
     
    String update(E entity) throws Exception;
     
    String delete(E entity);
     
    E find(K key);
     
    List<E> list() throws Exception;
     
}