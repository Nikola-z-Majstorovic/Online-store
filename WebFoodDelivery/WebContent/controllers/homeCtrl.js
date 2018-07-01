myModule.controller('homeCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService', function ($rootScope, $scope, $timeout, $window,dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in home ctrl');
    $scope.restaurants=[];
    $scope.refreshRestaurants = function(){
    	console.log("usao u refersh");
    dataService.getAll('restaurants','getRestaurants',null,function(res) {
    	if(res.status==200){
    		$scope.restaurants= res.data;
    	  	for (var i = 0; i < $scope.restaurants.length; i++) {
	    		$scope.restaurants[i].Editable = false;
            }
    	}else {
    		console.log("greska");    		
    	}
    	$scope.backupRestaurants = angular.copy($scope.restaurants);
    });
    };
    
    $scope.refreshRestaurants();
    
    $scope.addRestaurant = function () {
    	$scope.status = "create";
        var newRestaurant = {
        	id:  $scope.restaurants.length+1,
            name: null,
            adress: null,
            category: null,
            visibility: true,
            Editable: true
        }

        $scope.selectedRestaurant = newRestaurant;

        $scope.restaurants.push(newRestaurant);

    }
    
    $scope.saveRestaurant = function (restaurant) {

       var restaurantToAdd = {
    		   id : restaurant.id,
    		   name: restaurant.name,
    		   adress: restaurant.adress,
    		   category: restaurant.category,
               visibility: restaurant.visibility,
       }

                	if($scope.status == "update"){
                		console.log('bingo update');

	                	dataService.update('restaurants', 'updateRestaurant', restaurantToAdd, function(res){
	            	    	console.log(res);
	            	    	if(res.status == 200){
	            	    		$scope.restaurants = [];
	            	    		$scope.refreshRestaurants();
	            	    	}else{
	            	    		console.log("something went wrong");
	            	    	}
	
	            	    });
	                	
	                		                	                              	
                	}

        	
        	if($scope.status == "create"){
        		console.log('bingo create');
        		console.log(restaurantToAdd);
	            dataService.create('restaurants', 'addRestaurant', restaurantToAdd, function(res){
	    	    	console.log(res);
	    	    	if(res.status == 200){
	    	    		$scope.restaurants = [];
	    	    		$scope.refreshRestaurants();
	    	    	}else{
	    	    		console.log("something went wrong");
	    	    	}
	
	    	    });
        	}
       
        
        	$scope.status = "";
        

    }
    $scope.exitEditRestaurant=function() {
    	$scope.status = "";
    	for(var i=0;i<$scope.restaurants.length;i++) {
    		$scope.restaurants[i].Editable=false;
    		selectedRestaurant={};
    	}
    	$scope.restaurants= angular.copy($scope.backupRestaurants);
    }
    $scope.editRestaurant = function (id) {
    	$scope.status = "update";
        for (var i = 0; i < $scope.restaurants.length; i++) {
            if ($scope.restaurants[i].id == id) {
            	$scope.restaurants[i].Editable = true;
                $scope.selectedRestaurant = angular.copy($scope.restaurants[i]);
            } else {
            	$scope.restaurants[i].Editable = false;
            }
        }
    }

    
    $scope.deleteRestaurant = function (restaurant) {
        //first remove vehicle from users use
    	//NAPOMENA
//        var user = appService.lodashFindBy($rootScope.users, 'register', vehicle.register);
//        if (user != null) {
//            user.vehicleId = null;
//        }

    	dataService.delete('restaurants', 'deleteRestaurant', restaurant.id, function(res){
	    	console.log(res);
	    	if(res.status == 200){
	    		console.log(res.data)
	    		console.log($scope.restaurants );
	    		$scope.restaurants = [];
	    	    $scope.refreshRestaurants();
	    	}else{
	    		console.log("something went wrong");
	    	}

	    });

    }
    $scope.showMenu = function (restaurantId) {
    	$rootScope.changeView("/restaurant/"+restaurantId);
    }
    $scope.getRestaurantCathegoryName = function (restaurantCathegoryValue) {
        return appService.lodashFindBy($rootScope.enumRestaurantCathegories, 'value', restaurantCathegoryValue);
    };
    
}]);

