<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">描述</text>
						</view>
						<view class="padding text-df">
							{{info.description}}
						</view>
					</view>
				</view>
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">水质评价</text>
						</view>
						<view class="padding text-df">
							{{info.waterJudge}}
						</view>
					</view>
				</view>
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">提交时间</text>
						</view>
						<view class="padding text-df">
							{{info.created}}
						</view>
					</view>
				</view>
				<view class="cu-form-group align-start">
					<view class="title">请上传处理后的水质图片</view>
					<u-upload :action="action" max-count=1 @on-success="successSub" width="150" height="150"
						show-progress="false"></u-upload>
				</view>
				<view class="cu-form-group align-start">
					<view class="title">超标原因</view>
					<textarea maxlength="-1" @input="textareaAInput" placeholder="请输入超标原因"></textarea>
				</view>
				<view class="cu-form-group align-start">
					<view class="title">解决方案</view>
					<textarea maxlength="-1" @input="textareaBInput" placeholder="请输入解决方案"></textarea>
				</view>
				<button type="primary" @tap="Submit() ">提交申报</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				fuck: false,
				form: {
					reason: '',
					solution: '',
					waterJudge: '',
					description: '',
					infoId: '',
					company: '',

				},
				info: this.$store.state.curWaterInfo,
				action: this.baseUrl2 + "/upload"
			}
		},
		methods: {
			successSub(data, index, lists, name) {
				this.form.waterJudge = data
				uni.showToast({
					title: '提交成功',
					duration: 2000
				})
				console.log(data)
			},
			textareaAInput(e) {
				this.form.reason = e.detail.value
			},
			textareaBInput(e) {
				this.form.solution = e.detail.value
			},
			check(obj) {
				var name;
				for (name in obj) {
					return true;
				}
				return false;
			},
			Submit() {
				this.form.description = this.info.description
				this.form.infoId = this.info.id
				this.form.company = this.info.company
				if (this.check(this.form.waterJudge) && this.check(this.form.reason) && this.check(this.form.solution)) {
					uni.request({
						method: "POST",
						data: this.form,
						url: this.baseUrl + "/" + this.$store.state.openid + "/uploadReason",
						success: (res) => {
							if (res.data.msg == "提交成功") {
								uni.$emit('refreshData');
								uni.navigateBack({
									delta:1,
									animationDuration: 500
								})
								uni.showToast({
									title: '提交成功',
									icon: "success",
									duration: 1000
								})
							} else {
								uni.showToast({
									title: '错误，请重试',
									icon: "error",
									duration: 2000
								})
							}
						}
					})
				} else {
					uni.showToast({
						title: '请填写完整信息',
						icon: "error",
						duration: 2000
					})
				}

			}

		}
	}
</script>

<style>

</style>
