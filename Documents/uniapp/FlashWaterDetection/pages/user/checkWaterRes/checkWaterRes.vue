<template>
	<view>
		<u-notice-bar mode="horizontal" :list="list"></u-notice-bar>
		<view class="cu-bar  margin-top">
			<view class="action">
				<text class="cuIcon-title text-orange"></text>
				<text>欢迎您，管理员{{name}}!下面是您的提交报告</text>
			</view>
		</view>
		<u-empty text="您没有提交记录" mode="history" v-if="check(infos)" icon-size="250" font-size="50"></u-empty>
		<view class="cu-card case" v-for="(info, index) in infos" :key="index">
			<view class="cu-item shadow">
				<u-tag :text="index+1" mode="dark" />
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">公司名称</text>
						</view>
						<view class="padding text-df">
							{{info.company}}
						</view>
					</view>
				</view>
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
								<text class="text-black text-bold">水质定位</text>
							</view>
							<view class="padding text-df">
								{{info.locationAddress}}
							</view>
						</view>
					</view>
					<view class="solids-bottom padding-xs flex align-center">
						<view class="flex-sub text-left">
							<view class="text-xl padding">
								<text class="text-black text-bold">是否需要向上级报备</text>
							</view>
							<u-row>
								<u-col span="6">
									<view class="demo-layout ">
										<view class="padding text-df">
											{{info.needUploadReason}}
										</view>
									</view>
								</u-col>
								<u-col span="6" text-align="right">
									<view class="demo-layout " v-if="info.needUploadReason=='是'">
										<button type="warn" size="mini" @tap="Baobei(info)">进行报备</button>
									</view>
								</u-col>
							</u-row>

						</view>
					</view>
					<view class="solids-bottom padding-xs flex align-center">
						<view class="flex-sub text-left">
							<view class="text-xl padding">
								<text class="text-black text-bold">处理状态</text>
							</view>
							<view class="padding text-df text-red">
								{{info.status}}
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
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapMutations,
	} from 'vuex'
	export default {
		data() {
			return {
				name: this.$store.state.userinfo.nickname,
				infos: [],
				list: [
					'请各公司监管人员及时上传水质报告',
					"确认水质申报状态为'已通过审核'",

				]
			}
		},
		methods: {
			...mapMutations(['setCurWaterInfo']),
			getData() {
				uni.request({
					method: 'GET',
					url: this.baseUrl + '/' + this.$store.state.openid + '/getWaterRes',
					success: (res) => {
						this.infos = res.data.data
					}
				})
			},
			Baobei(info) {
				this.setCurWaterInfo(info)
				uni.navigateTo({
					url: "../uploadReasonOne/uploadReasonOne"
				})
			},
			check(obj) {
				var name;
				for (name in obj) {
					return false;
				}
				return true;
			},
		},
		onLoad() {
			this.getData()
			uni.$once('refreshData', () => {
				this.getData();
			})
		}
	}
</script>

<style>

</style>
