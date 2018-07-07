myModule.controller('loginCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService','$http', function ($rootScope, $scope, $timeout, $window, dataService,$http) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in login ctrl');
    $scope.errorLogin=false;

    $scope.login = function () {
        console.log($rootScope.loginuser);

           dataService.login($rootScope.loginuser,function(res) {
            	if(res.status==200){
            		$rootScope.loginuser = res.data;
            		
            		if($rootScope.loginuser.myFavoriteRestaurants == null){
            			$rootScope.loginuser.myFavoriteRestaurants = [];
            		}else{
            			for (var i = 0; i < $rootScope.loginuser.myFavoriteRestaurants.length; i++) {
            				$rootScope.loginuser.myFavoriteRestaurants[i] = Number($rootScope.loginuser.myFavoriteRestaurants[i]);
            	        }
            		}
            	    $rootScope.changeView('/home');
            	}else {
            		console.log("greska");
            		$scope.errorLogin=true;
            	}
            });
            
    }
}]);

