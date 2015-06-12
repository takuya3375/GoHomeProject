package com.fullneflower.ghp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutBean  implements  fullneflowerBean{
	public String execute(HttpServletRequest request, HttpServletResponse response){
		/**
		 * セッションが保持している場合はセッションを切断し、ログイン画面へ遷移
		 */try{
			 HttpSession session = request.getSession(false);
			 if(session != null){
				 session.invalidate();
			 }
			 return "failure";
		 }catch(Exception e){
			 /**
			  * エラーが起きたのでシステムエラー画面へ遷移
			  */
			 e.printStackTrace();
			 return "error";
		 }
	}
}
