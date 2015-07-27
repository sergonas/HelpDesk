package me.oque.service;

import me.oque.dao.SelectionDao;
import me.oque.entity.DataObject;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Dmitry Smorzhok on 19.07.15.
 */
@Service("selectionService")
public class SelectionServiceImpl implements SelectionService {

    @Inject
    protected SelectionDao selectionDao;

    @Override
    public <T extends DataObject> void save(T object) {
        selectionDao.save(object);
    }

    @Override
    public <T extends DataObject> void delete(T object) {
        selectionDao.delete(object);
    }

    @Override
    public <T extends DataObject> void deleteById(Class<T> clazz, Long id) {
        selectionDao.deleteById(clazz, id);
    }

    @Override
    public <T extends DataObject> List<T> getAll(Class<T> clazz) {
        return selectionDao.getAll(clazz);
    }

    @Override
    public <T extends DataObject> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize) {
        return selectionDao.listObjectByPage(clazz, page, pageSize);
    }

    @Override
    public <T extends DataObject> long countAll(Class<T> clazz) {
        return selectionDao.countAll(clazz);
    }
}
