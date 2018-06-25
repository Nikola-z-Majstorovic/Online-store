myModule.controller('loginCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService', function ($rootScope, $scope, $timeout, $window, dataService) {
    //-----------------------------------------------------------------------------------------------------------
//    console.log('we are in login ctrl');
    $scope.errorLogin=false;
    console.log($rootScope.loginuser);
    $rootScope.loginuser = {
        username: "",
        password: ""
    };
    console.log($rootScope.loginuser);
    $scope.login = function () {
                 
           dataService.login($rootScope.loginuser,function(res) {
//            	console.log(res);
            	if(res.status==200){
            		$rootScope.loginuser = res.data;
//            		console.log($rootScope.loginuser);
            	    $rootScope.changeView('/home');
            	}else {
            		console.log("greska");
            		$scope.errorLogin=true;
            	}
            });
            
    }
}]);

