/* Copyright (c) 2012,2013, Oracle and/or its affiliates. All rights reserved.  */
package com.hpx.javaee.interceptor;

import javax.interceptor.Interceptor;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.hpx.javaee.service.AccountBean;

/**
 * Interceptor Class
 * 
 */
@OnDeposit
@Interceptor
public class LogInterceptor {
  
  @AroundInvoke
  public Object doLog(InvocationContext ctx) throws Exception {
    AccountBean account = (AccountBean)ctx.getTarget(); 
    System.out.println("Mr or Miss " + account.getName() + " has successfully deposited " + account.getAmount() + "!");    
    return ctx.proceed();
  }
}
