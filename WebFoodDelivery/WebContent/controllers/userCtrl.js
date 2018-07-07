myModule.controller('userCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService','appService', function ($rootScope, $scope, $timeout, $window, dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in user ctrl');
   
    $scope.status = "";
    $scope.refreshUsers = function(){
	    dataService.getAll('users', 'getUsers', null, function(res){
	    	console.log(res);
	    	if(res.status == 200){
	    		$scope.users = res.data;
		    	for (var i = 0; i < $scope.users.length; i++) {
		    		$scope.users[i].Editable = false;
		    		if($scope.users[i].role =="DELIVERER"){ 
		    			$scope.users[i].oldVehicleRegister = angular.copy($scope.users[i].register);
		    		}
		        }		    	    
		    	 dataService.getAll('vehicles', 'getVehicles', null, function(res){
		    	    	console.log(res);
		    	    	if(res.status == 200){
		    	    		$scope.vehicles = res.data;
		    	    		
		    	    		$scope.vehicles = angular.copy(appService.lodashFilterBy($scope.vehicles, 'visibility', true));
		    	    		
		    	    		console.log($scope.vehicles);
		    	    		
		    	    	}else{
		    	    		console.log("something went wrong");
		    	    	}
		    	    });
		    	
		    	
		    	
	    	}else{
	    		console.log("something went wrong");
	    	}
	    	

	    });
    };
    
    $scope.refreshUsers();

    $scope.editUser = function (id) {

        for (var i = 0; i < $scope.users.length; i++) {
            if ($scope.users[i].id == id) {
            	$scope.users[i].Editable = true;
                $scope.selectedUser = angular.copy($scope.users[i]);
            } else {
            	$scope.users[i].Editable = false;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    $scope.exitEditUser = function () {

        for (var i = 0; i < $scope.users.length; i++) {
        	$scope.users[i].Editable = false;        	
        }
        
        $scope.selectedUser = {};

       
    }

    //-----------------------------------------------------------------------------------------------------------
   
    $scope.saveUser = function (user) {
    	
    	
 
//    	delete myObject.regex;
//    	// or,
//    	delete myObject['regex'];
//    	// or,
//    	var prop = "regex";
//    	delete myObject[prop];
//    	var myObject = {
//    		    "ircEvent": "PRIVMSG",
//    		    "method": "newURI",
//    		    "regex": "^http://.*"
//    		};
//    		delete myObject.regex;
    	
    	if(user.role == "CUSTOMER" || user.role == "ADMIN"){
    		if(user.register != null){
    			
    			var vehicle = angular.copy(appService.lodashFindBy($scope.vehicles, 'register', user.register));

    			vehicle.inUse = false;

    			dataService.update('vehicles', 'updateVehicle', vehicle, function(res){
        	    	if(res.status == 200){
        	    		console.log('vehicle updated');
        	    	}else{
        	    		console.log(res);
        	    	}

        	    });
    			
    			user.register = null;
    		}    		
    	}else{//if user is DELIVERER
    		if(user.register != null && user.register != user.oldVehicleRegister){
    			
    			if(user.oldVehicleRegister != null ){//just in case to avoid errors. Checking secret vehicle value on user
    				var vehicle = angular.copy(appService.lodashFindBy($scope.vehicles, 'register', user.oldVehicleRegister));
    				
    				vehicle.inUse = false;
    				//We are setting inUse to false for vehicle that DRIVER wont be using anymore, because he switched to another vehicle
        			dataService.update('vehicles', 'updateVehicle', vehicle, function(res){
            	    	if(res.status == 200){
            	    		console.log('vehicle updated');
            	    	}else{
            	    		console.log(res);
            	    	}

            	    });
    			}

    			var vehicle = angular.copy(appService.lodashFindBy($scope.vehicles, 'register', user.register));
    
    			vehicle.inUse = true;
    		    //We are setting inUse to true for this vehicle, because DRIVER is using this vehicle
    			dataService.update('vehicles', 'updateVehicle', vehicle, function(res){
        	    	if(res.status == 200){
        	    		console.log('vehicle updated');
        	    	}else{
        	    		console.log(res);
        	    	}

        	    });

    		} 
    		
    	}
    	
    	//We are deleting properties that are only needed in frontend. We must delete those properties, for model to be mapped right in backend
    	 delete user.oldVehicleRegister;
		 delete user.Editable;

		 //Finnaly we update user here
	       dataService.update('users', 'updateUser', user, function(res){
		    
		    	if(res.status == 200){    		
		    		$scope.users = [];
		    		$scope.vehicles = [];
		    		$scope.refreshUsers();
		    	}else{
		    		console.log("something went wrong");
		    	}

		    });
    }
    //-----------------------------------------------------------------------------------------------------------

    
}]);

