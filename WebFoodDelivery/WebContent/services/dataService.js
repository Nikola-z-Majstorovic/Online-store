myModule.factory('dataService', ['$http','$resource', '$window', '$routeParams', '$location', '$route', '$rootScope', 'appService', function ($http,$resource, $window, $routeParams, $location, $route, $rootScope, appService) {

    return {
        get: function (id) {
            
        },
        getAll: function (method,param,cb) {
        	if(param==null) {
        	var error;
        	var result =$http.get('/WebFoodDelivery/rest/database/'+ method)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        	}else {
               	var error;
            	var result =$http.get('/WebFoodDelivery/rest/database/'+ method+ "/" + param)
                .then(function (response) {
                	cb(response);
                }, function (reason) {
                	cb(reason);
                });
        	}
        },
        create:function(objectToCreate){

        },
        update:function(objectToUpdate){

        },
        login: function (user,cb) {
//        	return $http.get('/WebFoodDelivery/rest/database/getUsers').then(console.log('bingo'), console.log('fail'));
        	var error;
        	var result =$http.post('/WebFoodDelivery/rest/database/signIn', user)
            .then(function (response) {
            	cb(response);
//                result = response.data;
//                console.log(response);
//                console.log(result);
            }, function (reason) {
            	cb(reason);
            });
        	
        	
        },
        reg : function(user,cb) {
        	var error;
        	var result =$http.post('/WebFoodDelivery/rest/database/signUp', user)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        }
    }
}]);