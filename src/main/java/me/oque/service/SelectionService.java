package me.oque.service;

import me.oque.entity.DataObject;

import java.util.List;

/**
 * Created by Dmitry Smorzhok on 19.07.15.
 */
public interface SelectionService {

    /**
     * saves given object in database
     * @param object - object to save
     */
    public <T extends DataObject> void save(T object);

    /**
     * deletes given object in database
     * @param object - object to save
     */
    public <T extends DataObject> void delete(T object);

    /**
     * deletes object of given class by id
     * @param clazz - class of objects
     * @param id - id of object to delete
     */
    public <T extends DataObject> void deleteById(Class<T> clazz, Long id);

    /**
     * get all objects of type T from database
     * @param clazz - class of objects
     * @return all objects of given type T from db
     */
    public <T extends DataObject> List<T> getAll(Class<T> clazz);

}
