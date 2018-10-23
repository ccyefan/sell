<html>

<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

	<!--边栏sidebar-->
	<#include "../common/nav.ftl" >
	
	<!--主要内容content-->
	<div id="page-content-wrapper">

		<div class="container-fluid">
			<div class="row clearfix">

				<div class="col-md-12 column">
					<form role="form" method="post" action="save">
						<div class="form-group">
							 <label for="exampleInputEmail1">名字</label>
							 <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}" />
						</div>
						<div class="form-group">
							 <label>类别编号</label>
							 <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}" />
						</div>
						<input hidden type="text" name="categoryId" value="${(category.categoryId)!''}" />
						<button type="submit" class="btn btn-default">提交</button>
					</form>
				</div>

			</div>
			
			<div class="row clearfix">
				<div class="col-md-12 column">

				</div>
			</div>
		</div>		
				
	</div>

</div>

</body>
</html>