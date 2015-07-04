package com.isaacs.dao.factories;

public enum Entities {
	    MARKET ("Market"), 
	    STOCK  ("Stock")
	    ;
	    private final String code;

	    Entities(String code) {
	        this.code = code;
	    }
	    
	    public String getCode() {
	    	return this.code;
	    }
}
