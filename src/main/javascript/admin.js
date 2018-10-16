import './shared.js'

import Vue from 'vue';

import Groups from './vue/admin/Groups.vue';

const GroupsComponent = Vue.extend(Groups);

$(document).ready(function () {
	var groupsComponentVue = new GroupsComponent({
		el: '#manage-groups'
	});
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		var anchorEle = e.target;
		var tabname = $(anchorEle).data("tabname");
		
		switch(tabname) {
		case 'children':
			break;
		case 'child-attributes':
			break;
		case 'groups':
			groupsComponentVue.fetch();
			break;
		default:
			break;
		}
	});
});