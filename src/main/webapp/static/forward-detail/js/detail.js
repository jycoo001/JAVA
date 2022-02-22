$(function() {
	//轮播
	var mySwiper = new Swiper('.swiper', {
		direction: 'horizontal',
		loop: true,
		pagination: {
			el: '.swiper-pagination',
			clickable: true,
			renderBullet: function(index, className) {
				return '<span class="' + className + '">' + (index + 1) + '</span>';
			}
		},
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});
	
	//导航条的点击li事件
	$(".navigation>.container ul").on("click","li",function() {
		var url = $(this).attr("data-url");
		location.href=path+url;
	});
	
	$(window).scroll(function() { //开始监听滚动条
		var top = $(window).scrollTop();
		if (top < 70) {
			$(".hidden").css("display", "inline-block");
		} else if (top >= 70) {
			$(".hidden").css("display", "inline-block");
		}
	});
});
