package com.gmail.fomenkoc.domain;

import java.util.Objects;

public class Prod {

	private Integer prodID;
	private String prodName;
	private String description;
	private Double price;

	public Prod(Integer prodID, String prodName, String description,
			Double price) {
		super();
		this.prodID = prodID;
		this.prodName = prodName;
		this.description = description;
		this.price = price;
	}

	public Prod(String prodName, String description, Double price) {
		super();
		this.prodName = prodName;
		this.description = description;
		this.price = price;
	}

	public Integer getProdID() {
		return prodID;
	}

	public void setProdID(Integer prodID) {
		this.prodID = prodID;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, price, prodID, prodName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prod other = (Prod) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(price, other.price)
				&& Objects.equals(prodID, other.prodID)
				&& Objects.equals(prodName, other.prodName);
	}

	@Override
	public String toString() {
		return "Prod [prodID=" + prodID + ", prodName=" + prodName
				+ ", description=" + description + ", price=" + price + "]";
	}

}
