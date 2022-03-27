<template>
	<view>
		<u-calendar :show="show" :mode="mode" @confirm="confirm"></u-calendar>
				<u-button @click="show = true">打开</u-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
			}
		},
		methods: {
			confirm(e) {
							console.log(e);
						}
		}
	}
</script>

<style>

</style>
