cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,
		$resource, $window, $document, $timeout, cmsService) {
	
	$scope.selectMenu1 = '新闻管理';
	$scope.selectMenu2 = '新闻列表';
	
	$scope.pageData = {
		curPage : 1,
		pageSize : 15,
		pageCount : 0,
		totalCount : 0,
		condition : {},
		datas : []
	};
	cmsService.postData(ajaxRoot + "article/query/page", $scope.pageData).then(
			function(response) {
				var result = response.data;
				console.log(result);
				if (result.success) {
					$scope.pageData = result.data.pageData;
				} else {
					onError(response);
				}
			}, onError);

});