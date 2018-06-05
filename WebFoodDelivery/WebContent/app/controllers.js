app.controller('signInController', function($scope,customerFactory) {
	 function init() {
		 customerFactory.getCustomers().then(function (data) {
			 $scope.test=data;
		 });
		
	    }
		init();
		
		
});