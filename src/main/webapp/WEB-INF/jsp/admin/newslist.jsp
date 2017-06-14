<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="cmsApp">
<head>
<jsp:include flush="true" page="/WEB-INF/jsp/common/head.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini"
	ng-controller="${moduleCode}Controller" ng-cloak>
	<div class="wrapper">
		<jsp:include flush="true" page="/WEB-INF/jsp/common/header_admin.jsp" />

		<!-- Full Width Column -->
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>总想搞个大新闻......</h1>
					<ol class="breadcrumb">
						<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active"><a href="show">NewsList</a></li>
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					
					
					
					
					<div class="row">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<i class="ion ion-clipboard"></i>
									<h3 class="box-title">新闻列表</h3>

									<div class="box-tools">
										<div class="input-group input-group-sm" style="width: 150px;">
											<input type="text" name="table_search"
												class="form-control pull-right" placeholder="Search">

											<div class="input-group-btn">
												<button type="submit" class="btn btn-default">
													<i class="fa fa-search"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
								<!-- /.box-header -->
								<div class="box-body table-responsive no-padding">
									<table class="table table-hover">
										<thead>
											<tr>
												
												<th width="60%">新闻标题</th>
												<th width="20%">发布人</th>
												<th width="20%">发布时间</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="x in pageData.datas">
												<td><a href="show?id={{x.articleId}}">{{ x.title }}</a></td>
												<td>
												<span class="label label-danger" ng-if="x.updateUserType==0">{{ x.updateUserName }}</span>
												<span class="label label-primary" ng-if="x.updateUserType!=0">{{ x.updateUserName }}</span>
												</td>
												<td>{{ x.updateDate }}</td>
											</tr>

										</tbody>
									</table>
								</div>
								<!-- /.box-body -->
								<div class="box-footer clearfix">
									<ul class="pagination pagination-sm no-margin pull-right">
										<li><a href="#">«</a></li>
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">»</a></li>
									</ul>
								</div>
							</div>
							<!-- /.box -->
						</div>
					</div>



					<!-- /.box -->
				</section>
				<!-- /.content -->
			</div>
			<!-- /.container -->
		</div>

	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/common/footer.jsp" />
</body>
</html>
