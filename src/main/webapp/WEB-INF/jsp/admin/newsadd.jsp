<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="cmsApp">
<head>
<jsp:include flush="true" page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini"
	ng-controller="${moduleCode}Controller" ng-cloak>
<div class="wrapper">
<jsp:include flush="true" page="/WEB-INF/jsp/common/header_admin.jsp"></jsp:include>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        	创建新闻
        <small>请认真搞出一个大新闻....</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">CreateNews</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Compose New News</h3>
            </div>
            <form role="form" name="myForm" novalidate>
	            <!-- /.box-header -->
	            <div class="box-body">
	              <div class="form-group">
	                <input type="text" class="form-control" name="title" ng-model="article.title" placeholder="Title:" required>
	                <span class="help-block" style="color:red" ng-show="myForm.title.$dirty && myForm.title.$invalid">Help block with error</span>
	              </div>
	              <div class="form-group">
	                    <textarea id="compose-textarea" ng-model="article.body" class="form-control" style="height: 300px">
	                      
	                    </textarea>
	              </div>
	              <div class="form-group">
	                <div class="btn btn-default btn-file">
	                  <i class="fa fa-paperclip"></i> Attachment
	                  <input type="file" name="attachment">
	                </div>
	                <p class="help-block">Max. 32MB</p>
	              </div>
	            </div>
	            <!-- /.box-body -->
	            <div class="box-footer">
	              <div class="pull-right">
	                <button type="reset" class="btn btn-default" ng-click="reset()"><i class="fa fa-pencil"></i> 重置</button>
	                <button type="submit" class="btn btn-primary" ng-click="submit()"><i class="fa fa-envelope-o"></i> 发布</button>
	              </div>
	            </div>
	            <!-- /.box-footer -->
            </form>
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>

  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<jsp:include flush="true" page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
<script>
  $(function () {
    //Add text editor
    $("#compose-textarea").wysihtml5();
  });
</script>
</body>
</html>
