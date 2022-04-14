<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<u-form :model="form" ref="uForm" label-position="top">
					<u-form-item label="公司" prop="company">
						<u-input v-model="form.company" />
					</u-form-item>
					<u-form-item label="描述" prop="description">
						<u-input v-model="form.description" />
					</u-form-item>
					<u-form-item label="请上传水质图片" prop="photo">
						<u-upload :action="action" max-count=1 @on-success="successSub" width="150" height="150" show-progress="false"></u-upload>
					</u-form-item>
				</u-form>
				<view class="uni-padding-wrap">
					<view style="background:#FFFFFF; padding:40rpx;">
						<view class="uni-hello-text uni-center">当前位置信息</view>
						<block v-if="hasLocation === false">
							<view class="uni-h2 uni-center uni-common-mt">未选择位置</view>
						</block>
						<block v-if="hasLocation === true">
							<view class="uni-hello-text uni-center" style="margin-top:10px;">
								{{form.locationAddress}}
							</view>
							<view class="uni-h2 uni-center uni-common-mt">
								<text>E: {{location.longitude[0]}}°{{location.longitude[1]}}′</text>
								<text>\nN: {{location.latitude[0]}}°{{location.latitude[1]}}′</text>
							</view>
						</block>
					</view>
					<view style="text-align: center;">
						<button type="primary" @tap="chooseLocation" size="mini">选择位置</button>
						<button @tap="clear" size="mini" type="warn">清空</button>
			
					</view>
					
					
				</view>
				<u-button @click="submit" type="primary">提交</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	import * as util from '../../../common/util.js'
	var formatLocation = util.formatLocation;

	export default {
		data() {
			return {
				title: 'chooseLocation',
				hasLocation: false,
				location: {},
				needUpload: true,
				action:this.baseUrl2 + "/upload",
				form: {
					company: '',
					description: '',
					waterJudge: '',
					locationAddress:''
				},
				rules: {
					company: [{
						required: true,
						message: '请输入公司姓名',
						trigger: 'blur'
					}],
					description: [{
						required: true,
						message: '请输入图片描述',
						trigger: 'blur'
					}],
				}
			}
		},
		methods: {
			successSub(data, index, lists, name) {
				this.form.waterJudge = data
				this.needUpload = false
				uni.showToast({
					title: '提交成功',
					duration: 2000
				})

				console.log(data)
			},
			submit() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						if (this.needUpload == true||this.hasLocation==false) {
							uni.showToast({
								title: '请上传所有信息',
								icon: "error",
								duration: 2000
							})
						} else {
							uni.request({
								url: this.baseUrl + "/" + this.$store.state.openid + "/declare",
								method: "POST",
								data: this.form,
								success: res => {
									uni.navigateBack({
										delta:1,
										animationDuration: 500
									})
									if(res.data.msg=="上传成功"){
										uni.showToast({
											title: '上传成功',
											icon: "success",
											duration: 1000
										})
									}else{
										uni.showToast({
											title: '错误，请重试',
											icon: "error",
											duration: 2000
										})
									}
								}
							})
						}

					} else {

					}
				});

			},
			chooseLocation: function() {
				uni.chooseLocation({
					success: (res) => {
						this.hasLocation = true,
						this.location = formatLocation(res.longitude, res.latitude),
						this.form.locationAddress = res.address
					}
				})
			},
			clear: function() {
				this.hasLocation = false
			}
		},

		onReady() {
			this.$refs.uForm.setRules(this.rules);
		}
	}
</script>

<style>

</style>
