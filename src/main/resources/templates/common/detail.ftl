<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>订单 详情</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-4 column">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>
							订单id
						</th>
						<th>
							订单总金额
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							${orderDTO.orderId}
						</td>
						<td>
							${orderDTO.orderAmount}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- 订单详情表数据 -->
		<div class="col-md-12 column">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>商品id</th>
						<th>商品名称</th>
						<th>价格</th>
						<th>数量</th>
						<th>总额</th>
					</tr>
				</thead>
				<tbody>
					<#list orderDTO.getOrderDetailList() as orderDetail>
						<tr>
							<td>${orderDetail.orderId}
							<td>${orderDetail.productName}</td>
							<td>${orderDetail.productPrice}</td>
							<td>${orderDetail.productQuantity}</td>
							<td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
		
		<div class="col-md-12 column">
			<#if orderDTO.getOrderStatusEnum().getMessage() == "新订单">
				 <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-primary btn-default">完结订单</a>
				 <a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}" type="button" class="btn btn-primary btn-danger">取消订单</a>
			</#if>
		</div>		
	</div>
</div>
</body>