<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>sell</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- <h1>${orderDTOPage.totalPages}</h1>
<h1>${orderDTOPage.getTotalPages()}</h1> -->
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-bordered">
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
							<td><a>详情</a></td>
							<td><a>取消</a></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
	</div>    
</body>
</html>