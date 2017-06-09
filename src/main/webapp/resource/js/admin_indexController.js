cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,
		$resource, $window, $document, $timeout, cmsService) {
	$scope.pageData = {
		curPage : 1,
		pageSize : 10,
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