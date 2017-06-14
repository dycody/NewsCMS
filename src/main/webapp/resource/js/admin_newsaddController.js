cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,$location,
		$resource, $window, $document, $timeout, cmsService) {
	
	$scope.selectMenu1 = '新闻管理';
	$scope.selectMenu2 = '新建新闻';
	var userId = 5; 
	
	$scope.article = {
			title : "",
			body : "<h3><u>我是范文样本^-^</u></h3>\
	                      <h4>小标题</h4>\
	                      <p>正文要长</p>\
	                      <ul>\
	                        <li>List item one</li>\
	                        <li>List item two</li>\
	                        <li>List item three</li>\
	                        <li>List item four</li>\
	                      </ul>\
	                      <p>Thank you</p>",
	};
	$scope.reset = function(){
		$scope.article.title = "";
		$scope.article.body = "";
	}

	$scope.submit = function() {
		
		$scope.article.body = $("#compose-textarea").val();
		$scope.article.userId = userId;
		console.log($scope.article);
		
		cmsService.postData(ajaxRoot + "article/save", $scope.article).then(
				function(response) {
					var result = response.data;
					if (result.success) {
						console.log("1111");
						window.location.href="index"; 						
					} else {
						onError(response);
					}
				},onError
		
		);
		
	};
});