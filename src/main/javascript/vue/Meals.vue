<template>
<div class="grid-container">
	<template v-for="meal in meals">
	<Meal :meal="meal"></Meal>
	</template>
</div>
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