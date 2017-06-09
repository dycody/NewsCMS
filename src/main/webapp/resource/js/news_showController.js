cmsApp.controller(moduleCode + 'Controller', function($scope, $http, $filter,
		$resource, $window, $document, $timeout, cmsService) {

	$scope.article = {};
	cmsService.postData(ajaxRoot + "article/query/id", {
		"articleId" : articleId
	}).then(function(response) {
		var result = response.data;
		console.log(result);
		if (result.success) {
			$scope.article = result.data.instance;
		} else {
			onError(response);
		}
	}, onError);

});