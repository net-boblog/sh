$(function() {
				$('#search_href').click(function() {
					$("#searchForm").submit();
				});
			
				$("#failConfirm").click(function(){
					$("#update_fail").hide();
					$("#confirm_orderId").val("");
				});
				
				$(".quxiao_close").click(function(){
					$("#Confirm_goods_div").hide();
					$("#confirm_orderId").val("");
				});
				$(".cancel_window").click(function(){
					$("#Confirm_goods_div").hide();
					$("#confirm_orderId").val("");
				});
				
			});
			
			function confirm_btn(orderId){
				$("#Confirm_goods_div").show();
				$("#confirm_orderId").val(orderId);
			}