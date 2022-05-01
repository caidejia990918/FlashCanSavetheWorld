<template>
	<view>
		<view class="cu-card case" v-for="(info, index) in infos" :key="index">
			<view class="cu-item shadow">
				<u-tag :text="index+1" mode="dark" />
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">用户昵称</text>
						</view>
						<view class="padding text-df">
							{{info.nickname}}
						</view>
					</view>
				</view>
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">openid</text>
						</view>
						<view class="padding text-df">
							{{info.openid}}
						</view>
					</view>
				</view>
				<view class="solids-bottom padding-xs flex align-center">
					<view class="flex-sub text-left">
						<view class="text-xl padding">
							<text class="text-black text-bold">权限</text>
						</view>
						<view class="padding text-df">
							{{info.rule}}
						</view>
					</view>
				</view>
				<view class="text-center">
					<u-button type="primary" size="medium" @click="toShow(index)">修改权限</u-button>
				</view>
			</view>
		</view>
		<u-select v-model="show" mode="single-column" :list="list" @confirm="confirm"></u-select>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				show_s: false,
				temp:'',
				infos: [],
				list: [{
						value: '0',
						label: '环境经理(0)'
					},
					{
						value: '1',
						label: '分管负责人(1)'
					}
				],

			}
		},
		methods: {
			toShow(tt) {
				this.temp = tt
				this.show = true
			},
			confirm(e) {
				this.infos[this.temp].rule = e[0].value
				uni.request({
					method:"POST",
					data: {
						auth : e[0].value,
						openid : this.infos[this.temp].openid,
					},
					url: this.baseUrl + '/wx/user/' + this.$store.state.openid + '/changeAuth',
				})
				console.log(e[0].value);
			},
			getData() {
				uni.request({
					method: 'GET',
					url: this.baseUrl + '/wx/user/' + this.$store.state.openid + '/getData',
					success: (res) => {
						this.infos = res.data.data
						console.log(this.infos)
					}
				})
			},
		},
		onLoad() {
			this.getData();
		}
	}
</script>

<style>

</style>
