myModule.controller('restaurantCtrl', ['$rootScope', '$scope', '$timeout', '$window','dataService','appService','$routeParams', function ($rootScope, $scope, $timeout, $window,dataService,appService,$routeParams) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in restaurant ctrl');
    $scope.selectedRestaurantId = Number($routeParams.restaurantId);
    dataService.getAll('readArticlesFromRestaurant',$routeParams.restaurantId,function(res) {
    	if(res.status==200){   		
    		console.log(res.data);
    	}else {
    		console.log("greska");    		
    	}
    });
    console.log($scope.selectedRestaurantId);
    $rootScope.restaurants=[{"id":0,"name":"Bas dobar","adress":"selajckih buna","category":"Domestic_kitchen","food": [{"id":1,
    	"name":"soup",
    	"price":10.0,
    	"description":"chicken soup",
    	"amount":1.0},
    	 { "id":2,
    	"name":"meat",
    	"price":50.0,
    	"description":"chicken",
    	"amount":1.0}]},
    	{"id":1,"name":"Montevideo","adress":"bla","category":"Domestic_kitchen","foods":[{"id":1,
		"name":"soup",
		"price":10.0,
		"description":"chicken soup",
		"amount":1.0},
		 { "id":2,
		"name":"meat",
		"price":50.0,
		"description":"chicken",
		"amount":1.0}]}];
    console.log($rootScope.restaurants);
    $scope.restaurant = angular.copy(appService.lodashFindBy( $rootScope.restaurants,"id", $scope.selectedRestaurantId));
    console.log($scope.restaurant);
    if($rootScope.myOrders==undefined) {
    		$rootScope.myOrders=[];
    	
    }
    
    $scope.orderArticle= function (restaurantId,article) {
    	var order={
    			restaurantId: restaurantId,
    			article : article,
    			articleId : article.id,
    			quantity : 1
    	}
        var articleExists = appService.lodashFindBy( $rootScope.myOrders,"articleId", order.articleId);
    	if(articleExists!=null) {
    		articleExists.quantity = articleExists.quantity+1;
    	}else {
        	$rootScope.myOrders.push(order);
    	}
    	console.log($rootScope.myOrders);
    }
}]);

