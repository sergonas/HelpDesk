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
    <T extends DataObject> void save(T object);

    /**
     * deletes given object in database
     * @param object - object to save
     */
    <T extends DataObject> void delete(T object);

    /**
     * deletes object of given class by id
     * @param clazz - class of objects
     * @param id - id of object to delete
     */
    <T extends DataObject> void deleteById(Class<T> clazz, Long id);

    /**
     * get all objects of type T from database
     * @param clazz - class of objects
     * @return all objects of given type T from db
     */
    <T extends DataObject> List<T> getAll(Class<T> clazz);

    /**
     * gets objects from db for pagination
     * @param clazz - class of object
     * @param page - number of page
     * @param pageSize - size of single page
     * @return list of objects for given page
     */
    <T extends DataObject> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize);

    /**
     * counts total number of records in database
     * @param clazz - class of object to count
     * @return amount of records
     */
    <T extends DataObject> long countAll(Class<T> clazz);

}
