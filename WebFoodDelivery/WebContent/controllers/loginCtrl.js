myModule.controller('loginCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService','$http', function ($rootScope, $scope, $timeout, $window, dataService,$http) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in login ctrl');
    $scope.errorLogin=false;

 

    $scope.login = function () {
        console.log($rootScope.loginuser);

           dataService.login($rootScope.loginuser,function(res) {
            	console.log(res);
            	if(res.status==200){
            		$rootScope.loginuser = res.data;
            	    $rootScope.changeView('/home');
            	}else {
            		console.log("greska");
            		$scope.errorLogin=true;
            	}
            });
            
    }
}]);

