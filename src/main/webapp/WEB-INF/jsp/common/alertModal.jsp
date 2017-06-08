<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 	提示框 -->
<div class="modal fade fs18" id="systemAlert" tabindex="-1" role="dialog" style="z-index:999999;" aria-labelledby="systemAlertModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-border">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <span class="modal-title" id="systemAlertModalLabel">系统提示</span>
		    </div>
			<div class="modal-body text-center">
				<span id="systemAlertBody"></span>
		    </div>
		    <div>
		    	<span id="specialAlertBody"></span>
		    </div>
		    <div class="modal-footer no-border">
		    	<button id="cancelSystemConfirm" class="btn btn-default" data-dismiss="modal">关闭</button>
		    </div>
	    </div>			
	</div>
</div> 
<!-- 确认框 -->
<div class="modal fade fs18" id="systemConfirm" tabindex="-1" style="z-index:999999;" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-border">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <span class="modal-title" id="confirmModalLabel">确认提示</span>
		    </div>
			<div class="modal-body text-center">
				<span id="systemConfirmBody"></span>
			</div>
			<div class="modal-footer no-border">
		      	<button class="btn btn-info" id="doSystemConfirm" onclick="doSystemConfirm()">确定</button>
				<button class="btn" id="cancelSystemConfirm" onclick="cancelSystemConfirm()">取消</button>
		     </div>
	     </div>			
	</div>
</div>

<script type="text/javascript"
	src='<c:url value="/resource/"/>js/common.js'></script>

