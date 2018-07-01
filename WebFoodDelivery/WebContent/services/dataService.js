myModule.factory('dataService', ['$http','$resource', '$window', '$routeParams', '$location', '$route', '$rootScope', 'appService', function ($http,$resource, $window, $routeParams, $location, $route, $rootScope, appService) {

    return {
        get: function (id) {
            
        },
        getAll: function (ctrl,method,param,cb) {
        	if(param==null) {
        	$http.get('/WebFoodDelivery/rest/' +ctrl+ '/'+ method)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        	}else {
            	$http.get('/WebFoodDelivery/rest/' +ctrl+ '/'+ method+ "/" + param)
                .then(function (response) {
                	cb(response);
                }, function (reason) {
                	cb(reason);
                });
        	}
        },
        delete:function(ctrl,method,param,cb){
        	$http.delete('/WebFoodDelivery/rest/' + ctrl + '/' + method+ "/" + param)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        },
        create:function(ctrl,method,param,cb){
        	$http.post('/WebFoodDelivery/rest/' + ctrl + '/' + method, param)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        },
        update:function(ctrl,method,param,cb){

        	$http.put('/WebFoodDelivery/rest/' +ctrl+ '/'+ method, param)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        },
        login: function (user,cb) {
        	$http.post('/WebFoodDelivery/rest/users/signIn', user)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        	
        	
        },
        reg : function(user,cb) {
        	$http.post('/WebFoodDelivery/rest/users/signUp', user)
            .then(function (response) {
            	cb(response);
            }, function (reason) {
            	cb(reason);
            });
        }
    }
}]);