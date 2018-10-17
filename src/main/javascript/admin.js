import './shared.js'

import Vue from 'vue';

import Groups from './vue/admin/Groups.vue';
import Attributes from './vue/admin/Attributes.vue';

const GroupsComponent = Vue.extend(Groups);
const AttributesComponent = Vue.extend(Attributes);

$(document).ready(function () {
	var groupsComponentVue = new GroupsComponent({
		el: '#manage-groups'
	});
	
	var attributesComponentVue = new AttributesComponent({
		el: '#manage-child-attributes'
	});
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		var anchorEle = e.target;
		var tabname = $(anchorEle).data("tabname");
		
		switch(tabname) {
		case 'children':
			break;
		case 'child-attributes':
			attributesComponentVue.fetch();
			break;
		case 'groups':
			groupsComponentVue.fetch();
			break;
		default:
			break;
		}
	});
});