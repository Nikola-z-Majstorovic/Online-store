app.controller("signInController", function($scope,signUpFactory) {
	$scope.submit = function() {

		$scope.user.username;
		$scope.user.password;
			getCustomer();
	};
	function getCustomer() {
			signUpFactory.getCustomers().success(function(data) {
				$scope.test=data;
		});
	}
});
app.controller("signUpController", function($scope, signUpFactory ) {
		$scope.submit = function() {
			$scope.customer.username;
			$scope.customer.password;
			$scope.customer.name;
			$scope.customer.surname;
			$scope.customer.email;
			$scope.customer.phone;
			addCustomer($scope.customer);
		};
		
		function addCustomer(customer) {
			signUpFactory.addCustomer(customer).then(function(data) {
				toast('Customer ' + volonter.korisnickoIme + " registrated.");
			}).catch(function (response) {
				//$notify.error(response.msg);
				toast("Customer name and/or email are alredy busy.");
			});	
		}
	
});