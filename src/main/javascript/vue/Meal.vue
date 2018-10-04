<template>
<div class="col-md-3">
	<a href="#" v-on:click="updateMeal(meal)" style="text-decoration: none;">
		<div v-bind:class="buttonClassAttrib(meal.eaten)">
			<div>
				<div v-if="meal.type == 'BREAKFAST'" class="label label-default" style="position: absolute; top: 8px; left: 25px;">F</div>
				<div v-else class="label label-default" style="position: absolute; top: 8px; left: 25px;">M</div>
				<div v-if="meal.eaten" class="pull-right">
					gegessen
					<span class="glyphicon glyphicon-ok" style="margin: 5px 5px 5px 5px;"></span>
				</div>
			</div>
			<div>
				<div><b>{{meal.child.firstName}}</b></div>
				<div>{{meal.child.lastName}}</div>
			</div>
		</div>
	</a>
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