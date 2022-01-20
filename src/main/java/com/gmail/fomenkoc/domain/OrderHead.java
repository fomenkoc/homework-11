package com.gmail.fomenkoc.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class OrderHead {

	private Integer orderID;
	private Timestamp orderDate;
	private String orderStatus;
	private String orderNote;
	private Integer userID;
	private Double totalQuantity;
	private Double totalSum;

	public OrderHead(Integer orderID, Timestamp orderDate, String orderStatus,
			String orderNote, Integer userID, Double totalQuantity,
			Double totalSum) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderNote = orderNote;
		this.userID = userID;
		this.totalQuantity = totalQuantity;
		this.totalSum = totalSum;
	}

	public OrderHead(Timestamp orderDate, String orderStatus, String orderNote,
			Integer userID, Double totalQuantity, Double totalSum) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderNote = orderNote;
		this.userID = userID;
		this.totalQuantity = totalQuantity;
		this.totalSum = totalSum;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderDate, orderID, orderNote, orderStatus,
				totalQuantity, totalSum, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderHead other = (OrderHead) obj;
		return Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderID, other.orderID)
				&& Objects.equals(orderNote, other.orderNote)
				&& Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(totalQuantity, other.totalQuantity)
				&& Objects.equals(totalSum, other.totalSum)
				&& Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "OrderHead [orderID=" + orderID + ", orderDate=" + orderDate
				+ ", orderStatus=" + orderStatus + ", orderNote=" + orderNote
				+ ", userID=" + userID + ", totalQuantity=" + totalQuantity
				+ ", totalSum=" + totalSum + "]";
	}

}
