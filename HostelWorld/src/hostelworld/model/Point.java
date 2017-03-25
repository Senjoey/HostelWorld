package hostelworld.model;

import java.io.Serializable;

public class Point implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userId;//队换人
	private String date;//兑换时间
	private double pontisSum;//兑换积分数
	private double moneySum;//兑换金额（不同会员等级兑换率不同）
	private double balance;//兑换后会员卡余额
	
	public String getUserId() {
		return userId;
	}
	public String getDate() {
		return date;
	}
	public double getPontisSum() {
		return pontisSum;
	}
	public double getMoneySum() {
		return moneySum;
	}
	public double getBalance() {
		return balance;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPontisSum(double pontisSum) {
		this.pontisSum = pontisSum;
	}
	
	public void setMoneySum(double moneySum) {
		this.moneySum = moneySum;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
