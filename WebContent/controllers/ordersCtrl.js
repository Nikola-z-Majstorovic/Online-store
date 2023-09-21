myModule.controller('ordersCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'dataService', 'appService', function ($rootScope, $scope, $timeout, $window, dataService, appService) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in orders Ctrl');

    $scope.freeOrders=[];
    
    $scope.refreshOrders =function () {
        dataService.getAll('orders', 'getOrders', null, function(res){
        	console.log(res);
        	if(res.status == 200){
        		$scope.orders = res.data;
        		console.log($scope.orders);
        		
        	}else{
        		console.log(res);
        	}
        }); 
    }
    
    $scope.refreshOrders();
    
    
    $scope.assingOrderToMe = function (orderID) {
    	
    	console.log(orderID);
        var order = angular.copy(appService.lodashFindBy($scope.orders, 'id', orderID));
        console.log(order);

        order.idD = $rootScope.loginuser.id;

        if (order.status == 'Ordered') {
            order.status = 'Delivery_in_progress';
        }
        
        
        dataService.update('orders', 'updateOrder', order, function(res){	  
	    	if(res.status == 200){    		 
	    		$scope.refreshOrders();   	       	       
	    	}else{
	    		console.log(res);
	    	}
	    });
        
        
    }

    $scope.completeOrder = function (orderID) {

        var order = angular.copy(appService.lodashFindBy($scope.orders, 'id', orderID));
        if (order != null) {
            if (order.status == 'Delivery_in_progress') {
                order.status = 'Delivered';
                
                dataService.getAll('articles','getArticlesFromRestaurant', order.idR,function(res) {
        	    	if(res.status==200){   	
        	    		$scope.allArticlesFromOrderRestaurant = res.data;	        	    		
        	    		for (var i = 0; i < order.articleOrders.length; i++) {
                        	
                        	var existingArticle = angular.copy(appService.lodashFindBy($scope.allArticlesFromOrderRestaurant, 'id', order.articleOrders[i].idA));
                        	                        	
                        	if(existingArticle != null){
                        		existingArticle.articleRanking = angular.copy(existingArticle.articleRanking + order.articleOrders[i].amount);
                        		
                        		console.log(existingArticle.articleRanking);
                        		dataService.update('articles','updateArticlesFromRestaurant', existingArticle, function(res) {
                    		    	if(res.status==200){   	                    		    	    
                    		    	}else {
                    		    		console.log(res);    		
                    		    	}
                    		    });
                        	}
                        }
        	    		
        	    	}else {
        	    		console.log(res);    		
        	    	}
        	    });
                
                
                
                
            }
        } else {
            alert("Order doesnt exist enymore!");
        }
        
        dataService.update('orders', 'updateOrder', order, function(res){	  
	    	if(res.status == 200){    		 
	    		$scope.refreshOrders();   	       	       
	    	}else{
	    		console.log(res);
	    	}
	    });
    }

    //ADMIN PART
    
    $scope.getArticlesForSelectedOrder = function(restaurantID){
    	$scope.ArticlesFromOrderedRestaurant = [];
	    dataService.getAll('articles','getArticlesFromRestaurant', restaurantID,function(res) {
	    	if(res.status==200){   	
	    		$scope.ArticlesFromOrderedRestaurant = res.data;	    	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
    };
    
    $scope.deleteSelectedOrder = function(orderID){
    	dataService.delete('orders','deleteOrder', orderID,function(res) {
	    	if(res.status==200){   	
	    		$scope.refreshOrders();	    	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
    }
    
    $scope.cancelSelectedOrder = function(order){
    	order.status = "Canceled";
    	order.idD = null;
    	
    	dataService.update('orders','updateOrder', order,function(res) {
	    	if(res.status==200){   	
	    		$scope.refreshOrders();	    	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
    }
    
    $scope.editOrder = function(order){
    	$scope.selectedOrder = angular.copy(order);
    	$scope.selectedOrder.Editable = true;
    	
    	$scope.getArticlesForSelectedOrder(order.idR);
    	
    	
    	$scope.getDeliverers();
    	
    }
    
    $scope.removeSelectedArticle = function(article){
    	  var articleToRemove = appService.lodashFindBy($scope.selectedOrder.articleOrders, 'idA', article.idA);

          
	        if (articleToRemove.amount > 1) {
	            articleToRemove.amount = articleToRemove.amount - 1;
	            $scope.selectedOrder.price = angular.copy($scope.selectedOrder.price) - articleToRemove.priceByUnit;
	        } else {
	            if ($scope.selectedOrder.articleOrders.length >= 1) {
	            	
	            	$scope.selectedOrder.articleOrders = _.without($scope.selectedOrder.articleOrders, article);
	            	$scope.selectedOrder.price = angular.copy($scope.selectedOrder.price) - article.priceByUnit;
	            }
	        }

    }
    
    $scope.exitEditOrder = function(order){
    	$scope.selectedOrder = {};    	
    }   
    
    $scope.orderArticle = function(article){
    	console.log(article);
    var articleExists = appService.lodashFindBy($scope.selectedOrder.articleOrders, 'idA', article.id);
        
        console.log(articleExists);
        if (articleExists != null) {
           articleExists.amount = articleExists.amount + 1; 
           
           $scope.selectedOrder.price = angular.copy($scope.selectedOrder.price) + articleExists.priceByUnit;
        } else {
            var articleToAdd = {
        		idA: article.id,
        		name: article.name,
        		amount: 1,
                priceByUnit: article.price
            }

            $scope.selectedOrder.articleOrders.push(articleToAdd);
            
            $scope.selectedOrder.price = angular.copy($scope.selectedOrder.price) + articleToAdd.priceByUnit;
        }        
    }
    
    $scope.saveOrder = function(){
    	if($scope.selectedOrder.articleOrders.length > 0){
    		 delete $scope.selectedOrder.Editable;
    		 
    		 if($scope.selectedOrder.idD != '' && $scope.selectedOrder.idD != null){
    			 $scope.selectedOrder.status = 'Delivery_in_progress';
    		 }
    		 
    		 dataService.update('orders', 'updateOrder', $scope.selectedOrder, function(res){	  
    		    	if(res.status == 200){    		 
    		    		$scope.selectedOrder = {}; 
    		    		$scope.refreshOrders();
    		    	}else{
    		    		console.log(res);
    		    	}
    		    });    		    	
    	}else{
    		alert("To save order You must have some articles added in order!");
    	}
    }
    
    
    
    $scope.getDeliverers = function(){
    	  dataService.getAll('users', 'getUsers', null, function(res){

  	    	if(res.status == 200){
  	    		$scope.allUsers = res.data;
  	    		$scope.delivererUsersArray = appService.lodashFilterBy(res.data, 'role', 'DELIVERER');  		    	
  	    	}else{
  	    		console.log(res);
  	    		$scope.delivererUsersArray = [];
  	    	}  	    	
  	    });
    }
    
    
    $scope.getUserInfo = function(userID, propertyName){
    	var user = appService.lodashFindBy($scope.allUsers, 'id', userID);  		 
    
    	if(user != null){
    		if(propertyName == "phone"){
    			return user.phone;
    		}else if(propertyName == "name"){
    			return user.name;
    		}else if(propertyName == "address"){
    			return user.adress;
    		}
    	}
    }
}]);

