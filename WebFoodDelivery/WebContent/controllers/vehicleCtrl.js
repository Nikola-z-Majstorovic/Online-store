myModule.controller('vehicleCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService','appService', '$http', function ($rootScope, $scope, $timeout, $window, dataService,appService,$http) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in vehicle ctrl');
   
    $scope.status = "";
    $scope.refreshCars = function(){
	    dataService.getAll('vehicles', 'getVehicles', null, function(res){
	    	console.log(res);
	    	if(res.status == 200){
	    		$scope.vehicles = res.data;
		    	for (var i = 0; i < $scope.vehicles.length; i++) {
		    		$scope.vehicles[i].Editable = false;
		        }
	    	}else{
	    		console.log("something went wrong");
	    	}
	    	
	    	$scope.backupVehicles = angular.copy($scope.vehicles);

	    });
    };
    
    $scope.refreshCars();

    $scope.editVehicle = function (register) {
    	$scope.status = "update";
        for (var i = 0; i < $scope.vehicles.length; i++) {
            if ($scope.vehicles[i].register == register) {
            	$scope.vehicles[i].Editable = true;
                $scope.selectedVehicle = angular.copy($scope.vehicles[i]);
            } else {
            	$scope.vehicles[i].Editable = false;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    $scope.exitEditVehicle = function () {
    	$scope.status = "";
  
        for (var i = 0; i < $scope.vehicles.length; i++) {
        	$scope.vehicles[i].Editable = false;
            //$scope.selectedVehicle = $rootScope.vehicles[i];
            $scope.selectedVehicle = {};
        }
        

    	$scope.vehicles = angular.copy($scope.backupVehicles);
       
    }
    //-----------------------------------------------------------------------------------------------------------    
    $scope.addVehicle = function () {
    	$scope.status = "create";
        var newVehicle = {
            brand: null,
            model: null,
            type: null,
            inUse: false,
            register: null,
            visibility: true,
            note: null,
            Editable: true
        }

        $scope.selectedVehicle = newVehicle;

        $scope.vehicles.push(newVehicle);

    }
    //-----------------------------------------------------------------------------------------------------------
   
    $scope.saveVehicle = function (vehicle) {

       var vehicleToAdd = {
    		   brand: vehicle.brand,
               type: vehicle.type,
               model: vehicle.model,
               inUse: vehicle.inUse,
               register: vehicle.register,
               visibility: vehicle.visibility,
               note: vehicle.note
       }

                	if($scope.status == "update"){
                		console.log('bingo update');

	                	dataService.update('vehicles', 'updateVehicle', vehicleToAdd, function(res){
	            	    	console.log(res);
	            	    	if(res.status == 200){
	            	    		$scope.vehicles = [];
	            	    		$scope.refreshCars();
	            	    	}else{
	            	    		console.log("something went wrong");
	            	    	}
	
	            	    });
	                	
	                		                	                              	
                	}

        	
        	if($scope.status == "create"){
        		console.log('bingo create');
	            dataService.create('vehicles', 'addVehicle', vehicleToAdd, function(res){
	    	    	console.log(res);
	    	    	if(res.status == 200){
	    	    		$scope.vehicles = [];
	    	    		$scope.refreshCars();
	    	    	}else{
	    	    		console.log("something went wrong");
	    	    	}
	
	    	    });
        	}
       
        
        	$scope.status = "";
        

    }
    //-----------------------------------------------------------------------------------------------------------
    $scope.deleteVehicle = function (vehicle) {
        //first remove vehicle from users use
    	//NAPOMENA
//        var user = appService.lodashFindBy($rootScope.users, 'register', vehicle.register);
//        if (user != null) {
//            user.vehicleId = null;
//        }
    	if(vehicle.inUse==false) {
    	dataService.delete('vehicles', 'deleteVehicle', vehicle.register, function(res){
	    	console.log(res);
	    	if(res.status == 200){
	    		$scope.vehicles = [];
	    		$scope.refreshCars();
	    	}else{
	    		console.log("something went wrong");
	    	}

	    });
    	} else {
    		alert("Cannot delete.Vehicle is in use.")
    	}
    }

    
}]);

