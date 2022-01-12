<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>商城</title>
<link href="static/forward/css/index.css" rel="stylesheet">
<link href="static/forward/css/forward-index.css" rel="stylesheet">
<link href="static/forward/css/public.css" rel="stylesheet">
<link href="static/forward/css/swiper.min.css" rel="stylesheet">
<script src="static/forward/js/jquery-3.4.1.js"></script>

<script type="text/javascript">
	var user = "${user.userId}"
	var pp = [];
	var cc=[];
	var ch=[];
	var i = 0;
    <c:forEach items="${one}" var="o">
    	pp[i] = ["${o.id}","${o.name}",0];
    	i++;
	</c:forEach>
	i=0;
	<c:forEach items="${two}" var="t">
		cc[i] = ["${t.id}","${t.name}","${t.parentId}"];
		i++;
	</c:forEach>
	i=0;
	<c:forEach items="${three}" var="th">
		ch[i] = ["${th.id}","${th.name}","${th.parentId}"];
		i++;
	</c:forEach>
	$(function() {
		$(".left-hid-s").hover(function() {
			$(this).find("li").css("display", "inline-block");
		}, function() {
			$(this).find("li").css("display", "none");
		});
		var data = [];
		if (window.localStorage) {
			var localStorage = window.localStorage;
			var lo = JSON.parse(localStorage["cartlist"]);
			for (var i = 0; i < lo.length; i++) {
				if (i == lo.length - 1) {
					data += (lo[i].id + 1) + ":" + lo[i].num;
				} else {
					data += (lo[i].id + 1) + ":" + lo[i].num + ",";
				}
			}
		}
		$(".cartaaa").click(function() {
			location.href = "cart/index/" + data;
		});
	});
</script>
</head>

