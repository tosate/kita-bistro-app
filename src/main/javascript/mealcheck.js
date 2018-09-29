import './shared.js';
import 'select2';
import 'select2/dist/css/select2.css';

console.log($(document));

$(document).ready(function () {
	$("#inputGroupName").select2({
		data: groupNames
	});
	
	$("#inputMeal").select2({
		data: meals
	});
});