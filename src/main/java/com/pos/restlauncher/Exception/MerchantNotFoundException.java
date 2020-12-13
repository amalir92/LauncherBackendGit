package com.pos.restlauncher.Exception;

public class MerchantNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MerchantNotFoundException(String id) {
      super("Could not find merchant " + id);
    }
  }
