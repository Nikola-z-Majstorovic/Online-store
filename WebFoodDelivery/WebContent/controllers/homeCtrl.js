myModule.controller('homeCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService', function ($rootScope, $scope, $timeout, $window,dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in home ctrl');
    $rootScope.restaurants=[];
    dataService.getAll('getRestaurants',null,function(res) {
//    	console.log(res);
    	if(res.status==200){
    		$rootScope.restaurants= res.data;
    		console.log($rootScope.restaurants);
    	}else {
    		console.log("greska");    		
    	}
    });
    $scope.navigateToRestaurant = function (restaurantId) {
    	$rootScope.changeView("/restaurant/"+restaurantId);
    }

}]);

