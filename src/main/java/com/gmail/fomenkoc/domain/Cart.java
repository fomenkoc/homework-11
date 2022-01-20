package com.gmail.fomenkoc.domain;

import java.util.Objects;

import com.gmail.fomenkoc.dao.impl.ProdDao;

public class Cart {
	private Integer cartID;
	private Integer userID;
	private Integer prodID;
	private Double price;
	private Double quantity;
	private Double sum;

	public Cart(Integer cartID, Integer userID, Integer prodID, Double price,
			Double quantity, Double sum) {
		super();
		this.cartID = cartID;
		this.userID = userID;
		this.prodID = prodID;
		this.price = price;
		this.quantity = quantity;
		this.sum = sum;
	}

	public Cart(Integer userID, Integer prodID, Double price, Double quantity,
			Double sum) {
		super();
		this.userID = userID;
		this.prodID = prodID;
		this.price = price;
		this.quantity = quantity;
		this.sum = sum;
	}
	
	public Cart(Integer userID, Integer prodID) {
		this(userID, prodID, 0.0, 1.0, 0.0);
		ProdDao prodDao = new ProdDao();
		Prod prod = prodDao.read(prodID);
		Double price = prod.getPrice();
		this.price = price;
		this.sum = price;
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getProdID() {
		return prodID;
	}

	public void setProdID(Integer prodID) {
		this.prodID = prodID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartID, price, prodID, quantity, sum, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartID, other.cartID)
				&& Objects.equals(price, other.price)
				&& Objects.equals(prodID, other.prodID)
				&& Objects.equals(quantity, other.quantity)
				&& Objects.equals(sum, other.sum)
				&& Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", userID=" + userID + ", prodID="
				+ prodID + ", price=" + price + ", quantity=" + quantity
				+ ", sum=" + sum + "]";
	}

}
