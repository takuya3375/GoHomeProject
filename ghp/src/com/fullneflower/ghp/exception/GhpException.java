package com.fullneflower.ghp.exception;

public class GhpException extends Exception {

	public GhpException(){
		super();
	}
	public GhpException(String msg,Throwable e){
		super(msg,e);
	}
	public GhpException(String msg){
		super(msg);
	}
	public GhpException(Throwable e){
		super(e);
	}
}
