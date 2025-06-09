package spse.stefacek.exceptions;

import spse.stefacek.exceptions.templates.AbstractException;
import spse.stefacek.exceptions.templates.ErrorSeverity;

public class DatabaseException extends AbstractException {
  public DatabaseException(String message, Throwable cause, ErrorSeverity severity) {
    super(message, cause, severity);
  }
}
