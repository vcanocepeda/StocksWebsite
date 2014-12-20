package com.isaacs.dao;

import com.isaacs.model.User;

public abstract interface UserDao
{  
  public abstract User findUser(String username, String password);
}