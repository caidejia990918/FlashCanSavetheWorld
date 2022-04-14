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
						<view class="cu-avatar round lg">
							<image  :src="getUserinfo.avatar" class="cu-avatar round lg"></image>
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
								<text class="text-cyan ">上传水质报告</text>
							</view>

						</view>
						<view class="cu-item text-center" @tap="TocheckWaterRes(getNeedAuth)">
							<view class="content">
								<image src="/static/img/index/w2.png" class="png" mode="aspectFit"></image>
								<text class="text-blue">查看水质报告</text>
							</view>
						</view>
						<view class="cu-item text-center">
							<view class="content" @tap="ToCheckWaterRp(getNeedAuth)">
								<image src="/static/img/index/w1.png" class="png" mode="aspectFit"></image>
								<text class="text-green">查看上报记录</text>
							</view>
						</view>


						<view class="cu-item text-center">
							<view class="content" @tap="AdminContro(getUserinfo.rule)">
								<image src="/static/img/index/w4.png" class="png" mode="aspectFit"></image>
								<text class="text-purple">分管负责人登录</text>
							</view>
						</view>
					</view>
				</view>
				<u-popup v-model="show" mode="center" border-radius="14" width="500">
					<view class="text-center">
						<text class="text-xl text-brown">登录</text>
						<u-form :model="form" ref="uForm" label-position="top">
							<u-form-item label="请输入登录密码" prop="password">
								<u-input v-model="form.password" type="password" placeholder="请输入密码" />
							</u-form-item>
						</u-form>
						<button type="primary" size="mini" @tap="login(getUserinfo.password)">确认</button>
					</view>

				</u-popup>

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
				
				form: {
					password: "",
				},
				show: false,
				rules: {
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}],
				}
			}
		},
		methods: {
			...mapActions(['authUserInfo']),
			ToUploadWataerImg(flag) {
				if (flag == true) {
					this.authUserInfo().then(res => {
						console.log(res)
						uni.navigateTo({
							url: "../user/uploadWaterImg/uploadWaterImg"
						})
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

			AdminContro(hasrule) {
				if (hasrule == 1) {
					this.show = true
				} else {
					uni.showToast({
						title: '您不是负责人',
						icon: "error",
						duration: 2000
					})
				}
			},
			login(pass) {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						if(this.form.password == pass){
							this.show = false
							uni.navigateTo({
								url:"../admin/menu/menu"
							})
						}else{
							uni.showToast({
								title: '密码错误',
								icon:"none",
								duration: 1000
							})
						}
						
					} else {
					}
				});
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
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		}
	}
</script>

<style>

</style>
