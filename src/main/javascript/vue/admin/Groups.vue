<template>
<div>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th>Aktionen</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="group in groups" v-bind:key="group.id">
				<template>
					<td>{{group.name}}</td>
					<td>
						<button class="btn btn-default btn-xs" @click.prevent="deleteGroup(group.id)">
							<span class="glyphicon glyphicon-trash" style="display: block;"></span>
						</button>
					</td>
				</template>
			</tr>
		</tbody>
	</table>
</div>
</template>

<script>
export default {
	data: function () {
		var data = { groups: [] };
		return data;
	},
	controllerResponse: function () {
		var controllerResponse = { success: Boolean, message: "" };
		return controllerResponse;
	},
	methods: {
		fetch: function() {
			var vm = this;
			$.ajax({
				url: "/admin/fetch/groups",
				timeout: 10000
			}).done(function (data) {
				$(vm.$refs.alert).hide();
				console.log(data);
				vm.groups = data;
			}).fail(function () {
				$(vm.$refs.alert).show();
			});
		},
		deleteGroup: function(id) {
			var vm = this;
			$.get("/admin/delete/group", {
				groupId: id
			}).done(function (controllerResponse) {
				if(controllerResponse.success) {
					$(vm.$refs.alert).hide();
				} else {
					console.log(controllerResponse.message);
					alert(controllerResponse.message);
				}
			}).fail(function (controllerResponse) {
				alert("Fehler beim Löschen der Gruppe!");
			});
		}
	}
}
</script>