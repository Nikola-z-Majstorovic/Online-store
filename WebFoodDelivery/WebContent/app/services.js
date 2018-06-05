app.factory('customerFactory', function($http) {

	var factory = {};
	factory.getCustomers = function() {
		return $http.get('/WebFoodDelivery/rest/database/signIn');
	};

	factory.addCustomer = function(customer) {
		return $http.post('/WebFoodDelivery/rest/database/addCustomer', customer);
	};
	return factory;
	
});