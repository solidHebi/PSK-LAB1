package com.psk_1.psk12.Exceptions;

public class CopyModifiedException extends RuntimeException {
  public CopyModifiedException() {
    super("Copy was modified by another user.");
  }
}
