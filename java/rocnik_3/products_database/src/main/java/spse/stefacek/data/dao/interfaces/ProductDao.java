package spse.stefacek.data.dao.interfaces;

import java.util.List;

import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.data.model.Category;
import spse.stefacek.data.model.Enhancement;
import spse.stefacek.data.model.Product;

public interface ProductDao extends BaseDao<Product> {
  int addEnhancement(int productId, int enhancementId) throws DatabaseException;

  int addCategory(int productId, int categoryId) throws DatabaseException;

  List<Enhancement> getEnhancements(int productId) throws DatabaseException;

  List<Category> getCategories(int productId) throws DatabaseException;

  int removeEnhancement(int productId, int enhancementId) throws DatabaseException;

  int removeCategory(int productId, int categoryId) throws DatabaseException;
}