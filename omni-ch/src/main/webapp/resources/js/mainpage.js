/**
 * 
 */

/**
 * for Toggle menu
 */
$(document).ready(function() {
	$('.toggle-menu').on('click', function(e) {
		if ($('.menu-panel').hasClass('active-menu-panel')) {
			$('.menu-panel').removeClass('active-menu-panel box-shadow-2');

			$('.icon-toggle').removeClass('fa-times');
			$('.icon-toggle').addClass('fa-bars');
		} else {
			$('.menu-panel').addClass('active-menu-panel box-shadow-2');

			$('.icon-toggle').removeClass('fa-bars');
			$('.icon-toggle').addClass('fa-times');
		}
	});
});