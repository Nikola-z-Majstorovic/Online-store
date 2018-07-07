myModule.controller('homeCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService', function ($rootScope, $scope, $timeout, $window,dataService,appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in home ctrl');
    console.log($rootScope.loginuser);
    $scope.restaurants=[];
    $scope.refreshRestaurants = function(){
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
    dataService.getAll('articles','getArticles',null,function(res) {
    	if(res.status==200){
    		$scope.rankedArticles= res.data;
    	

    	}else {
    		console.log("greska");    		
    	}

    });
    };
    
    $scope.filterRanked = function (item) { 
    	if(item.articleRanking > 0){
    		return item;
    	}
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
	                	dataService.update('restaurants', 'updateRestaurant', restaurantToAdd, function(res){	            	    	
	            	    	if(res.status == 200){
	            	    		$scope.restaurants = [];
	            	    		$scope.refreshRestaurants();
	            	    	}else{
	            	    		console.log("something went wrong");
	            	    	}
	
	            	    });
	                	
	                		                	                              	
                	}

        	
        	if($scope.status == "create"){
	            dataService.create('restaurants', 'addRestaurant', restaurantToAdd, function(res){	    	    	
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

    	dataService.delete('restaurants', 'deleteRestaurant', restaurant.id, function(res){	    	
	    	if(res.status == 200){
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

    
    $scope.isChecked = function (restaurantID){ 
    	
    	var userFavoriteRestaurants = angular.copy($rootScope.loginuser.myFavoriteRestaurants);

    	if(_.includes(userFavoriteRestaurants, restaurantID)){
    		return true;
    	}else{
    		return false;
    	}
    }
    $scope.likeUnlikeRestaurant = function (restaurantID){

    	var userFavoriteRestaurants =  angular.copy($rootScope.loginuser.myFavoriteRestaurants);
    	if(_.includes(userFavoriteRestaurants, restaurantID)){
    		$rootScope.loginuser.myFavoriteRestaurants = angular.copy(_.without($rootScope.loginuser.myFavoriteRestaurants, restaurantID));
    	}else{
    		$rootScope.loginuser.myFavoriteRestaurants.push(restaurantID);
    	}    	
    	dataService.update('users', 'updateUser', $rootScope.loginuser, function(res){		  
	    	if(res.status == 200){    		
	    		
	    	}else{
	    		console.log("something went wrong");
	    	}

	    });
    }
    
    $scope.getArticleTypeName = function (articleTypeValue) {
        return appService.lodashFindBy($rootScope.enumArticleType, 'value', articleTypeValue);
    };;
    
    
}]);

