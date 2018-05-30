app.factory('signUpFactory', function($http) {
	console.log("usaoufabriku")
	var factory = {};
	factory.getCustomers = function() {
		return $http.get('/WebfoodDelivery/rest/database/test');
	};

	factory.addCustomer = function() {
		return $http.post('/WebfoodDelivery/rest/database/test');
	};
	return factory;
	
});