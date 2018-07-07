myModule.controller('restaurantCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService','$routeParams', function ($rootScope, $scope, $timeout, $window,dataService,appService,$routeParams) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in restaurant ctrl');
    
    $scope.selectedRestaurantId = Number($routeParams.restaurantId);
    
    $scope.refreshArticles = function(){
    	$scope.Articles = [];
    	$scope.selectedArticle = {};
	    dataService.getAll('articles','getArticlesFromRestaurant', $scope.selectedRestaurantId,function(res) {
	    	if(res.status==200){   	
	    		$scope.Articles = res.data;	    	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
	    dataService.getAll('articles','getArticles', null,function(res) {
	    	if(res.status==200){   	
	    		$scope.allArticles = res.data;	    	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
    };
    
    $scope.refreshArticles();
///////////////////////////////////////////////////////////////////////////////////////// START ADMIN
    $scope.editArticle = function(articleID){
        for (var i = 0; i < $scope.Articles.length; i++) {
            if ($scope.Articles[i].id == articleID) {
                $scope.selectedArticle = angular.copy($scope.Articles[i]);
                $scope.selectedArticle.Editable = true;
            } 
        }      
    };
    
    $scope.exitEditArticle = function (article) {

        $scope.selectedArticle = {};
    };
    
    $scope.deleteArticle = function(articleID){
           
    	dataService.delete('articles','deleteArticlesFromRestaurant', articleID, function(res) {
	    	if(res.status==200){   	
	    	//Load fresh articles data after delete
	       	 $scope.refreshArticles();
	
	    	}else {
	    		console.log("greska");    		
	    	}
	    });
	
    };
    
    $scope.addArticle = function () {

        var newArticle = {
        	id: null,
        	name: null,
        	price: null,
        	description: null,
        	artImgPath: null,
        	quantity: null,
            food: null,
            idR: $scope.selectedRestaurantId,
            visibility: true,
            Editable: true            
        }

        $scope.selectedArticle = newArticle;
    };
    
    $scope.saveArticle = function () {
    	
    	delete $scope.selectedArticle.Editable;

    	if($scope.selectedArticle.id != null){
	    	dataService.update('articles','updateArticlesFromRestaurant', $scope.selectedArticle, function(res) {
		    	if(res.status==200){   	
		    		$scope.refreshArticles();
		    	}else {
		    		console.log(res);    		
		    	}
		    });
    	}else{    		
    		console.log($scope.Articles.length);
    		$scope.selectedArticle.id = $scope.allArticles.length + 1;
    		
    		
    		console.log($scope.selectedArticle);
    		dataService.create('articles','addArticleToRestaurant', $scope.selectedArticle, function(res) {
		    	if(res.status==200){   	
		    		$scope.refreshArticles();

		    	}else {
		    		console.log(res);    		
		    	}
		    });
    		
    	}
      
    };
/////////////////////////////////////////////////////////////////////////////////////// END ADMIN   
    
////////////////////////////////////////////////////////////////////////////////////// START USER    
    $scope.orderList = [];
    $scope.totalPrice=0;
   
    $scope.refreshOrders =function () {
    dataService.getAll('orders', 'getOrders', null, function(res){
    	console.log(res);
    	if(res.status == 200){
    		var orders = res.data;
    		$scope.allOrders = orders;
    	}else{
    		console.log(res);
    	}
    }); 
    }
    
      $scope.refreshOrders();
    
      $scope.orderArticle = function (article) {
        var articleExists = appService.lodashFindBy($scope.orderList, 'idA', article.id);
        
        console.log(articleExists);
        if (articleExists != null) {

           articleExists.amount = articleExists.amount + 1;
           articleExists.price = articleExists.amount * articleExists.priceByUnit;

            
        } else {
            var article = {
        		idA: article.id,
        		name: article.name,
        		amount: 1,
                price: article.price,
                priceByUnit: article.price
            }

            $scope.orderList.push(article);
        }

        var sumPriceTotal = 0;
        for (var i = 0; i < $scope.orderList.length; i++) {
            sumPriceTotal = sumPriceTotal + $scope.orderList[i].price;            
        }
        $scope.totalPrice = sumPriceTotal;

    };
    
    $scope.removeOrderFromCart = function (article) {
    	
    	console.log(article);
    	console.log($scope.orderList);
        var articleToRemove = appService.lodashFindBy($scope.orderList, 'idA', article.idA);

        
	        if (articleToRemove.amount > 1) {
	            articleToRemove.amount = articleToRemove.amount - 1;
	            articleToRemove.price = articleToRemove.price - articleToRemove.priceByUnit;
	        } else {
	            if ($scope.orderList.length >= 1) {
	            	$scope.orderList = _.without($scope.orderList, article);
	            }
	        }

        var sumPriceTotal = 0;
        for (var i = 0; i < $scope.orderList.length; i++) {
            sumPriceTotal = sumPriceTotal + $scope.orderList[i].price;
        }
        $scope.totalPrice = sumPriceTotal;
    }
    
    
    $scope.onChangeAttachUser = function (user){ 
    	$scope.attachOrderToUser = user;
    }
    $scope.onChangeAttachDeliverer = function (user){ 
    	$scope.attachOrderToDeliverer = user;    	
    }
    $scope.makeOrder = function () {
    	
    	var allowSave = true;
    	
    	if($rootScope.loginuser.role == "ADMIN"){
    		if($scope.attachOrderToUser == null){
    			allowSave = false;
    		}    		
    	}
    	console.log($scope.attachOrderToUser);
    	console.log($scope.attachOrderToDeliverer);
    	console.log(allowSave);
   
    	if(allowSave){
    		
    	for (var i = 0; i < $scope.orderList.length; i++) {
            delete $scope.orderList[i].price
        }
    	
    	var attachOrderToUser = $rootScope.loginuser.id;
    	var orderStatus = 'Ordered';
    	var delivererIDset = null;
    	
    	if($rootScope.loginuser.role == 'ADMIN'){
    		attachOrderToUser = $scope.attachOrderToUser;
    		
    		if($scope.attachOrderToDeliverer != null){
    			orderStatus = 'Delivery_in_progress';
    			delivererIDset = $scope.attachOrderToDeliverer;
    		}
    	}
    	
    	
    	
        var order = {
        	articleOrders: angular.copy($scope.orderList),
        	price: angular.copy($scope.totalPrice),
        	status: orderStatus,      	
        	id: angular.copy($scope.allOrders.length),
        	idC: attachOrderToUser,
        	idD: delivererIDset,
        	idR: $scope.selectedRestaurantId,
        	note: $scope.note,
        	visibility: true,
        	dateOfOrder: moment().format('MMMM Do YYYY, h:mm:ss')    
        }
               
        dataService.create('orders', 'addOrder', order, function(res){	  
	    	if(res.status == 200){    		 
    	        $scope.orderList = [];
    	        $scope.totalPrice = 0;
    	        $scope.note = null;
    	        
    	        $scope.refreshOrders();    	       	       
	    	}else{
	    		console.log(res);
	    	}
	    });
        alert("You make order. Your orders can check in your history.")
        
        $scope.attachOrderToUser = null;
        $scope.attachOrderToDeliverer = null;
        //moramo primenjivati angular.copy na objektima/varijablama 
        //kada delimo vrednosti izmedju varijabli i varijabli koje su na $scopu, i obrnuto, kao i izmedju $scop/$rootScope varijabli.
//        console.log($scope.orderList);
//        order.listOfOrders[0].articleName = "";
//        order.listOfOrders[0].articleId = null;
//        console.log($scope.orderList);
        
    	}
        
    }
    
      if($rootScope.loginuser.role == "ADMIN"){
    $scope.attachOrderToUser = null;
     $scope.attachOrderToDeliverer = null;
  	  dataService.getAll('users', 'getUsers', null, function(res){
	    	if(res.status == 200){
	    		$scope.customerUsersArray = appService.lodashFilterBy(res.data, 'role', 'CUSTOMER');  
	    		$scope.delivererUsersArray = appService.lodashFilterBy(res.data, 'role', 'DELIVERER');  		    	
	    	}else{
	    		console.log(res);
	    		$scope.customerUsersArray = [];
	    		$scope.delivererUsersArray = [];
	    	}  	    	
	    });
      }

    
////////////////////////////////////////////////////////////////////////////////////// END USER    
    
    $scope.getArticleTypeName = function (articleTypeValue) {
        return appService.lodashFindBy($rootScope.enumArticleType, 'value', articleTypeValue);
    };;
    
    
}]);

