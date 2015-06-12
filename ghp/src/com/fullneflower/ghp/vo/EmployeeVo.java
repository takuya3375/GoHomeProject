package com.fullneflower.ghp.vo;

public class EmployeeVo {
	/**
	 * プロパティ
	 */
	private String empNo; //社員番号
	private String password;//パスワード
	private String empName;//従業員名

	/**
	 * メソッド
	 */
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
