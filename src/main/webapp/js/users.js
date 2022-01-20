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



let users = null;
$.post("user", function(data) {
	if (data !== '') {
		users = data;
	}
}).done(function() {
	
	let tableContent = "<tr class='header'>"+
					"<th style='width: 20%;'>First Name</th>"+
					"<th style='width: 20%;'>Last Name</th>"+
					"<th style='width: 20%;'>Role</th>"+
					"<th style='width: 20%;'>Email</th>"+
					"<th style='width: 20%;'>Options</th>"+
					"</tr>";
	
	jQuery.each(users, function(i, value) {
	
		tableContent+="<tr>"+
					  "<td>" + value.firstName + "</td>"+
					  "<td>" + value.lastName + "</td>"+
					  "<td>" + value.roleName + "</td>"+
					  "<td>" + value.email + "</td>"+
					  "<td><button onclick='deleteUser(" + value.userID + ")'>delete</button></td>"+
					  "</tr>"
					   
	});
	
	  $('#myTable').html(tableContent);
	
});

function deleteUser(userID) {	
	let customUrl = '';
	let urlContent = window.location.href.split('/');
	for (let i = 0; i < urlContent.length-1; i++) {
		customUrl+=urlContent[i]+'/'
	}
	customUrl+='user?userID='+userID;
	
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
