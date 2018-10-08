<template>
<div class="meal-panel">
	<div class="panel-meal-heading" style="position: relative; padding: 5px 15px 15px;">
		<span v-if="meal.type == 'BREAKFAST'" class="label label-default">F</span>
		<span v-else class="label label-default">M</span>
		{{meal.child.firstName}} {{meal.child.lastName}}
		<div v-if="meal.child.attributes.length > 0" style="position: absolute; top: 5px; right:15px;">
			<span class="badge" style="background-color: #17a2b8">{{meal.child.attributes.length}}</span>
			<div ttId="search.expand" ttPosition="top" class="expandAll" style="display: inline-block;"
				v-on:click="expandAll">
				<span class="glyphicon glyphicon-plus" style="margin: 0px 5px; color: gainsboro;"></span>
			</div>
		</div>
	</div>
	<div>
		<template v-if="meal.child.attributes">
			<div class="panelChildBody">
				<table class="table table-hover" style="font-size: smaller; maring-bottom: 0px;">
					<tbody>
						<template v-for="attribute in meal.child.attributes">
						<tr>
							<td>{{ attribute.classification }}</td>
							<td>{{ attribute.name }}</td>
						</tr>
						</template>
					</tbody>
				</table>
			</div>
		</template>
	</div>
	<button type="button" v-bind:class="buttonClassAttrib(meal.eaten)" v-on:click="updateMeal(meal)">
		<span v-if="meal.eaten" style="margin-top: 40px; margin-bottom: 40px;">gegessen<span class="glyphicon glyphicon-ok-circle" style="margin-left: 15px;"></span></span>
		<span v-else style="margin-top: 40px; margin-bottom: 40px;">nicht gegessen<span class="glyphicon glyphicon-ban-circle" style="margin-left: 15px;"></span></span>
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
			},
			buttonClassAttrib(eaten) {
				if (eaten) {
					return "btn btn-default btn-block btn-success";
				} else {
					return "btn btn-default btn-block";
				}
			},
			expandAll: function() {
				var $el = $(this.$el);
				var baseEleIsExpanded = $el.find(".panelChildBody").is(':visible');
				if(!baseEleIsExpanded) {
					$el.find(".panelChildBody").slideDown(100);
				} else {
					$el.find(".panelChildBody").slideUp(100);
				}
			}
		}
}
</script>