<body>
	<!-- 顶部 -->
	<header class="header">
		<!-- 中间 -->
		<div class="wrap">
			<!-- 左边 -->
			<ul class="header-left">
				<li class="header-left-nav">
					<div class="header-tit">
						网站导航<span class="header-ico-sele">></span>
					</div>
					<div class="header-left-hid">
						<ul>
							<li><a href="shop.html">特色购物</a></li>
							<li><a href="shop.html">主体频道</a></li>
							<li><a href="shop.html">生活助手</a></li>
							<li><a href="shop.html">会员服务</a></li>
							<li><a href="shop.html">更多热点</a></li>
						</ul>
					</div>
				</li>
				<li class="header-left-nav">
					<div class="header-tit">
						商家入驻<span class="header-ico-sele">></span>
					</div>
					<div class="header-left-hid">
						<ul>
							<li><a href="shop.html">合作招商</a></li>
							<li><a href="shop.html">服务市场</a></li>
							<li><a href="shop.html">金融中心</a></li>
							<li><a href="shop.html">培训中心</a></li>
							<li><a href="shop.html">规则中心</a></li>
						</ul>
					</div>
				</li>
				<li class="header-left-nav">
					<div class="header-tit">
						客户服务<span class="header-ico-sele">></span>
					</div>
					<div class="header-left-hid">
						<ul>
							<li><a href="shop.html">帮助中心</a></li>
							<li><a href="shop.html">查找门店</a></li>
							<li><a href="shop.html">退换/维修</a></li>
							<li><a href="shop.html">意见反馈</a></li>
							<li><a href="shop.html">在线咨询</a></li>
						</ul>
					</div>
				</li>
				<li class="header-left-nav">
					<div class="header-tit">
						<span class="loca iconfont icon-dizhitianchong-"></span><b
							id="header-loca-di">成都</b>
					</div>
					<div class="header-left-hid" id="header-locat">
						<select id="province" onclick="provic()">
							<option value="">-请选择省-</option>
						</select> <select id="city" onclick="cityclick()">
							<option value="">-请选择市-</option>
						</select> <select id="district" onclick="dis()">
							<option value="">-请选区-</option>
						</select>
					</div>

				</li>
			</ul>
			<!-- 右边 -->
			<ul class="header-right">
				<li class="header-right-tit"><a href="forward/user/login"
					id="header-user">请登录</a></li>
				<li class="header-right-tit"><a href="forward/user/register">注册有礼</a>
				</li>
				<li class="header-right-tit"><a href="forward/order/all">我的订单</a></li>
				<li class="header-right-tit"><a href="forward/user/my">我的博汇</a></li>
				<li class="header-right-tit mycar"><a
					href="javascript:void(0);"><span
						class="cartaaa iconfont icon-gouwuche2"></span>购物车</a></li>
			</ul>
		</div>
	</header>
	<!-- 购物车 -->
	<div class="right">
		<ul class="wrap1">
			<li><a href="forward/user/my"><span
					class="iconfont icon-ren-copy"></span></a></li>
			<li><a href="javascript:void(0);" class="cartaaa"><span
					class="iconfont icon-gouwuche2"></span><span>购物车<b
						class="numc">0</b></span></a></li>
			<li><a href="shop.html"><span class="iconfont icon-shouhou1"></span><span>售后服务</span></a></li>
		</ul>
	</div>

	<!-- logo 搜索 -->
	<section class="section1 wrap">
		<div class="logo">
			<a href="index.html"><img src="${logo.address}"></a>
		</div>
		<div class="search">
			<input class="search-input" autofocus=" autofocus" type="text"
				placeholder="请输入你想要搜索的内容" value="">
			<button id="search-sea">搜索</button>
			<ul class="search-result"></ul>
		</div>
	</section>


	<!-- 商品分类 -->
	<section class="section2">
		<div class="nav wrap">
			<a href="shop.html"><span
				class="nav-li iconfont icon-tubiao1-copy"></span>全部商品分类</a> <a
				href="shop.html">限时抢购</a> <a href="shop.html">红孩子</a> <a
				href="shop.html">博汇超市</a> <a href="shop.html">电器城</a> <a
				href="shop.html">生活家电</a> <a href="shop.html">时尚服饰</a>
		</div>
		<div class="section2-center">
			<div class="wrap navleft">
				<ul class="left">
					<c:forEach items="${type}" var="t">
						<li class="left-sub"><a href="#"> ${t.name} </a>
							<ul class="left-sub-hid">
								<c:forEach items="${t.twoTypes }" var="two">
									<div class="left-hid-s">
										<p>
											<a href="#"><b>${two.name}</b></a>
										</p>
										<ul class="hid-box">
											<c:forEach items="${two.threeTypes}" var="three">
												<li style="display: none;"><a href="#">${three.name}</a></li>
											</c:forEach>
										</ul>
									</div>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 轮播图 -->
			<div class="swiper-container-bannerlun">
				<div class="swiper-wrapper">
					<c:forEach items="${shufflings}" var="s">
						<div class="swiper-slide">
							<a href="#"><img src="${s.picture}"
								style="width: 100%; height: 100%;"></a>
						</div>
					</c:forEach>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination" id="points"></div>
				<!-- Add Arrows -->
				<div class="swiper-button-next btn-right"></div>
				<div class="swiper-button-prev btn-left"></div>
			</div>

		</div>
	</section>
	<!-- 商品列表 -->
	<div>
		<div class="main-tit">
			—— <b>商品列表</b>——
		</div>
		<ul class="wrap shopwrap">

			<c:forEach items="${goods }" var="g">
				<li class="main"><a href="#"> <c:set var="count" value="1"></c:set>
						<c:forEach items="${g.pictures }" var="p">
							<c:choose>
								<c:when test="${count==1}">
									<img src="${p.picture}" alt="${g.id}">
									<c:set var="count" value="2"></c:set>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

						</c:forEach>
				</a>
					<div class="main-detail">
						<div class="detail-title">${g.name}${g.goodsDesc }</div>
						<div class="detail-price">
							<b class="price">￥${g.shopPrice}</b class="price">
							<div class="detail-car">加入购物车</div>
						</div>
					</div></li>
			</c:forEach>
		</ul>
	</div>

	<!-- 抢购列表一 -->
	<div class="main-tit">
		—— <b>抢购列表一</b>——
	</div>
	<section class="section3">
		<ul class="rush-list">
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="static/forward/images/1.png">
						<div class="get-time" data-timenow="2019-11-30,10:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="小米小爱蓝牙音箱随身版 白色">小米小爱蓝牙音箱随身版 白色</h3>
						<p>小巧便携，一键唤醒小爱</p>
						<p>
							<span>24.50元</span>
							<del>49元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="../../static/forward/images/2.png">
						<div class="get-time" data-timenow="2019-11-30,12:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="贝医生0+防蛀健齿牙膏 100克">贝医生0+防蛀健齿牙膏 100克</h3>
						<p>强健牙釉质，牙齿更坚固</p>
						<p>
							<span>1.00元</span>
							<del>12.9元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="static/forward/images/3.jpg">
						<div class="get-time" data-timenow="2019-11-30,14:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="小米路由器4C 白色">小米路由器4C 白色</h3>
						<p>小巧便携，一键唤醒小爱</p>
						<p>
							<span>24.50元</span>
							<del>49元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="static/forward/images/4.jpg">
						<div class="get-time" data-timenow="2019-12-30,16:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="小米旅行箱 青春款 20英寸 黄色 20寸">小米旅行箱 青春款 20英寸 黄色 20寸</h3>
						<p>环保材料，轻便重量</p>
						<p>
							<span>199.00元</span>
							<del>249元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="static/forward/images/5.jpg">
						<div class="get-time" data-timenow="2019-12-30,18:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="米家直流变频落地扇 白色">米家直流变频落地扇 白色</h3>
						<p>用科技创造自然风</p>
						<p>
							<span>279.00元</span>
							<del>349元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
			<li class="rush-item">
				<div class="shadow">
					<div class="sec3-img">
						<img src="../../static/forward/images/6.jpg">
						<div class="get-time" data-timenow="2019-12-30,20:00:00">
							距离抢购开始还有<br>1小时5分10秒
						</div>
					</div>
					<div class="info">
						<h3 title="烟灶套装（天然气）">烟灶套装（天然气）</h3>
						<p>点火排烟，风随火动</p>
						<p>
							<span>1999.00元</span>
							<del>2298元</del>
						</p>
						<button>即将开始</button>
					</div>
				</div>
			</li>
		</ul>
	</section>



	<div class="main-tit">
		—— <b>抢购列表二</b>——
	</div>
	<div class="wrap purch">
		<ul>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (1).jpg"> </a>
				<div class="box-right">
					<a href="shop.html"><b> 美的（Midea）</b>NPS10-15B小太阳电取暖器气家用电热扇烤火炉立式多功能节能办公室神器
						NPS10-15B </a>
					<p>
						<b class="redprice">299.00元</b><s>329.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (2).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> 科沃斯(Ecovacs)扫地机器人DL33家用<b>全自动</b>超薄智能洗擦拖地宝一体机吸尘
					</a>
					<p>
						<b class="redprice">3399.00元</b><s>4099.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (3).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> 小米(MI)<b>智能</b>床头灯<b>智能台灯</b>家居简约彩色灯泡卧室灯
					</a>
					<p>
						<b class="redprice">199.00元</b><s>236.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (4).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> <b> 九阳（Joyoung）</b>豆浆机不用手洗立体加热智能预约破壁豆浆机
						DJ06R-Kmini白
					</a>
					<p>
						<b class="redprice">399.00元</b><s>499.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (5).jpg"></a>
				<div class="box-right">
					<a href="shop.html"><b> 小度智能音箱 </b>百度智能音响 WiFi/蓝牙音箱
						、智能机器人、AI生活助手、2600+技能、带娃神器故事机 </a>
					<p>
						<b class="redprice">199.00元</b><s>299.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (6).jpg"></a>
				<div class="box-right">
					<a href="shop.html"><b> Apple 苹果 </b>iPhone 11Pro 手机 暗夜绿色 全网通
						256GB </a>
					<p>
						<b class="redprice">9488.00元</b><s>9999.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box" data-start="2019-12:11 17:24:00"
				data-start="2019-12:11 17:54:00">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (7).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> <b> 佳能（Canon）</b>EOS 800D 单反相机 单反机身 单反套机
						（EF-S 18-55mm f/4-5.6 IS STM 单反镜头）
					</a>
					<p>
						<b class="redprice">4999.00元</b><s>5299.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box"
				data-time="{start:'2019-12-11 17:24:00', end:'2019-12-11 17:54:09'}">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (8).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> <b> 凤禧珠宝</b>玉手镯满绿色翡翠玉镯子玉石玉器圆镯女款送妈妈女友生日礼物中码
					</a>
					<p>
						<b class="redprice">1599.00元</b><s>1999.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (5).jpg"></a>
				<div class="box-right">
					<a href="shop.html"><b> 小度智能音箱 </b>百度智能音响 WiFi/蓝牙音箱
						、智能机器人、AI生活助手、2600+技能、带娃神器故事机 </a>
					<p>
						<b class="redprice">199.00元</b><s>299.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (6).jpg"></a>
				<div class="box-right">
					<a href="shop.html"><b> Apple 苹果 </b>iPhone 11Pro 手机 暗夜绿色 全网通
						256GB </a>
					<p>
						<b class="redprice">9488.00元</b><s>9999.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
			<li class="box" data-start="2019-12:11 17:24:00"
				data-start="2019-12:11 17:54:00">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (7).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> <b> 佳能（Canon）</b>EOS 800D 单反相机 单反机身 单反套机
						（EF-S 18-55mm f/4-5.6 IS STM 单反镜头）
					</a>
					<p>
						<b class="redprice">4999.00元</b><s>5299.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>

			<li class="box"
				data-time="{start:'2019-12-11 17:24:00', end:'2019-12-11 17:54:09'}">
				<div class="times">
					距离抢购<b class="end">开始</b>还有
					<div class="times-sub"></div>
				</div> <a href="shop.html"><img
					src="../../static/forward/images/buy (8).jpg"></a>
				<div class="box-right">
					<a href="shop.html"> <b> 凤禧珠宝</b>玉手镯满绿色翡翠玉镯子玉石玉器圆镯女款送妈妈女友生日礼物中码
					</a>
					<p>
						<b class="redprice">1599.00元</b><s>1999.00元</s>
					</p>
					<div class="click">等待中...</div>
				</div>
			</li>
		</ul>
	</div>

	<!-- 页脚 -->
	<div class="main-tit">
		—— <b>END </b>——
	</div>
	<footer>

		<ul class="footer-top">
			<li>
				<div class="iconfont icon-wuyoushouhou"></div>
				<div class="footer-top-tit">
					<div>
						<a href="shop.html">正品保障</a>
					</div>
					<div>正品保障、提供发票</div>
				</div>
			</li>
			<li>
				<div class="iconfont icon-yunliankeji_gongyinglianfuben"></div>
				<div class="footer-top-tit">
					<div>
						<a href="shop.html">急速物流</a>
					</div>
					<div>如约送货、送货入户</div>
				</div>
			</li>
			<li>
				<div class="iconfont icon-shouhouwuyou"></div>
				<div class="footer-top-tit">
					<div>
						<a href="shop.html">售后无忧</a>
					</div>
					<div>30天包退、365天包换</div>
				</div>
			</li>
			<li>
				<div class="iconfont icon-tesefuwu"></div>
				<div class="footer-top-tit">
					<div>
						<a href="shop.html">特色服务</a>
					</div>
					<div>私人订制家电套餐</div>
				</div>
			</li>
			<li>
				<div class="iconfont icon-bangzhuzhongxin1"></div>
				<div class="footer-top-tit">
					<div>
						<a href="shop.html">帮助中心</a>
					</div>
					<div>你的购物指南</div>
				</div>
			</li>
		</ul>
		<ul class="footer-bottom">
			<li>
				<div class="footer-bottom-tit">购物指南</div>
				<ul>
					<li><a href="shop.html">保障范围</a></li>
					<li><a href="shop.html">购物流程</a></li>
					<li><a href="shop.html">会员介绍</a></li>
					<li><a href="shop.html">生活旅行</a></li>
					<li><a href="shop.html">常见问题</a></li>
					<li><a href="shop.html">联系客服购物指南</a></li>
				</ul>
			</li>
			<li>
				<div class="footer-bottom-tit">特色服务</div>
				<ul>
					<li><a href="shop.html">保障范围</a></li>
					<li><a href="shop.html">购物流程</a></li>
					<li><a href="shop.html">会员介绍</a></li>
					<li><a href="shop.html">生活旅行</a></li>
					<li><a href="shop.html">常见问题</a></li>
					<li><a href="shop.html">联系客服购物指南</a></li>
				</ul>
			</li>
			<li>
				<div class="footer-bottom-tit">帮助中心</div>
				<ul>
					<li><a href="shop.html">保障范围</a></li>
					<li><a href="shop.html">购物流程</a></li>
					<li><a href="shop.html">会员介绍</a></li>
					<li><a href="shop.html">生活旅行</a></li>
					<li><a href="shop.html">常见问题</a></li>
					<li><a href="shop.html">联系客服购物指南</a></li>
				</ul>
			</li>
			<li>
				<div class="footer-bottom-tit">新手指导</div>
				<ul>
					<li><a href="shop.html">保障范围</a></li>
					<li><a href="shop.html">购物流程</a></li>
					<li><a href="shop.html">会员介绍</a></li>
					<li><a href="shop.html">生活旅行</a></li>
					<li><a href="shop.html">常见问题</a></li>
					<li><a href="shop.html">联系客服购物指南</a></li>
				</ul>
			</li>
		</ul>
		<div class="footer-copy">
			Copyright ©2020 版权所有@博汇商城 XXX<br>
			本网站直接或间接向消费者推销商品或者服务的商业宣传均属于“广告” （包装及参数、售后保障等商品信息除外）<br> 流量统计
		</div>
	</footer>
	<script src="static/forward/js/swiper.min.js"></script>
	<script src="static/forward/js/index.js"></script>

	<!-- 素材 -->
	<script type="text/javascript">
	  var oRegion = document.getElementById("txtRegion");     //需要弹出下拉列表的文本框 
      var oDivList = document.getElementById("divList");         //要弹出的下拉列表
      var contentD = document.getElementById("contentDiv") ;
      //var oClose = document.getElementById("tdClose");   //关闭div的单元格，也可使用按钮实现 
      var QueryCode ="COPY_aibsm.enums.sm.receive.support_row" ;
      var bNoAdjusted = true; 
      var html = "" ; 
      var all_html ="" ;
      var colOptions = "" ;
      $(document).ready(function(){
             oRegion.style.background="url(/bomc3/jx/boms/busBackup/select2.jpg)  right -3px no-repeat";
             oRegion.style.backgroundColor="#fff" ;
             getJsonListFromCode(QueryCode,function(data){
                     if(data!=null&&data!=""){           // 存在查询结果 ;
                          $.each(data,function(i,e){
                                  all_html +="<li style='text-align:left; padding-left:5px;'>"+e.VALUE+"</li>" ;
                          }) ;
                    }
             },'') ;
      }) ;
      $(document).click(function (e) {          
         var target_id = $(e.target).attr('id') ;             // 获取点击dom元素id ;
         if(target_id!=oRegion.id)
         {
                 oDivList.style.display = "none";　　//隐藏div，实现关闭下拉框的效果 ;
                oRegion.style.background="url(/bomc3/jx/boms/busBackup/select2.jpg)  right -3px no-repeat";
                 oRegion.style.backgroundColor="#fff" ;
         }
      }) ;
      //设置下列选择项的一些事件 
      function setEvent(colOptions){
             for (var i=0; i<colOptions.length; i++) 
             { 
                 colOptions[i].onclick = function()　　　//为列表项添加单击事件 
                 { 
                     oRegion.value = this.innerText;     //显示选择的文本；
                     oRegion.style.backgroundColor="#219DEF" ;
                     oDivList.style.display = "none";  
                 }; 
                 colOptions[i].onmouseover = function()　//为列表项添加鼠标移动事件 
                 { 
                     this.style.backgroundColor = "#219DEF"; 
                 }; 
                 colOptions[i].onmouseout = function()  //为列表项添加鼠标移走事件 
                 { 
                     this.style.backgroundColor = ""; 
                 }; 
             } 
      }
      //文本获得焦点时的事件 
      oRegion.onfocus = function() 
      { 
          oRegion.style.background="url(/bomc3/jx/boms/busBackup/select.jpg)  right -3px no-repeat";
          oRegion.style.backgroundColor="#fff" ;
          oDivList.style.display = "block";
          if (bNoAdjusted)　　//控制div是否已经显示的变量 
          { 
              bNoAdjusted = false; 
              //设置下拉列表的宽度和位置 
              oDivList.style.width = this.offsetWidth - 4; 
              oDivList.style.posTop = oRegion.offsetTop + oRegion.offsetHeight + 1;          // 设定高度
              oDivList.style.posLeft = oRegion.offsetLeft +1 ;               // 设定与左边的位置;
          } 
      }; 
     
      // 文本内容改变时监听事件 ;
      oRegion.onpropertychange = function(){
              contentD.innerHTML ="" ; // 情况div中所有li元素;
              html ="" ;
              InitializeDIV( oRegion.value) ;
      }
      function InitializeDIV(value){
             var sql ="" ;
             if(value!=""){
                    html+= "<ul><li style='text-align:left; padding-left:3px;'>按"+'"'+"<font style='color :red;'>"+value+"</font>"+'"'+"检索:</li>";
                    sql += 'value='+value ;
             }else{
               html+= "<ul><li style='text-align:left; padding-left:3px;'>请输入检索条件:"+"</li>";
                    sql ="" ;
             }
             getJsonListFromCodeSync(QueryCode,function(data){
                     if(data!=null&&data!=""){           // 存在查询结果 ;
                          $.each(data,function(i,e){
                                  html+="<li style='text-align:left; padding-left:3px;'>"+e.VALUE+"</li>" ;
                          }) ;
                     }else{         // 没有查询结果;
                                 html ="" ;
                                 html+= "<ul><li style='text-align:left; padding-left:3px;'>无法匹配:"+'"'+"<font style='color :red;'>"+value+"</font>"+'"'+"</li>";
                                 html += all_html ;
                     }
                     html+="</ul>" ;
             },sql) ;
             contentD.innerHTML = html ;
             colOptions = $("#contentDiv li") ; //所有列表元素
             setEvent(colOptions) ;
      }

	</script>
</body>