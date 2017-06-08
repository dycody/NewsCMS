<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!-- /.content-wrapper -->
<footer class="main-footer">
	<div class="pull-right hidden-xs">
		<b>Version</b> 1.0.0
	</div>
	<strong>Copyright &copy; 2017 <a
		href="">WZU-network</a>.
	</strong> All rights reserved.
</footer>


<!-- jQuery 2.2.3 -->
<script	src="<c:url value="/resource/"/>plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<c:url value="/resource/"/>plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button);
</script>
<script src="<c:url value="/resource/"/>jquery/jquery-1.11.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<c:url value="/resource/"/>bootstrap/js/bootstrap.min.js"></script>
<script src="<c:url value="/resource/"/>bootstrap/js/bootstrap-select.min.js"></script>
<!-- Morris.js charts -->
<script	src="<c:url value="/resource/"/>plugins/morris/raphael-min.js"></script>
<script src="<c:url value="/resource/"/>plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script	src="<c:url value="/resource/"/>plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script	src="<c:url value="/resource/"/>plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script	src="<c:url value="/resource/"/>plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="<c:url value="/resource/"/>plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script	src="<c:url value="/resource/"/>plugins/daterangepicker/moment.min.js"></script>
<script	src="<c:url value="/resource/"/>plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script	src="<c:url value="/resource/"/>plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script	src="<c:url value="/resource/"/>plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script	src="<c:url value="/resource/"/>plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<c:url value="/resource/"/>plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/resource/"/>dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/resource/"/>dist/js/demo.js"></script>


<script type="text/javascript" src="<c:url value="/resource/"/>js/angular-1.3.13/angular.js"></script>
<script type="text/javascript" src="<c:url value="/resource/"/>js/angular-1.3.13/ui/ui-bootstrap-tpls-0.13.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resource/"/>js/angular-1.3.13/angular-sanitize.js"></script>
<script type="text/javascript" src="<c:url value="/resource/"/>js/angular-1.3.13/angular-resource.min.js"></script>
<script type="text/javascript" src="<c:url value="/resource/"/>js/angular-1.3.13/angular-chosen.min.js"></script>

<jsp:include flush="true" page="/WEB-INF/jsp/common/alertModal.jsp" /> 
<script type="text/javascript" src="<c:url value="/resource/"/>js/cmsApp.js"></script>
<script type="text/javascript" src="<c:url value="/resource/"/>js/${moduleCode}Controller.js"></script>

