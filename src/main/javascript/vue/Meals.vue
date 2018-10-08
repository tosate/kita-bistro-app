<template>
<ul class="ul-columns" style="padding: 5px; list-style-type: none;">
	<template v-for="meal in meals">
	<li>
		<Meal :meal="meal"></Meal>
	</li>
	</template>
</ul>
</template>

<script>
import Meal from './Meal.vue';

export default {
//	data : {
//		meals : []
//	},
	components: {
		Meal
	}
}
</script>