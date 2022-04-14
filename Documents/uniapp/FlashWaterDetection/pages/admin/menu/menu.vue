<template>
	<view>
		<u-notice-bar mode="horizontal" :list="anotation"></u-notice-bar>
		<u-select mode="single-column" v-model="show" :list="companys" @confirm="confirm"></u-select>
		<view>
			<u-tabs-swiper ref="uTabs" :list="list" :current="current" @change="tabsChange" :is-scroll="false"
				swiperWidth="750" :bold="false"></u-tabs-swiper>
		</view>
		<swiper :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish"
			:style="[{height: swiperHeight+ 'rpx'}]">
			<swiper-item class="swiper-item">
				<view class="swiper-item-content">
					<scroll-view style="height: 100%;width: 100%;" @scrolltolower="onreachBottom">
						<u-empty text="无提交记录" mode="history" v-if="check(infos1)" icon-size="250" font-size="50">
						</u-empty>
						<view class="cu-card case" v-for="(info, index) in infos1" :key="index">
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
								<view class="solids-bottom padding-xs flex align-center">
									<view class="flex-sub text-left">
										<view class="text-xl padding">
											<text class="text-black text-bold">提交人</text>
										</view>
										<view class="padding text-df">
											{{info.nickname}}
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
												<text class="text-black text-bold">首次提交水质系统评价</text>
											</view>
											<view class="padding text-df">
												{{info.firstJudge}}
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
												<text class="text-black text-bold">水质超标原因报告</text>
											</view>
											<view class="padding text-df text-blue">
												{{info.reason}}
											</view>
										</view>
									</view>
									<view class="solids-bottom padding-xs flex align-center">
										<view class="flex-sub text-left">
											<view class="text-xl padding">
												<text class="text-black text-bold">水质超标解决方案</text>
											</view>
											<view class="padding text-df text-blue">
												{{info.reason}}
											</view>
										</view>
									</view>
									<view class="solids-bottom padding-xs flex align-center">
										<view class="flex-sub text-left">
											<view class="text-xl padding">
												<text class="text-black text-bold">再次提交后水质系统评价</text>
											</view>
											<view class="padding text-df text-red">
												{{info.secondJudge}}
											</view>
										</view>
									</view>
									<view class="solids-bottom padding-xs flex align-center">
										<view class="flex-sub text-left">
											<view class="text-xl padding">
												<text class="text-black text-bold">首次提交时间</text>
											</view>
											<view class="padding text-df">
												{{info.createdF}}
											</view>
										</view>
									</view>
									<view class="solids-bottom padding-xs flex align-center">
										<view class="flex-sub text-left">
											<view class="text-xl padding">
												<text class="text-black text-bold">再次提交时间</text>
											</view>
											<view class="padding text-df">
												{{info.createdS}}
											</view>
										</view>
									</view>
									<u-button type="primary" @click="deal(info.id,info.id2,index)">进行处理</u-button>
								</view>
							</view>
						</view>
					</scroll-view>
				</view>
			</swiper-item>
			<swiper-item class="swiper-item">
				<scroll-view scroll-y style="height: 100%;width: 100%;" @scrolltolower="onreachBottom">
					<view>
						<u-button @click="show = true" type="primary">请选择公司</u-button>
						<scroll-view style="height: 100%;width: 100%;" @scrolltolower="onreachBottom">
							<view class="cu-card case" v-for="(info, index) in infos3" :key="index">
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
									<view class="solids-bottom padding-xs flex align-center">
										<view class="flex-sub text-left">
											<view class="text-xl padding">
												<text class="text-black text-bold">提交人</text>
											</view>
											<view class="padding text-df">
												{{info.nickname}}
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
													<text class="text-black text-bold">首次提交水质系统评价</text>
												</view>
												<view class="padding text-df text-yellow">
													{{info.firstJudge}}
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
													<text class="text-black text-bold">水质超标原因报告</text>
												</view>
												<view class="padding text-df text-blue">
													{{info.reason}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold">水质超标解决方案</text>
												</view>
												<view class="padding text-df text-blue">
													{{info.reason}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold ">再次提交后水质系统评价</text>
												</view>
												<view class="padding text-df text-red">
													{{info.secondJudge}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold">首次提交时间</text>
												</view>
												<view class="padding text-df">
													{{info.createdF}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold">再次提交时间</text>
												</view>
												<view class="padding text-df">
													{{info.createdS==null?'无提交记录':info.createdS}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold">审核状态</text>
												</view>
												<view class="padding text-df text-purple">
													{{info.status}}
												</view>
											</view>
										</view>
										<view class="solids-bottom padding-xs flex align-center">
											<view class="flex-sub text-left">
												<view class="text-xl padding">
													<text class="text-black text-bold">审核记录</text>
												</view>
												<view class="padding text-df">
													{{info.record}}
												</view>
											</view>
										</view>

									</view>
								</view>
							</view>
						</scroll-view>
					</view>
				</scroll-view>
			</swiper-item>
		</swiper>
		<view>
			<u-popup v-model="pp" mode="center" border-radius="14" width="500" closeable="true">
				<view class="text-center">
					<text class="text-xl text-brown">审核</text>
					<u-form :model="form" ref="uForm" label-position="top">
						<u-form-item label="审核结果" prop="record">
							<u-input v-model="form.record" type="text" placeholder="请输入审核结果" />
						</u-form-item>
						<u-radio-group v-model="form.status" @change="radioGroupChange">
							<u-radio @change="radioChange" v-for="(item, index) in conf" :key="index" :name="item"
								shape="square">
								{{item}}
							</u-radio>
						</u-radio-group>
					</u-form>
					<button type="primary" size="mini" @tap="submit()">提交</button>
				</view>

			</u-popup>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					record: '',
					status: '通过',
					id:'',
					id2:''
				},
				index_t:'',
				pp: false,
				show: false,
				swiperHeight: 0,
				infos1: [],
				infos2: [],
				infos3: [],
				companys: [],
				rules: {
					record: [{
						required: true,
						message: '请输入审核结果',
						trigger: 'blur'
					}],
				},
				anotation: [
					'请各分管负责人及时处理水质报告',
					"并提出合理建议",

				],
				conf: [
					"通过", "不通过"
				],
				list: [{
					name: '查看待处理报告'
				}, {
					name: '查看所有水质报告'
				}],
				// 因为内部的滑动机制限制，请将tabs组件和swiper组件的current用不同变量赋值
				current: 0, // tabs组件的current值，表示当前活动的tab选项
				swiperCurrent: 0, // swiper组件的current值，表示当前那个swiper-item是活动的
			};
		},
		methods: {
			check(obj) {
				var name;
				for (name in obj) {
					return false;
				}
				return true;
			},
			confirm(e) {
				let str = e[0].label
				var i = 0
				this.infos3.length = 0
				for (i; i < this.infos2.length; i++) {
					if (this.infos2[i].company == str) {
						this.infos3.push(this.infos2[i])
					}
				}
				this.swiperHeight = this.infos3.length * 2600;
			},
			getData() {
				uni.request({
					method: 'GET',
					url: this.baseUrl + '/' + this.$store.state.openid + '/adminGetInfo',
					success: (res) => {
						this.infos1 = res.data.data
						this.swiperHeight = this.infos1.length * 2400
					}
				})
				uni.request({
					method: 'GET',
					url: this.baseUrl + '/' + this.$store.state.openid + '/adminGetInfoForAll',
					success: (res) => {
						this.infos2 = res.data.data
						// this.swiperHeight2 = this.infos2.length * 2400
						this.solution()
					}
				})
			},
			solution() {
				var i = 0;
				for (i; i < this.infos2.length; i++) {
					this.companys.push({
						value: i + 1,
						label: this.infos2[i].company
					})
					console.log(this.infos2[i].company)
				}
				console.log(this.companys)
			},
			deal(id,id2,index) {
				this.pp = true
				this.form.id = id
				this.form.id2 = id2
				this.index_t = index
			},
			submit() {
				let that = this
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.showModal({
							title: '提示',
							content: '确认提交',
							success: function(res) {
								if (res.confirm) {
									uni.request({
										url: that.baseUrl + "/" + that.$store.state.openid +
											"/admin/deal",
										method: "POST",
										data: that.form,
										success: (res) => {
											if (res.data.msg == "操作成功") {
												uni.showToast({
													title: '提交成功',
													icon: "success",
													duration: 2000,

												})
												that.infos1.splice(that.index_t,1)
												that.swiperHeight-=2400
												that.pp = false
											} else {
												uni.showToast({
													title: '提交失败',
													icon: "error",
													duration: 2000,
												})
											}
										}
									})

								} else if (res.cancel) {
									console.log('用户点击取消');
								}
							}
						});
					} else {
						console.log('验证失败');
					}
				});

			},
			radioChange(e) {
				// console.log(e);
			},
			// 选中任一radio时，由radio-group触发
			radioGroupChange(e) {
				// console.log(e);
			},
			// tabs通知swiper切换
			tabsChange(index) {
				this.swiperCurrent = index;
			},
			// swiper-item左右移动，通知tabs的滑块跟随移动
			transition(e) {
				let dx = e.detail.dx;
				this.$refs.uTabs.setDx(dx);
			},
			// 由于swiper的内部机制问题，快速切换swiper不会触发dx的连续变化，需要在结束时重置状态
			// swiper滑动结束，分别设置tabs和swiper的状态
			animationfinish(e) {
				let current = e.detail.current;
				this.$refs.uTabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			},
			// scroll-view到底部加载更多
			onreachBottom() {

			}
		},
		watch: {
			swiperCurrent: {
				handler(newName, oldName) {
					if (newName == 0) {
						this.swiperHeight = this.infos1.length * 2400
					} else {
						this.infos3.length = 0
						this.swiperHeight = 150
					}

				},
				immediate: true
			},
			swiperHeight: {
				handler(newName, oldName) {
					if (newName == 0) {
						this.swiperHeight=1000
					}
			
				},
				immediate: true
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		onLoad() {
			this.getData();
		}
	};
</script>
