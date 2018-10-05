<template>
<div class="col-md-3">
	<div class="panel panel-default" style="min-width: 75px; max-width: 300px; display: block; margin-bottom: 3px;">
		<div>
			<div v-if="meal.type == 'BREAKFAST'" class="label label-default">F</div>
			<div v-else class="label label-default">M</div>
			{{meal.child.firstName}} {{meal.child.lastName}}
		</div>
		<button type="button" v-bind:class="buttonClassAttrib(meal.eaten)" v-on:click="updateMeal(meal)">
			<span v-if="meal.eaten" style="margin-top: 40px; margin-bottom: 40px;">gegessen<span class="glyphicon glyphicon-ok-circle" style="margin-left: 15px;"></span></span>
			<span v-else style="margin-top: 40px; margin-bottom: 40px;">nicht gegessen<span class="glyphicon glyphicon-ban-circle" style="margin-left: 15px;"></span></span>
		</button>
	</div>
</div>
</template>

<script>

module.exports = {
		props: [ 'meal' ],
		methods: {
			updateMeal: function(meal) {
				$(this).prop("disabled", true);
				$.get("mealcheck/update-meal", {
					mealId : meal.id,
					eaten : meal.eaten
				}).always(function (){
					$(this).prop("disabled", false);
				}).done(function () {
					meal.eaten = (!meal.eaten);
				}).fail(function() {
					$("#searchWarning").html("Fehler bei der Aktualisierung des Essensstatus!");
					$("#searchWarning").show();
				});
			},
			buttonClassAttrib(eaten) {
				if (eaten) {
					return "btn btn-default btn-block btn-success";
				} else {
					return "btn btn-default btn-block";
				}
			}
		}
}
</script>