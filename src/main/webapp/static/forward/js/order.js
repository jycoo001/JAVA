$(function() {
	$(".goodsDesc").click(function() {
		var id = $(this).closest("tr").find("td:nth-child(1)").text();
		console.log(id);
		$(".orderDesc input[name=id]").val(id);
		$.ajax({
			url: "forward/order/desc-page",
			method: "post",
			data: {
				id: id,
			},
			dataType: "json",
			tranditional: true,
			success: function(map) {
				//实例就绪事件
				ue.ready(function() {
					this.setContent(map.order.desc);
				});
			}
		});
		$(".orderDesc").removeClass("hidden");
	});

	$(".fail").click(function() {
		console.log("join in 取消");
		layer.alert("已取消");
		$(".orderDesc").addClass("hidden");
	});
});