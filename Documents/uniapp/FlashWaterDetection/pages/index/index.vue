<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="image">
					<image src="../../static/img/water.jpeg" mode="widthFix"></image>

					<view class="cu-bar bg-shadeBottom"> <text class="text-cut">一滴清水，一片绿地，一个地球</text></view>
				</view>
				<view class="cu-list menu-avatar">
					<view class="cu-item">
						<view class="cu-avatar round lg" style="background-image:url(../../static/img/water.jpeg);">
						</view>
						<view class="content flex-sub">
							<view class="text-grey"> </view>

						</view>
					</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tabBar: '',
				title: 'Hello world',
			}
		},
		onLoad() {},
		methods: {
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			},
			get(){
				console.log(uni.$u.config.v);
			}
		}
	}
</script>

<style lang="scss">
  .demo-layout {
        height: 25px;
        border-radius: 4px;
    }

</style>
