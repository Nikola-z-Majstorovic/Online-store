webShop.factory('signUpFactory', function($http) {

	var factory = {};
	factory.getCustomers = function() {
		return $http.get('/WebfoodDelivery/rest/database/getCustomers');
	};

	factory.addCustomer = function(customer) {
		return $http.post('/WebfoodDelivery/rest/database/addCustomer', customer);
	};
	return factory;
	
});