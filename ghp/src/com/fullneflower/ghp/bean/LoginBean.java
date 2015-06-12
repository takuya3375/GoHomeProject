package com.fullneflower.ghp.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.EmployeeDao;
import com.fullneflower.ghp.vo.EmployeeVo;

/**
 *ログイン関連の処理をします
 */
public class LoginBean implements  fullneflowerBean{
	//private static final long serialVersionUID = 1L;
/**
 * ・与えられた値をDAOに送り、返ってきた結果が1であればsuccessを返す。
 * ・0であればerrorとfailureを返す。
 */
	public String execute(HttpServletRequest request, HttpServletResponse response){
		ConnectionManager cm = new ConnectionManager();
		try{

			/*
			 * 入力された値を受け取る
			 */
			String empNo= request.getParameter("empno");
			String passWo = request.getParameter("passwd");

			/*
			 * 入力された値をVoに格納する
			 */
			EmployeeVo empVo =new EmployeeVo();
			empVo.setEmpNo(empNo);
			empVo.setPassword(passWo);

			/*
			 * コネクション取得
			 */
			Connection connection = cm.getConnection();

			/*
			 * DAOに入力された値を渡し、担当者IDとパスワードの一致するデータを検索
			 */
			EmployeeDao empDao= new EmployeeDao(connection);
			int result = empDao.search(empVo);

			/*
			 * 件数が1件の場合はセッションを継続し、メニュー画面への遷移を許可
			 * それ以外はそのまま下記の処理へ
			 */
			if (result==1) {

				/*
				 * セッション開始
				 * セッションがない場合、付与する
				 */
				HttpSession session =request.getSession(true);

				/*
				 * セッションに受け取った値を付与する
				 */
				session.setAttribute("empno", empNo);
				session.setAttribute("passwd", passWo);

				return "success";
			}
			/*
			 * 該当件数が1件以外（０、もしくは２以上）の場合、
			 * 入力項目ごとにチェックをし、ログイン画面へ遷移
			 */
			if("".equals(empNo)){
				request.setAttribute("erro", "社員番号（パスワード）が入力されていません");
				/*
				 * IDが入力されてパスワードが入力された場合
				 */

			}else if("".equals(passWo)){
				request.setAttribute("erro", "社員番号（パスワード）が入力されていません");
				/*
				 * 合致しないIDとパスワードともに入力された場合
				 */
			}else{
				request.setAttribute("erro", "入力された社員番号またはパスワードが誤っています");
			}

		}catch(Exception e){

			/*
			 * エラーが起きたのでシステムエラー画面へ遷移
			 */
			return "error";
		}finally{
			cm.closeConnection();	//コネクションの切断
		}
		return "failure";
	}
}

