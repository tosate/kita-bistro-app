<template>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading"><b>{{meal.child.firstName}}</b></div>
		<div class="panel-body">{{meal.child.lastName}}</div>
	</div>
</div>
</template>

<script>

module.exports = {
		props: [ 'meal' ],
		methods: {
			foo: function() {
				
			}
		}
}
</script>