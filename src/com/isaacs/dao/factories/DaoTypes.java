package com.isaacs.dao.factories;

public enum DaoTypes {
	    HIBERNATE (0), 
	    RESTXML  (1)
	    ;
	    private final Integer code;

	    DaoTypes(Integer code) {
	        this.code = code;
	    }
	    
	    public int getCode() {
	    	return this.code;
	    }
}