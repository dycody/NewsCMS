var cmsApp=angular.module('cmsApp',['ngResource', 'ui.bootstrap', 'ngSanitize', 'localytics.directives' ]).filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    };
});

/*cmsApp.filter('choosenFilter', function() {  
	   return function(input, attrName, param) {  
	      console.log("------------------------------------------------- begin dump of custom parameters");  
	      console.log("input=",input);  
	      console.log("attrName(string)=", attrName); 
	      console.log("param(string)=", param);  
	      return {10:'cjj',20:'ert',30:'sdf'};
	      var result = new Array();
	      if (input.length>0 && param instanceof Array && param.length>0) {
	    	  for (var data in input) {
	    		  if (data[attrName]) {
	    			  for (var id in param) {
	    				  if (data[attrName] === id) {
	    					  result.push(data);
	    					  break;
	    				  }
	    			  }
	    		  }
	    	  }
	      }
	      if (result.length>0) {
	    	  return result;
	      }
	      return input;  
	   };  
	 });*/

cmsApp.directive('contenteditable', function() {
    return {
        restrict: 'A', 
        require: '?ngModel', 
        link: function(scope, element, attrs, ngModel) {
          if(!ngModel) return; 

          ngModel.$render = function() {
            element.html(ngModel.$viewValue || '');
          };

          element.on('blur keyup change', function() {
            scope.$apply(read);
          });
          read(); 

          function read() {
            var html = element.html();
            if( attrs.stripBr && html == '<br>' ) {
              html = '';
            }
            ngModel.$setViewValue(html);
          }
        }
      };
    });
cmsApp.service('cmsService', function($http) {
	this.getData = function(URL,params) {
		//console.log("Inside ajaxService GET...");
		//console.log("Connection using URL=[" + URL + "]");
		if(URL){
			if(URL.indexOf("?")!=-1){
				if(URL.indexOf("?")==URL.length-1){
					URL += "moduleCode="+moduleCode+"&timestamp="+new Date().getTime();
				}else{
					URL += "&moduleCode="+moduleCode+"&timestamp="+new Date().getTime();
				}
			}else{
				URL += "?moduleCode="+moduleCode+"&timestamp="+new Date().getTime();
			}
		}
		var ajaxObject = {
			url : URL
		};
		if(params instanceof Object){
			ajaxObject.params = params;
		}
		return $http(ajaxObject);
	};

	this.postData = function(URL,jsonData) {
		if(URL&&moduleCode){
			if(URL.indexOf("?")!=-1){
				if(URL.indexOf("?")==URL.length-1){
					URL += "moduleCode="+moduleCode;
				}else{
					URL += "&moduleCode="+moduleCode;
				}
			}else{
				URL += "?moduleCode="+moduleCode;
			}
		}
		var ajaxObject = {
				method : "post",
				url : URL,
				headers : {
					'Content-Type' : 'application/json;charset=UTF-8'
				}
			};
		if(jsonData){
			ajaxObject.data = jsonData;
		}
		return $http(ajaxObject);
	};
	
	this.postFile = function(URL,formData) {
		return $http({
			method : "post",
			url : URL,
			headers : {
				'Content-Type' : undefined
			},
			data : formData,
		});
	};
});

var rootPath = "/NewsCMS";
var ajaxRoot = rootPath + "/secured/";
var forwardRoot = rootPath + "/";

function alertResponseMsg(response){
	if(response&&response.data){
		if(response.data.msg){
			systemAlert(response.data.msg);
		}
	}
};

function onError(response) {
	if(response){
		if(response.data){			
			if(!response.data.success){
				if(response.data.msg){
					systemAlert(response.data.msg);
				}else{
					systemAlert("后台操作失败！请放心，失败的操作不会影响数据的完整性！");				
				}
			}else{
				if(response.data.msg){
					systemAlert(response.data.msg);
				}
			}
		}else{
			systemAlert("后台操作失败！请放心，失败的操作不会影响数据的完整性！");
		}
	}else{
		systemAlert("发送请求失败！请检查访问路径或参数！");	
	}
};

function redirectTo(url) {
    window.location.href = url;
};