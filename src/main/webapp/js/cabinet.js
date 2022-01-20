"use strict";

let products = null;

$.get("products", function(data) {
	if (data !== '') {
		products = data;
	}
}).done(function(){
	let cardsContent = "";
	jQuery.each(products, function(i, value) {
	
		cardsContent +=
		"<div class='prodCard'>" +
		"<div class='card'>" +
		"<div class='card-body'>" +
		"<h5 class='card-title'>" + value.prodName + "</h5>"+
		"<h6 class='card-subtitle mb-2 text-muted'>" + value.price + "</h6>"+
		"<p class='card-text'>" + value.description + "</p>"+
		"<a href='product?prodID=" + value.prodID + "' class='card-link'>link</a>"+
		"</div>" +
		"</div>" +
		"</div>" +
		"</div>"
	});
	
	  $('#productCards').html(cardsContent);
});