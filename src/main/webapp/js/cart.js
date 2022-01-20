"use strict";

function myFunction() {
	let input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}



let buckets = null;
$.get("cart", function(data) {
	if (data !== '') {
		buckets = data;
	}
}).done(function() {
	
	let tableContent = "<tr class='header'>"+
					"<th style='width: 20%;'>Name</th>"+
					"<th style='width: 20%;'>Description</th>"+
					"<th style='width: 20%;'>Price</th>"+
					"<th style='width: 10%;'>Quantity</th>"+
					"<th style='width: 20%;'>Sum</th>"+
					"<th style='width: 10%;'>Options</th>"+
					"</tr>";
	
	jQuery.each(buckets, function(i, value) {
	
		tableContent+="<tr>"+
					  "<td>" + value.prodName + "</td>"+
					  "<td>" + value.description + "</td>"+
					  "<td>" + value.price + "</td>"+
					  "<td>" + value.quantity + "</td>"+
					  "<td>" + value.sum + "</td>"+
					  "<td><button onclick='deleteCart(" + value.cartID + ")'>delete</button></td>"+
					  "</tr>"
					   
	});
	
	  $('#myTable').html(tableContent);
	
});

function deleteCart(cartID) {	
	let customUrl = '';
	let urlContent = window.location.href.split('/');
	for (let i = 0; i < urlContent.length-1; i++) {
		customUrl+=urlContent[i]+'/'
	}
	customUrl+='cart?cartID='+cartID;
	
	$.ajax({
	    url: customUrl,
	    type: 'DELETE',
	    success: function(data) {
	    	if (data == 'Success') {
	    		location.reload();
			}
	    }
	});
}
