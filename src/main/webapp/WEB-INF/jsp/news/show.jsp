<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="cmsApp">
<head>
<jsp:include flush="true" page="/WEB-INF/jsp/common/head.jsp" />
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav"
	ng-controller="${moduleCode}Controller" ng-cloak>
	<div class="wrapper">
		<jsp:include flush="true" page="/WEB-INF/jsp/common/header_news.jsp" />

		<!-- Full Width Column -->
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>总想搞个大新闻......</h1>
					<ol class="breadcrumb">
						<li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active"><a href="show">News</a></li>
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					<div class="row clearfix">
						<div class="col-xs-12">
							<div class="jumbotron well">
								<h1 align="center">{{article.title}}</h1>
								<P align="center">{{article.updateUserName}}&nbsp;&nbsp;{{article.updateDate}}</P>
								<p ng-bind-html="article.body"></p>
								<p align="right">
									<a class="btn btn-primary btn-large"  href="#">参与回复</a>
								</p>
							</div>
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.container -->
		</div>

	</div>
	<jsp:include flush="true" page="/WEB-INF/jsp/common/footer.jsp" />
</body>
</html>
