app.factory('userFactory', function($http) {

	var factory = {};
	factory.getUser = function(user) {
		return $http.post('/WebFoodDelivery/rest/database/signIn',user);
	};

	factory.addUser = function(user) {
		return $http.post('/WebFoodDelivery/rest/database/addUser', user);
	};
	return factory;
	
});