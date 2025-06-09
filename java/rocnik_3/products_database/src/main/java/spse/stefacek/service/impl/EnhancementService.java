package spse.stefacek.service.impl;

import java.math.BigDecimal;
import java.util.List;

import spse.stefacek.data.dao.impl.EnhancementDaoJDBC;
import spse.stefacek.data.dao.interfaces.EnhancementDao;
import spse.stefacek.data.model.Enhancement;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.ServiceException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.service.interfaces.ServiceInterface;

public class EnhancementService implements ServiceInterface<Enhancement> {
  private final EnhancementDao enhancementDao;

  public EnhancementService() {
    this.enhancementDao = new EnhancementDaoJDBC();
  }

  @Override
  public List<Enhancement> getAll() throws DatabaseException {
    return enhancementDao.getAll();
  }

  @Override
  public void deleteItem(Enhancement item) throws DatabaseException, ServiceException {
    if (enhancementDao.delete(item.getId()) == 0)
      throw new ServiceException("Vybraná položka nebyla nalezena.", new Throwable(), ErrorSeverity.ERROR);
  }

  @Override
  public Enhancement save(Enhancement item) throws DatabaseException, ServiceException {
    validateEnhancement(item);

    Enhancement newItem = enhancementDao.save(item);

    if (newItem == null)
      throw new ServiceException("Uložení položky se nezdařilo.", new Throwable(), ErrorSeverity.ERROR);

    return newItem;
  }

  private boolean validateEnhancement(Enhancement enhancement) throws ServiceException {
    if (enhancement.getName() == null || enhancement.getName().isEmpty()) {
      throw new ServiceException("Název produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
    }
    if (enhancement.getDescription() == null || enhancement.getDescription().isEmpty()) {
      throw new ServiceException("Popis produktu nesmí být prázdný.", new Throwable(), ErrorSeverity.ERROR);
    }
    if (enhancement.getPrice().compareTo(BigDecimal.ZERO) < 0) {
      throw new ServiceException("Cena produktu musí být kladná.", new Throwable(), ErrorSeverity.ERROR);
    }
    return true;
  }

}
