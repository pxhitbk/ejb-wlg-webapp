/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 *  
 */
package com.hpx.javaee.service;

import com.hpx.javaee.entity.Account;

/**
 * EJB Business Interface
 */
public interface AccountManager {

  public void depositOnAccount(String name, float amount);
  
  public Account findAccount(String name);
  
}
