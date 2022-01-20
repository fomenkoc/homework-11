"use strict";

let role = null;

$(document).ready(function() {
	$('.leftmenutrigger').on('click', function(e) {
		$('.side-nav').toggleClass("open");
		e.preventDefault();
	});
});

$("a.product-logout").click(function() {
	$.get("logout", function(data) {
		if (data !== '') {
			let customUrl = '';
			let urlContent = window.location.href.split('/');
			for (let i = 0; i < urlContent.length - 1; i++) {
				customUrl += urlContent[i] + '/'
			}
			customUrl += data;
			window.location = customUrl;
		}
	});

});


$(document).ready(function() {
	$.get("role", function(data) {
		if (data !== '') {
			role = data;
		}
	}).done(function() {
		if (role.isStaff !== true) {
			$(".staff-only").hide();
		}
		$(".user-role").html(role.roleName);
	});
});