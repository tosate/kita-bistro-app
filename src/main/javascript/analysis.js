import './shared.js';
import Vue from 'vue';
import Analysis from './vue/Analysis.vue';
const AnalysisComponent = Vue.extend(Analysis);

console.log($(document));

$(document).ready(function () {
	
	var meals = [];
	
	var analysisVueInstance = new AnalysisComponent({
		el: '#searchResult',
		data: {
			meals: []
		}
	});
	
	$("searchResult").empty();
	analysisVueInstance.meals = [];
	
	$.get("analysis/meals-with-notice", {
		date: new Date()
	}).done(function(data) {
		if(data.length > 0) {
			analysisVueInstance.meals = data;
		} else {
			$("#searchWarning").html("keine Ergebnisse gefunden!");
			$("#searchWarning").show();
		}
	}).fail(function (data) {
		$("#searchWarning").html(data.responseText);
		$("#searchWarning").show();
	});
});