<html>

<#include "../common/header.ftl">

<body>
<#-- <h1>${orderDTOPage.totalPages}</h1>
<h1>${orderDTOPage.getTotalPages()}</h1> -->

<div id="wrapper" class="toggled">

	<!--边栏sidebar-->
	<#include "../common/nav.ftl" >
	
	<!--主要内容content-->
	<div id="page-content-wrapper">

		<div class="container-fluid">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>订单id</th>
								<th>姓名</th>
								<th>手机号</th>
								<th>地址</th>
								<th>金额</th>
								<th>订单状态</th>
								<th>支付状态</th>
								<th>创建时间</th>
								<th colspan = "2">操作</th>
							</tr>
						</thead>
						<tbody>
							<#list orderDTOPage.content as orderDTO>
							<tr>
								<td>${orderDTO.orderId}</td>
								<td>${orderDTO.buyerName}</td>
								<td>${orderDTO.buyerPhone}</td>
								<td>${orderDTO.buyerAddress}</td>
								<td>${orderDTO.orderAmount}</td>
								<td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
								<td>${orderDTO.getPayStatusEnum().getMessage()}</td>
								<td>]${orderDTO.createTime}</td>
								<td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
								<td>
									<#if orderDTO.getOrderStatusEnum().getMessage() == "新订单">
										<a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}">取消</a>
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="row clearfix">
				<div class="col-md-12 column">
					<ul class="pagination pull-right">
						<#if currentPage lte 1>
							<li class="disabled">
								 <a href="#">上一页</a>
							</li>
						<#else>
							<li>
								 <a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
							</li>
						</#if>
						<#list 1..orderDTOPage.getTotalPages() as index>
							<#if currentPage == index>
								<li class = "disabled">
									 <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
								</li>
							<#else>
								<li>
									 <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
								</li>
							</#if>
						</#list>
						<#if currentPage gte orderDTOPage.totalPages>
							<li class="disabled">
								 <a href="#">下一页</a>
							</li>
						<#else>
							<li>
								 <a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}"">下一页</a>
							</li>
						</#if>
					</ul>
				</div>
			</div>
		</div>		
				
	</div>

</div>

</body>
</html>