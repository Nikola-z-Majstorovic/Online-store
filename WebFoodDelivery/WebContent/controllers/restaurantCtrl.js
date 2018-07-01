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
	    		
	    		console.log($scope.Articles);
	
	    	}else {
	    		console.log(res);    		
	    	}
	    });
    };
    
    $scope.refreshArticles();

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
    	
//    	var article = angular.copy($scope.selectedArticle);
    	
    	delete $scope.selectedArticle.Editable;
    	//$scope.selectedArticle.isFood = angular($scope.selectedArticle.food);
    	//delete $scope.selectedArticle.food;

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
    		$scope.selectedArticle.id = $scope.Articles.length + 1;
    		
    		
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
    
    $scope.dispatcherOrders=[];
    if($scope.odredlist== undefined) {
    $scope.orderList=[];
	}
    $scope.totalPrice=0;

    
 
    
    
      $scope.orderArticle = function (article) {
        console.log(article.id);
        var articleExists = appService.lodashFindBy($scope.orderList, 'articleId', article.id);
        if (articleExists != null) {

            articleExists.quantity = articleExists.quantity + 1;
            articleExists.price = articleExists.quantity * articleExists.priceByUnit;

            
        } else {
            var article = {
                articleId: article.id,
                articleName: article.name,
                quantity: 1,
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
        var articleToRemove = appService.lodashFindBy($scope.orderList, 'articleId', article.articleId);

        	console.log(articleToRemove.data);
	        if (articleToRemove.quantity > 1) {
	            articleToRemove.quantity = articleToRemove.quantity - 1;
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
    
    $scope.makeOrder = function () {

    	console.log("usao u make order");
    
        var order = {
            listOfOrders: angular.copy($scope.orderList),
            totalToPay: angular.copy($scope.totalPrice),
//            userAddress: $rootScope.loginuser.adress,
//            userPhone: $rootScope.loginuser.phone,
//            userName: $rootScope.loginuser.username,
            orderStatus: 3, //processing order
//            userId :  $rootScope.loginuser.userId,
            delivererUserId: null,
            orderId: angular.copy($scope.dispatcherOrders.length),
            orderedDate: new Date()      
        }
        
        //moramo primenjivati angular.copy na objektima/varijablama 
        //kada delimo vrednosti izmedju varijabli i varijabli koje su na $scopu, i obrnuto, kao i izmedju $scop/$rootScope varijabli.
//        console.log($scope.orderList);
//        order.listOfOrders[0].articleName = "";
//        order.listOfOrders[0].articleId = null;
//        console.log($scope.orderList);
        
        $scope.dispatcherOrders.push(order);
        $scope.orderList = [];
        $scope.totalPrice = 0;
        
        
    }
    
    $scope.getArticleTypeName = function (articleTypeValue) {
        return appService.lodashFindBy($rootScope.enumOrderStatuses, 'value', articleTypeValue);
    };
    
    $scope.getOrderStatusName = function (orderTypeValue) {
    	console.log(orderTypeValue);
        return appService.lodashFindBy($rootScope.enumOrderStatuses, 'value', orderTypeValue);
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    dataService.getAll('readArticlesFromRestaurant',$routeParams.restaurantId,function(res) {
//    	if(res.status==200){   		
//    		console.log(res.data);
//    	}else {
//    		console.log("greska");    		
//    	}
//    });
//    console.log($scope.selectedRestaurantId);
//    $rootScope.restaurants=[{"id":0,"name":"Bas dobar","adress":"selajckih buna","category":"Domestic_kitchen","food": [{"id":1,
//    	"name":"soup",
//    	"price":10.0,
//    	"description":"chicken soup",
//    	"amount":1.0},
//    	 { "id":2,
//    	"name":"meat",
//    	"price":50.0,
//    	"description":"chicken",
//    	"amount":1.0}]},
//    	{"id":1,"name":"Montevideo","adress":"bla","category":"Domestic_kitchen","foods":[{"id":1,
//		"name":"soup",
//		"price":10.0,
//		"description":"chicken soup",
//		"amount":1.0},
//		 { "id":2,
//		"name":"meat",
//		"price":50.0,
//		"description":"chicken",
//		"amount":1.0}]}];
//    console.log($rootScope.restaurants);
//    $scope.restaurant = angular.copy(appService.lodashFindBy( $rootScope.restaurants,"id", $scope.selectedRestaurantId));
//    console.log($scope.restaurant);
//    if($rootScope.myOrders==undefined) {
//    		$rootScope.myOrders=[];
//    	
//    }
//    
//    $scope.orderArticle= function (restaurantId,article) {
//    	var order={
//    			restaurantId: restaurantId,
//    			article : article,
//    			articleId : article.id,
//    			quantity : 1
//    	}
//        var articleExists = appService.lodashFindBy( $rootScope.myOrders,"articleId", order.articleId);
//    	if(articleExists!=null) {
//    		articleExists.quantity = articleExists.quantity+1;
//    	}else {
//        	$rootScope.myOrders.push(order);
//    	}
//    	console.log($rootScope.myOrders);
//    }
}]);

