app.controller('signInController', function($scope) {


});
app.controller('signUpController', function($scope,signUpFactory) {

    function init() {
    	console.log('signUpController.Init');
    	signUpFactory.getCustomers.success(function (data) {
        	$scope.customers = data;
		});
    }
	init();
	
	
	$scope.addCustomer = function(customer) {
		signUpFactory.addCustomer(customer).then(function(data) {
			toast('Customer ' + customer.username + " registrated.");
		}).catch(function (response) {
			//$notify.error(response.msg);
			toast("Username and/or email are alredy busy.");
		});	
	};
	
});