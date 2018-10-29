<template>
<div id="noticeModalDialog" class="modal fade" tabindex="-1">
	<div class="modal-dialog" role="document" style="width: 50%;">
		<div class="modal-content">
			<div class="modal-header">
				Notiz erfassen
			</div>
			<div class="modal-body">
				<textarea id="noticeTextarea" rows="2" cols="80"></textarea>
				<button type="button" class="btn btn-primary" v-on:click="saveNotice(meal)">
					Speichern
				</button>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</div>
</div>
</template>

<script>

module.exports = {
		data: function () {
			var data = {
					mealData: {
						id: '',
						notice: ''
					}
			};
			return data;
		},
		methods: {
			showModalNoticeDialog: function(meal) {
				var vm = this;
				vm.mealData.id = meal.id;
				vm.mealData.notice = meal.notice
				$("#noticeModalDialog").modal();
				$("#noticeTextarea").val(vm.mealData.notice);
			},
			saveNotice: function() {
				var vm = this;
				$(this).prop("disabled", true);
				$.get("mealcheck/save/notice", {
					mealId: vm.mealData.id,
					notice: $("#noticeTextarea").val()
				}).alway(function () {
					$(this).prop("disabled", false);
				}).done(function (controllerResponse) {
					if(controllerResponse.success) {
						meal.notice = $("#noticeTextarea").val();
					} else {
						console.log(controllerRespone.message);
						alert(controllreResponse.message);
					}
				}).fail(function() {
					alert("Fehler beim Speichern der Notiz!");
				});
			}
		}
}
</script>