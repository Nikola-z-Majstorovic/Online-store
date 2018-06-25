myModule.factory('appService', ['$rootScope', '$location', '$timeout', '$window', function ($rootScope, $location, $timeout, $window) {
    return {
        init: function () {
            $rootScope.changeView = function (view) {
            	console.log('usao u rootscope' + view);
                $location.path(view);
            };
	        $rootScope.logOut = function(){
	        	$rootScope.changeView("/login");
	        };
	        $rootScope.enumRestaurantCathegories = [
	              { name: 'Domestic kitchen', value: "Domestic_kitchen" },
	              { name: 'Fast food', value: 2 },
	              { name: 'Chinese food', value: 3 }
	            ];

	            $rootScope.enumArticleType = [
	              { name: 'Meal', value: "FOOD" },
	              { name: 'Drink', value: "DRINK" }
	            ];

	            $rootScope.enumDeliveredActive = [
	              { name: 'Delivered', value: 'delivered' },
	              { name: 'Active', value: 'active' }
	            ];
        },
        //#region Lodash helpers (remove/find/filter)
        lodashRemoveBy: function (array, propertyName, propertyValue) {
            return _.remove(array, [propertyName, propertyValue]);
        },
        lodashFindBy: function (array, propertyName, propertyValue) {
            return _.find(array, [propertyName, propertyValue]);
        },
        lodashFilterBy: function (array, propertyName, propertyValue) {
            return _.filter(array, [propertyName, propertyValue]);
        },
        lodashSortBy: function (array, propertyName) {
            return _.sortBy(array, [propertyName]);
        },
        findBy: function (array, propertyName, propertyValue) {
            if (array) {
                if (array.length != 0) {
                    for (var i = 0; i < array.length; i++) {
                        if (array[i][propertyName] === propertyValue) {
                            return array[i];
                        }
                    }
                }
            }
            return null;
        }
    }
}]);