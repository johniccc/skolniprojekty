package spse.stefacek.exceptions;

import spse.stefacek.exceptions.templates.AbstractException;
import spse.stefacek.exceptions.templates.ErrorSeverity;

public class PresentationException extends AbstractException {
  public PresentationException(String message, Throwable cause, ErrorSeverity severity) {
    super(message, cause, severity);
  }
}
