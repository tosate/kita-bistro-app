import $ from 'jquery';
import _ from 'lodash';

window.$ = $;
window.jQuery = $;

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.css';

var moment = require('moment');

import 'bootstrap-notify';

console.log("Loaded shared module - shared.js")

window.PORTAL = {};

PORTAL.CurrentPage = {};

window.onerror = function (msg, url, lineNo, columnNo, error) {
	try {
		var string = msg.toLowerCase();
		var substring = "script error";
		var message = '';
		if(string.indexOf(substring) > -1) {
			message = 'Script Error - can not extract error information - see browser console for detail';
		} else {
			var message = [
				'Message=' + msg,
				'URL=' + url,
				'Line=' + lineNo,
				'Column=' + columnNo,
				'Error object=' + JSON.stringify(error)
			].join(', ');
			
			$.post('/javascriptError', {errorMsg: message} );
		}
	} catch(err) {
		console.error("Could not execute default js error handler : " + err);
	}
	
	return false;
};

window.notifyOk = PORTAL.notifyOk = function (msg) {
	var dateString = moment().format("HH:mm:ss");
	$.notify({
		// options
		message: dateString + " | " + msg,
	},{
		// settings
		type: 'success',
		delay: 5000,
		animate: {
			enter: 'animated flipInX',
			exit: 'animated flipOutX'
		},
		placement: {
			from: "bottom",
			align: "center"
		},
		offset: {
			y: 60
		},
		template: '<div data-notify="container" class="col-xs-11 col-sm-8 alert alert-{0}" role="alert"><button type="button" aria-hidden="true" class="close" data-notify="dismiss">&times;</button><span data-notify="icon"></span> <span data-notify="title">{1}</span> <span data-notify="message">{2}</span><div class="progress" data-notify="progressbar"><div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div></div><a href="{3}" target="{4}" data-notify="url"></a></div>'
	});
};

$().ready(function () {
	// Global stuff to execute when the document is 'ready'
	$(".fancy-input-input").on('input change blur', function () {
		var value = $(this).val();
		if (value && value.length > 0) {
			$(this).attr("style","text-indent: 15px; padding-top: 12px;");
			$(this).parent().parent().find(".placeholder").attr("style","z-index: 3; font-size: 12px; top: 5px;");
		} else {
			$(this).attr("style","text-indent: 10px; padding-top: 5px;");
			$(this).parent().parent().find(".placeholder").attr("style","z-index: 1;");
		}
	});
	
	//Activates the current page in the header main menu
	$('ul.nav a[href="'+ window.location.href.substr(window.location.href.lastIndexOf("/")+1) +'"]').parent().addClass('active');
});