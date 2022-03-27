<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="image">
					<image src="../../static/img/water.jpeg" mode="widthFix"></image>

					<view class="cu-bar bg-shadeBottom"><text class="text-cut">一滴清水，一片绿地，一个地球</text></view>
				</view>
				<view class="cu-list menu-avatar" @click="authinfo">
					<view class="cu-item">
						<view class="cu-avatar round lg"
							:style="[{ backgroundImage:'url(' + getUserinfo.avatar + ')' }]">
						</view>
						<view class="content flex-sub">
							<view class="text-grey">{{getNeedAuth==false?"欢迎您,"+getUserinfo.nickname+"!":"还未登录，请先登录"}}
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="cu-card case">
			<view class="cu-item shadow">

				<view class="cu-dialog">
					<view class="cu-list menu text-left solid-top">
						<view class="cu-item text-center">
							<view class="content " @tap="ToUploadWataerImg(getNeedAuth)">
								<image src="/static/img/index/w3.png" class="png" mode="aspectFit"></image>
								<text class="text-cyan ">上传水质检测报告</text>
							</view>

						</view>
						<view class="cu-item text-center">
							<view class="content" @tap="ToCheckWaterRp(getNeedAuth)">
								<image src="/static/img/index/w1.png" class="png" mode="aspectFit"></image>
								<text class="text-green">查看上报记录</text>
							</view>

						</view>
						<view class="cu-item text-center">
							<view class="content">
								<image src="/static/img/index/w2.png"@tap="TocheckWaterRes(getNeedAuth)" class="png" mode="aspectFit"></image>
								<text class="text-blue">查看水质报告</text>
							</view>

						</view>

						<view class="cu-item text-center">
							<view class="content">
								<image src="/static/img/index/w4.png" class="png" mode="aspectFit"></image>
								<text class="text-purple">管理员登录</text>
							</view>

						</view>
					</view>

				</view>

			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapGetters,
		mapActions
	} from 'vuex'
	export default {
		data() {
			return {

			}
		},
		methods: {
			...mapActions(['authUserInfo']),
			ToUploadWataerImg(flag) {
				if (flag == true) {
					this.authUserInfo()
					uni.navigateTo({
						url: "../user/uploadWaterImg/uploadWaterImg"
					})
				} else {
					uni.navigateTo({
						url: "../user/uploadWaterImg/uploadWaterImg"
					})
				}

			},
			ToCheckWaterRp(flag) {
				if (flag == true) {
					this.authUserInfo()
					uni.navigateTo({
						url: "../user/checkWaterRp/checkWaterRp"
					})
				} else {
					uni.navigateTo({
						url: "../user/checkWaterRp/checkWaterRp"
					})
				}
			},
			TocheckWaterRes(flag) {
				if (flag == true) {
					this.authUserInfo()
					uni.navigateTo({
						url: "../user/checkWaterRes/checkWaterRes"
					})
				} else {
					uni.navigateTo({
						url: "../user/checkWaterRes/checkWaterRes"
					})
				}
			},

			AdminContro() {

			},
			authinfo() {
				if (this.$store.state.needAuth == true) {
					this.authUserInfo()
				}

			}
		},
		onLoad() {

		},
		computed: {
			...mapGetters(['getUserinfo', 'getNeedAuth', 'getIsLogin'])
		},
	}
</script>

<style>

</style>
