myModule.controller('regCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService', function ($rootScope, $scope, $timeout, $window, dataService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in reg ctrl');
    $scope.regUser = {
        username: "",
        password: "",
        name: "",
        surname: "",
        phone: "",
        email: ""
    };
    $scope.submit = function () {
                 
           dataService.reg($scope.regUser,function(res) {
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
