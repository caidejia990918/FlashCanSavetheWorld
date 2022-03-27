<template>
	<view>
		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="text-center">
					<text class="text-xxl text-bold ">检测样本示例</text>
				</view>

				<swiper class="screen-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true"
					:circular="true" :autoplay="true" interval="5000" duration="500">
					<swiper-item v-for="(item,index) in swiperList" :key="index">
						<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
						<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false"
							objectFit="cover" v-if="item.type=='video'"></video>
						<text>12312</text>
					</swiper-item>
				</swiper>


			</view>
		</view>

		<view class="cu-card case">
			<view class="cu-item shadow">
				<view class="text-center">
					<text class="text-xxl text-bold">小程序简介</text>
				</view>
				<view class="solids-bottom padding-s flex ">
					<view class="flex-sub text-left">
						<view class="text-df padding text-xl">水资源是生活及生存不可或缺的资源，然而随着社会发展带来的生活水平的提高，人们对水的需求量也逐渐的增大，工业污水和生活废水却又不断污染着可以利用的水资源，使本来就匮乏的生活用水面临更严峻的挑战。因此，如何减轻水污染以及如何判断水质的优劣成为了人们日益关注的问题。该小程序立足于机器学习中的深度强化学习方法来实现水质监测微算法，实现水质监测模型的参数寻优。</view>
						
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
				cardCur: 0,
				src: '/static/img/index/beatifulwater.jpeg',
				
				swiperList: [{
					id: 0,
					type: 'image',
					url: "../../static/img/index/1.jpg"
				}, {
					id: 1,
					type: 'image',
					url: '../../static/img/index/2.jpg',
				}, {
					id: 2,
					type: 'image',
					url: '../../static/img/index/3.jpg'
				}, {
					id: 3,
					type: 'image',
					url: '../../static/img/index/4.jpg'
				}, {
					id: 4,
					type: 'image',
					url: '../../static/img/index/5.jpg'
				}],
				dotStyle: false,
				towerStart: 0,
				direction: '',


			}
		},
		onLoad() {
			this.TowerSwiper('swiperList');
			this.login();
		},
		methods: {
			...mapActions(['login', 'authUserInfo']),
			DotStyle(e) {
				this.dotStyle = e.detail.value
			},
			// cardSwiper
			cardSwiper(e) {
				this.cardCur = e.detail.current
			},
			// towerSwiper
			// 初始化towerSwiper
			TowerSwiper(name) {
				let list = this[name];
				for (let i = 0; i < list.length; i++) {
					list[i].zIndex = parseInt(list.length / 2) + 1 - Math.abs(i - parseInt(list.length / 2))
					list[i].mLeft = i - parseInt(list.length / 2)
				}
				this.swiperList = list
			},

			// towerSwiper触摸开始
			TowerStart(e) {
				this.towerStart = e.touches[0].pageX
			},

			// towerSwiper计算方向
			TowerMove(e) {
				this.direction = e.touches[0].pageX - this.towerStart > 0 ? 'right' : 'left'
			},

			// towerSwiper计算滚动
			TowerEnd(e) {
				let direction = this.direction;
				let list = this.swiperList;
				if (direction == 'right') {
					let mLeft = list[0].mLeft;
					let zIndex = list[0].zIndex;
					for (let i = 1; i < this.swiperList.length; i++) {
						this.swiperList[i - 1].mLeft = this.swiperList[i].mLeft
						this.swiperList[i - 1].zIndex = this.swiperList[i].zIndex
					}
					this.swiperList[list.length - 1].mLeft = mLeft;
					this.swiperList[list.length - 1].zIndex = zIndex;
				} else {
					let mLeft = list[list.length - 1].mLeft;
					let zIndex = list[list.length - 1].zIndex;
					for (let i = this.swiperList.length - 1; i > 0; i--) {
						this.swiperList[i].mLeft = this.swiperList[i - 1].mLeft
						this.swiperList[i].zIndex = this.swiperList[i - 1].zIndex
					}
					this.swiperList[0].mLeft = mLeft;
					this.swiperList[0].zIndex = zIndex;
				}
				this.direction = ""
				this.swiperList = this.swiperList
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
