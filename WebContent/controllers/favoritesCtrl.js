myModule.controller('favoritesCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService', function ($rootScope, $scope, $timeout, $window,dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in favorite ctrl');

    
  	$scope.restaurants=[];
    dataService.getAll('restaurants','getRestaurants',null,function(res) {
    	if(res.status==200){
    		$scope.restaurants= res.data;
//    		myFavoriteRestaurants=lodashFilterBy($scope.restaurants,'idR',loginuser.)
            
    	}else {
    		console.log("greska");    		
    	}
    });
    
    
    $scope.filterMyFavorites = function (item) { 
    	if(_.includes($rootScope.loginuser.myFavoriteRestaurants, item.id)){
    		return item;
    	}
    };
}]);

