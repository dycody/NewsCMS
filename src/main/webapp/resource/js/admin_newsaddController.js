cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,
		$resource, $window, $document, $timeout, cmsService) {
	
	$scope.reset = function(){
		$scope.article.title = "";
		$scope.article.body = "";
	}

	$scope.submit = function() {
		
	};
});