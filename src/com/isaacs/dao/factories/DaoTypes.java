package com.isaacs.dao.factories;

// We shouldn't keep logic in this class, just the types (maybe we
// should take out the DaoTypes and this class implementing it
// public enum Digits {TWO, EIGHT, TEN, THIRTEEN }
public interface DaoTypes {
	public static final String[] DaoType =  {"DaoHibernate", "DaoRestXml"};
}