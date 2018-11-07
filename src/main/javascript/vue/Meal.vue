<template>
<div class="meal-panel">
	<div id="noticeModalDialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">Notiz erfassen</div>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="noticeDialogError" class="alert alert-danger" role="alert" style="text-align:center; display: none;" ref="noticeAlert"></div>
					<form>
						<div class="form-group">
							<label for="noticeTextarea">Notiz</label>
							<textarea id="noticeTextarea" v-model="editMealData.notice" rows="2" cols="60" class="form-control"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">
						Abbrechen
					</button>
					<button type="button" class="btn btn-primary" v-on:click="saveNotice">
						Notiz speichern
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-meal-heading" style="position: relative; padding: 5px 10px 5px;">
		<span v-if="meal.type == 'BREAKFAST'" class="label label-default">F</span>
		<span v-else class="label label-default">M</span>
		<button type="button" class="btn btn-default btn-xs" v-on:click="openModalNoticeDialog(meal)" style="width: 60px; padding: 5px 8px 5px;">
			Notiz
			<span class="glyphicon glyphicon-pencil"></span>
		</button>
		<button type="button" v-if="meal.child.attributes.length > 0" class="btn btn-default btn-xs" style="width: 60px; padding: 5px 8px 5px;" v-on:click="expandAll">
			<span class="badge" style="background-color: #17a2b8">{{meal.child.attributes.length}}</span>
			<span class="glyphicon glyphicon-warning-sign"></span>
		</button>
	</div>
	<div style="padding: 5px 15px 15px;">
		{{meal.child.firstName}} {{meal.child.lastName}}
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
		data: function() {
			var data = {
				editMealData: {
					id: 0,
					notice: ''
				}
			};
			return data;
		},
		methods: {
			updateMeal: function(meal) {
				$(this).prop("disabled", true);
				$.get("mealcheck/update/meal", {
					mealId : meal.id,
					eaten : meal.eaten
				}).always(function (){
					$(this).prop("disabled", false);
				}).done(function (controllerResponse) {
					if(controllerResponse.success) {
						meal.eaten = (!meal.eaten);
					} else {
						console.log(controllerResponse.message);
						alert(controllerResponse.message);
					}
				}).fail(function() {
					alert("Fehler bei der Aktualisierung des Essensstatus!");
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
			},
			openModalNoticeDialog: function(meal) {
				var vm = this;
				$("#noticeModalDialog").modal();
				vm.editMealData.id = meal.id;
				if(meal.notice == null) {
					vm.editMealData.notice = '';
				} else {
					vm.editMealData.notice = meal.notice;
				}
			},
			saveNotice: function() {
				var vm = this;
				$.get("/mealcheck/save/notice", {
					mealId: vm.editMealData.id,
					notice: vm.editMealData.notice
				}).done(function(controllerResponse) {
					if(controllerResponse.success) {
						$(vm.$refs.noticeAlert).hide();
						$("#noticeModalDialog").modal('hide');
					} else {
						console.log(controllerResponse.message);
						$(vm.$refs.noticeAlert).show();
						$("#noticeDialogError").html(controllerResponse.message);
					}
				}).fail(function(controllerResponse) {
					$(vm.$refs.noticeAlert).show();
					$("#noticeDialogError").html("Fehler beim Speichern der Notiz: " + controllerResponse.message);
				});
			}
		}
}
</script>