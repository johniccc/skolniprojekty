package spse.stefacek.service.impl;

import java.util.List;

import spse.stefacek.data.dao.impl.CategoryDaoJDBC;
import spse.stefacek.data.dao.interfaces.CategoryDao;
import spse.stefacek.data.model.Category;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.ServiceException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.service.interfaces.ServiceInterface;

public class CategoryService implements ServiceInterface<Category> {
    private final CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDaoJDBC();
    }

    @Override
    public List<Category> getAll() throws DatabaseException {
        return categoryDao.getAll();
    }

    @Override
    public void deleteItem(Category item) throws DatabaseException, ServiceException {
        if (categoryDao.delete(item.getId()) == 0)
            throw new ServiceException("Vybraná položka nebyla nalezena.", new Throwable(), ErrorSeverity.ERROR);
    }

    @Override
    public Category save(Category item) throws DatabaseException, ServiceException {
        validateCategory(item);

        Category newItem = categoryDao.save(item);
        if (newItem == null)
            throw new ServiceException("Uložení položky se nezdařilo.", new Throwable(), ErrorSeverity.ERROR);

        return newItem;
    }

    private boolean validateCategory(Category category) throws ServiceException {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new ServiceException("Název produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new ServiceException("Popis produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
        }
        return true;
    }
}
