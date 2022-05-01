<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="cu-bar  margin-top">
					<view class="action">
						<text class="cuIcon-title text-orange"></text>
						<text>欢迎您，超级管理员{{name}}!</text>
					</view>
				</view>
				<u-grid :col="2" >
						<u-grid-item @click="uploadPic()">
							<u-icon name="photo" :size="46"></u-icon>
							<view class="grid-text" >上传训练图片</view>
						</u-grid-item>
						<u-grid-item @click="ToAuth()">
							<u-icon name="lock" :size="46"></u-icon>
							<view class="grid-text">授予权限</view>
						</u-grid-item>
						<u-grid-item>
							<u-icon name="hourglass" :size="46"></u-icon>
							<view class="grid-text">模型训练</view>
						</u-grid-item>
						<u-grid-item>
							<u-icon name="weixin-fill" :size="46"></u-icon>
							<view class="grid-text">用户管理</view>
						</u-grid-item>
					</u-grid>
			</view>
		</view>
		<u-popup v-model="show" mode="center" border-radius="14" width="500">
			<view class="text-center">
				<text class="text-xl text-blue">上传模型训练图片</text>
				<u-form :model="form" ref="uForm" label-position="top">
					<u-form-item label="图片名称(格式为:分类_编号)" prop="name">
						<u-input v-model="form.name" />
					</u-form-item>
					<u-form-item label="请上传训练图片" prop="photo">
						<u-upload ref="uUpload" :action="action" max-count=1 @on-success="successSub" width="150" height="150" show-progress="false" :auto-upload="false"></u-upload>
					</u-form-item>
				</u-form>
				<button type="primary" size="mini" @tap="submit()">提交</button>
			</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show:false,
				name: this.$store.state.userinfo.nickname,
				action:this.baseUrl2 + "/admin_upload",
				form:{
					name:"",
				},
				infos:[],
				
			}
		},
		methods: {
			successSub(data, index, lists, name) {
				uni.showToast({
					title: '提交成功',
					duration: 2000
				})
				this.show = false;
				console.log(data)
			},
			uploadPic(){
				this.show = true
			},
			submit(){
				uni.request({
					url: this.baseUrl2 + "/uploadFileName",
					method: "POST",
					data: this.form,
					success: res => {
						this.$refs.uUpload.upload();
						console.log(res)
					}
				})
			},
			ToAuth(){
				uni.navigateTo({
					url:"../controller_auth/controller_auth"
				})
			}
		},
		
	}
</script>

<style>

</style>
