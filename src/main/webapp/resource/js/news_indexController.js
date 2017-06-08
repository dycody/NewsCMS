cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,
		$resource, $window, $document, $timeout, cmsService) {
	console.log("22");
	$scope.queryPage = function() {
		
		console.log("111");
		
		if($scope.select_sj=="month"){
		    var startDate = new Date(year,month,1);
			$scope.startDate = $filter('date')(startDate, 'yyyy-MM-dd');
			$scope.endDate = $filter('date')(endDate, 'yyyy-MM-dd');
//			$scope.startDate = null;
//			$scope.endDate = null;
		}
		
		cmsService.postData(ajaxRoot + "article/query/page", $scope.pageData)
				.then(function(response) {
					var result = response.data;
					console.log(result);
					if (result.success) {
						$scope.pageData = result.data.pageData;
						curPageDatas = $scope.pageData.datas;
						$scope.queryStatType($scope.statType);
						assignPaginationInfo();
					} else {
						onError(response);
					}
				}, onError);
	}
	
	$scope.queryPage();

});