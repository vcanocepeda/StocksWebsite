package com.isaacs.dao.factories;

public abstract class AbstractFactory {
   abstract MarketDaoFactory getMarketDaoFactory(String color);
}