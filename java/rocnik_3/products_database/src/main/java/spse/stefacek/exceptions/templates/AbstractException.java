package spse.stefacek.exceptions.templates;

abstract public class AbstractException extends Exception {
  private ErrorSeverity severity;

  public AbstractException(String message, Throwable cause, ErrorSeverity severity) {
    super(message, cause);
    this.severity = severity;
  }

  public ErrorSeverity getSeverity() {
    return this.severity;
  }

  public void setSeverity(ErrorSeverity severity) {
    this.severity = severity;
  }
}
