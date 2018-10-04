<template>
<div class="container">
	<div class="row" style="margin-top: 10px;" v-for="i in Math.ceil(meals.length / 4)">
		<template v-for="meal in meals.slice((i - 1) * 4, i * 4)">
			<Meal :meal="meal"></Meal>
		</template>
	</div>
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