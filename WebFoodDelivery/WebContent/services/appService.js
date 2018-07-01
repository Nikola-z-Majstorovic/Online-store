myModule.factory('appService', [ '$rootScope', '$location', '$timeout',
		'$window', function($rootScope, $location, $timeout, $window) {
			return {
				init : function() {
				   $rootScope.loginuser = {
				    		username:"",
				    		password:""
				    };
						   
					$rootScope.changeView = function(view) {
						$location.path(view);
					};
					$rootScope.logOut = function() {
						$rootScope.changeView("/login");
					};

					$rootScope.enumVehicleTypes = [ {
						name : 'Car',
						value : 'Car'
					}, { 
						name : 'Bycicle',
						value : 'Bycicle'
					}, {
						name : 'Scooter',
						value : 'Scooter'
					} ];

					$rootScope.enumRestaurantCathegories = [ {
						name : 'Domestic kitchen',
						value : 'Domestic_kitchen'
					}, {
						name : 'Grill',
						value : 'Grill'
					}, {
						name : 'Chinese restaurant',
						value : 'Chinese_restaurant'
					}, { 
						name : 'Indian restaurant',
						value: 'Indian_restaurant'
					}, {
						name : 'Pizzeria',
						value : 'Pizzeria'
					}];

					$rootScope.enumArticleType = [ {
						name : 'Food',
						value : true
					}, {
						name : 'Drink',
						value : false
					} ];

					$rootScope.enumOrderStatuses = [ {
						name : 'Delivered',
						value : 1
					}, {
						name : 'Comming',
						value : 2
					}, {
						name : 'Processing',
						value : 3
					} ];
					
					$rootScope.enumUserRoles = [ {
						name : 'CUSTOMER',
						value : 'CUSTOMER'
					}, {
						name : 'ADMIN',
						value : 'ADMIN'
					} , {
						name : 'DELIVERER',
						value : 'DELIVERER'
					} ];
				},
				// #region Lodash helpers (remove/find/filter)
				lodashRemoveBy : function(array, propertyName, propertyValue) {
					return _.remove(array, [ propertyName, propertyValue ]);
				},
				lodashFindBy : function(array, propertyName, propertyValue) {
					return _.find(array, [ propertyName, propertyValue ]);
				},
				lodashFilterBy : function(array, propertyName, propertyValue) {
					return _.filter(array, [ propertyName, propertyValue ]);
				},
				lodashSortBy : function(array, propertyName) {
					return _.sortBy(array, [ propertyName ]);
				},
				findBy : function(array, propertyName, propertyValue) {
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
		} ]);