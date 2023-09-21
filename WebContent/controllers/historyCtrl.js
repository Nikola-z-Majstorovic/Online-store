myModule.controller('historyCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService','appService', function ($rootScope, $scope, $timeout, $window, dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in history ctrl');
    dataService.getAll('orders', 'getOrders', null, function(res){

    	if(res.status == 200){
    		var orders = res.data;
	  		$scope.myOrders = appService.lodashFilterBy(orders, 'idC', $rootScope.loginuser.id);
	  		console.log($scope.myOrders)
    	}else{
    		console.log(res);
    	}
    }); 

    
}]);

