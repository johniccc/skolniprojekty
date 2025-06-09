package spse.stefacek.service.impl;

import java.math.BigDecimal;
import java.util.List;

import spse.stefacek.data.dao.impl.ProductDaoJDBC;
import spse.stefacek.data.dao.interfaces.ProductDao;
import spse.stefacek.data.model.Product;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.ServiceException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.service.interfaces.ServiceInterface;

public class ProductService implements ServiceInterface<Product> {
  private final ProductDao productDao;

  public ProductService() {
    this.productDao = new ProductDaoJDBC();
  }

  @Override
  public List<Product> getAll() throws DatabaseException {
    return productDao.getAll();
  }

  @Override
  public void deleteItem(Product product) throws DatabaseException, ServiceException {
    if (productDao.delete(product.getId()) == 0)
      throw new ServiceException("Vybraná položka nebyla nalezena.", new Throwable(), ErrorSeverity.ERROR);
  }

  @Override
  public Product save(Product item) throws DatabaseException, ServiceException {
    validateProduct(item);

    Product newItem = productDao.save(item);

    if (newItem == null)
      throw new ServiceException("Uložení položky se nezdařilo.", new Throwable(), ErrorSeverity.ERROR);

    return newItem;
  }

  private boolean validateProduct(Product product) throws ServiceException {
    if (product.getName() == null || product.getName().isEmpty()) {
      throw new ServiceException("Název produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
    }
    if (product.getDescription() == null || product.getDescription().isEmpty()) {
      throw new ServiceException("Popis produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
    }
    if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
      throw new ServiceException("Cena produktu musí být kladná.", new Throwable(), ErrorSeverity.ERROR);
    }
    return true;
  }
}
