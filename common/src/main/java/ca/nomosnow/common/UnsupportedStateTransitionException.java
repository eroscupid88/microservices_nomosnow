package ca.nomosnow.common;

public class UnsupportedStateTransitionException extends RuntimeException {
  public UnsupportedStateTransitionException(Enum state) {
    super("current state: " + state);
  }
}
