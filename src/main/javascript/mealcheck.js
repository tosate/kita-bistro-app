import './shared.js';
import Vue from 'vue';
import Meals from './vue/Meals.vue';
const MealsComponent = Vue.extend(Meals);

import 'select2';
import 'select2/dist/css/select2.css';

console.log($(document));

$(document).ready(function () {
	$("#inputGroupName").select2({
		data: groupNames,
		minimumResultsForSearch: -1
	});
	
	$("#inputMealType").select2({
		data: mealTypes,
		minimumResultsForSearch: -1
	});
	
	var MealsVueInstance = new MealsComponent({
		el: '#searchResult',
		data: {
			meals: [ ]
		}
	});
	
	$("#searchForm").submit(function (e) {
		
		// prevent usual submit of the form, handle with ajax instead
		e.preventDefault();
		
		$("#searchResult").empty();
		
//		$("#searchWarning").hide();
		
		var mealType = $("#inputMealType").val();
		var groupName = $("#inputGroupName").val();
		
		//$("#searchingSpinner").fadeIn();
		MealsVueInstance.meals = [];
		
		$("#searchBtn").prop("disabled", true);
		$.get("mealcheck/search", {
			groupName : groupName,
			mealType : mealType
		}).always( function () {
//			$("searchingSpinner").hide();
			$("#searchBtn").prop("disabled", false);
		}).done( function (data) {
			if(data.length > 0) {
				MealsVueInstance.meals = data;
			} else {
				// Empty result
				$("#searchWarning").html("Keine Ergebnisse gefunden!");
				$("#searchWarning").show();
			}
		}).fail( function (data) {
			$("#searchWarning").html(data.responseText);
			$("#searchWarning").show();
		});
		
		// Stop propagation to avoid bubbling up parsley event
//		e.stopImmediatePropagation()
	});
	
	$('#saveNotice').click(function() {
		var vm = this;
		$.get("/mealcheck/save/notice", {
			mealId: $("#mealId").val(),
			notice: $("#noticeTextarea").val()
		}).done(function(controllerResponse) {
			if(controllerResponse.success) {
				$("#noticeDialogError").hide();
				$("#noticeModalDialog").modal('hide');
				$("#searchForm").submit();
			} else {
				console.log(controllerResponse.message);
				$("#noticeDialogError").show();
				$("#noticeDialogError").html(controllerResponse.message);
			}
		}).fail(function(controllerResponse) {
			$("#noticeDialogError").show();
			$("#noticeDialogError").html("Fehler beim Speichern der Notiz: " + controllerResponse.message);
		});
	});
});
