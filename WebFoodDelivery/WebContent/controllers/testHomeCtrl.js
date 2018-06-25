myModule.controller('testHomeCtrl', ['$rootScope', '$scope', '$timeout', '$window', 'appService','dataService','$routeParams', function ($rootScope, $scope, $timeout, $window, appService,dataService,$routeParams) {
    //-----------------------------------------------------------------------------------------------------------
    console.log('we are in home ctrl');
    dataService.getAll('getRestaurants',null,function(res) {
//    	console.log(res);
    	if(res.status==200){
    		$rootScope.restaurants= res.data;
    		console.log($rootScope.restaurants);
    	}else {
    		console.log("greska");    		
    	}
    });
    $scope.navigateToRestaurant = function (restaurantId) {
    	$rootScope.changeView("/restaurant/"+restaurantId);
    }

//    $rootScope.restaurants = [
//        { restaurantId: 1, restaurantName: "Kod Gedze", restaurantAddress: "Mojkovacka 15", phone: "065-22-44-88", restaurantDescription: "This is restaurant description", restaurantCathegory: 3 },
//        { restaurantId: 2, restaurantName: "To brate", restaurantAddress: "Nikole Tesle 6", phone: "065-22-44-88", restaurantDescription: "This is restaurant description", restaurantCathegory: 2 },
//        { restaurantId: 3, restaurantName: "Luda noc", restaurantAddress: "Uroseva 2", phone: "065-22-44-88", restaurantDescription: "This is restaurant description", restaurantCathegory: 1 },
//        { restaurantId: 4, restaurantName: "Dve sestre", restaurantAddress: "Bagdalska 17", phone: "065-22-44-88", restaurantDescription: "This is restaurant description", restaurantCathegory: 1 },
//    ];

    $rootScope.allAvailableArticles = [
        { articleId: 1, articleName: "Pileci medaljoni", price: 25, articleType: 1, articleDescription: "This is article description" },
        { articleId: 2, articleName: "Rolovana teletina", price: 50, articleType: 1, articleDescription: "This is article description" },
        { articleId: 3, articleName: "Pljeskavica", price: 15, articleType: 1, articleDescription: "This is article description" },
        { articleId: 4, articleName: "Pica kapricosa", price: 20, articleType: 1, articleDescription: "This is article description" },
        { articleId: 5, articleName: "Grcka salata", price: 10, articleType: 1, articleDescription: "This is article description" },
        { articleId: 6, articleName: "Mesana salata", price: 10, articleType: 1, articleDescription: "This is article description" },
        { articleId: 7, articleName: "Jelen pivo", price: 9, articleType: 2, articleDescription: "This is article description" },
        { articleId: 8, articleName: "Coca Cola", price: 7, articleType: 2, articleDescription: "This is article description" },
        { articleId: 9, articleName: "Limunada", price: 10, articleType: 2, articleDescription: "This is article description" },
        { articleId: 10, articleName: "Fanta", price: 7, articleType: 2, articleDescription: "This is article description" },
        { articleId: 11, articleName: "Djus", price: 5, articleType: 2, articleDescription: "This is article description" },
    ];

    
    $rootScope.restaurantAndArticles = [
        { restaurantId: 1, articleId: 1 },
        { restaurantId: 1, articleId: 2 },
        { restaurantId: 1, articleId: 3 },
        { restaurantId: 1, articleId: 4 },
        { restaurantId: 1, articleId: 5 },
        { restaurantId: 2, articleId: 1 },
        { restaurantId: 2, articleId: 8 },
        { restaurantId: 2, articleId: 9 },
        { restaurantId: 2, articleId: 10 },
        { restaurantId: 2, articleId: 6 },
        { restaurantId: 3, articleId: 5 },
        { restaurantId: 3, articleId: 7 },
        { restaurantId: 3, articleId: 6 },
        { restaurantId: 3, articleId: 4 },
        { restaurantId: 3, articleId: 11 },
        { restaurantId: 4, articleId: 5 },
        { restaurantId: 4, articleId: 1 },
        { restaurantId: 4, articleId: 10 },
        { restaurantId: 4, articleId: 3 },
        { restaurantId: 4, articleId: 11 },
    ];


    //Articles for display, when restaurant is selected
    $rootScope.Articles = [];
    //User buyer cart for ordered articles
    $rootScope.orderList = [];
    //Dispatcher list with all orders
    $rootScope.dispatcherOrders = [];    
    //total sum price for card
    $rootScope.totalPrice = 0;
    //Selected restaurant for tracking
    $rootScope.selectedRestaurant = null;
  
    $scope.showMenu = function (restaurantID) {
  
        $rootScope.Articles = [];
        $rootScope.selectedRestaurant = restaurantID;
        dataService.getAll('readArticlesFromRestaurant',/*$routeParams.restaurantId,*/restaurantID,function(res) {
        	if(res.status==200){   	
        		$rootScope.Articles = res.data;
        		console.log(res.data);
        	}else {
        		console.log("greska");    		
        	}
        });

//        var articleIdsForRestaurant = angular.copy(appService.lodashFilterBy($rootScope.restaurantAndArticles, 'restaurantId', restaurantID));
//
//        console.log(articleIdsForRestaurant);
//        for (var i = 0; i < articleIdsForRestaurant.length; i++) {
//            var article = angular.copy(appService.lodashFindBy($rootScope.allAvailableArticles, 'articleId', articleIdsForRestaurant[i].articleId));
//            if (article != null) {
//                $rootScope.Articles.push(article);
//            }
//        }
//
//        console.log($rootScope.Articles);

    };



    $scope.orderArticle = function (article) {
        console.log(article.articleId);
        var articleExists = appService.lodashFindBy($rootScope.orderList, 'articleId', article.articleId);
        if (articleExists != null) {

            articleExists.quantity = articleExists.quantity + 1;
            articleExists.price = articleExists.quantity * articleExists.priceByUnit;

            
        } else {
            var article = {
                articleId: article.articleId,
                articleName: article.articleName,
                quantity: 1,
                price: article.price,
                priceByUnit: article.price
            }

            $rootScope.orderList.push(article);
        }

        var sumPriceTotal = 0;
        for (var i = 0; i < $rootScope.orderList.length; i++) {
            sumPriceTotal = sumPriceTotal + $rootScope.orderList[i].price;            
        }
        $scope.totalPrice = sumPriceTotal;

    };

    $scope.removeOrderFromCart = function (article) {
        var articleToRemove = appService.lodashFindBy($rootScope.orderList, 'articleId', article.articleId);

        if (articleToRemove.quantity > 1) {
            articleToRemove.quantity = articleToRemove.quantity - 1;
            articleToRemove.price = articleToRemove.price - articleToRemove.priceByUnit;
        } else {
            if ($rootScope.orderList.length >= 1) {
                $rootScope.orderList = _.without($rootScope.orderList, article);
            }
        }

        var sumPriceTotal = 0;
        for (var i = 0; i < $rootScope.orderList.length; i++) {
            sumPriceTotal = sumPriceTotal + $rootScope.orderList[i].price;
        }
        $scope.totalPrice = sumPriceTotal;
    }

    $scope.makeOrder = function (userInformation) {

        var order = {
            listOfOrders: $rootScope.orderList,
            totalToPay: $scope.totalPrice,
            userAddress: 'perina adresa',
            userPhone: '1231231',
            userName: 'Djordje Djuric',
            orderStatus: 'active'
        }

        $rootScope.dispatcherOrders.push(order);
    }


    $scope.getRestaurantCathegoryName = function (restaurantCathegoryValue) {
        return appService.lodashFindBy($rootScope.enumRestaurantCathegories, 'value', restaurantCathegoryValue);
    };

    $scope.getArticleTypeName = function (articleTypeValue) {
        return appService.lodashFindBy($rootScope.enumArticleType, 'value', articleTypeValue);
    };
}]);

