"use strict";

let user = null;


$.get("user", function(data) {
	if (data !== '') {
		user = data;
	}
}).done(function(){
	let userName = user.firstName + " " + user.lastName;
	
	$(".user-id").html(user.userID);
	$(".user-name").html(userName);
	$(".user-email").html(user.email);
	 
});



