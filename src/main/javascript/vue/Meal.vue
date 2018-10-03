<template>
<div class="col-md-3">
	<button class="btn" type="button" v-on:click="updateMeal(meal)">
		<b>{{meal.child.firstName}}</b><br/>
		{{meal.child.lastName}}
	</button>
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
			}
		}
}
</script>