import './shared.js'

import Vue from 'vue';

import Groups from './vue/admin/Groups.vue';
import Attributes from './vue/admin/Attributes.vue';
import Children from './vue/admin/Children.vue';

const GroupsComponent = Vue.extend(Groups);
const AttributesComponent = Vue.extend(Attributes);
const ChildrenComponent = Vue.extend(Children);

import 'select2';
import 'select2/dist/css/select2.css';

console.log($(document));

$(document).ready(function () {
	var groupsComponentVue = new GroupsComponent({
		el: '#manage-groups'
	});
	
	var attributesComponentVue = new AttributesComponent({
		el: '#manage-child-attributes'
	});
	
	var childrenComponentVue = new ChildrenComponent({
		el: '#manage-children'
	});
	
	$("#inputLastName").select2({
		allowClear: true,
		data: lastNames
	});
	
	childrenComponentVue.initChildrenTab();
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		var anchorEle = e.target;
		var tabname = $(anchorEle).data("tabname");
		
		switch(tabname) {
		case 'children':
			childrenComponentVue.initChildrenTab();
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