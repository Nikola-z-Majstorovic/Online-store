//app.factory('userFactory', function($http) {
//	
//	
//	var factory = {};
//	factory.getUsers= function() {
//		return $http.get('/WebFoodDelivery/rest/database/getUsers');
//	};
//
//	factory.addUser = function(user) {
//		return $http.post('/WebFoodDelivery/rest/database/addUser', user);
//	};
//	
//	factory.modifyUser = function(user) {
//		return $http.post('/WebFoodDelivery/rest/database/modifyUser', user);
//	};
//	
//	factory.deleteUser = function(user) {
//		return $http.delete('/WebFoodDelivery/rest/database/deleteUser', user);
//	};
//	
//	return factory;
//	
//});
//
//
//app.factory('restaurantFactory', function($http) {
//	
//	
//	var factory = {};
//	factory.getRestaurants= function() {
//		return $http.get('/WebFoodDelivery/rest/database/getRestaurants');
//	};
//
//	factory.addRestaurant = function(restaurant) {
//		return $http.post('/WebFoodDelivery/rest/database/addRestaurants', restaurant);
//	};
//	
//	factory.modifyRestaurants = function(restaurant) {
//		return $http.post('/WebFoodDelivery/rest/database/modifyRestaurants', restaurant);
//	};
//	
//	factory.deleteRestaurant = function(restaurant) {
//		return $http.delete('/WebFoodDelivery/rest/database/deleteRestaurant', restaurant);
//	};
//	
//	return factory;
//	
//});
//
//
//app.factory('foodFactory', function($http) {
//	
//	var factory = {};
//	factory.getFood= function() {
//		return $http.get('/WebFoodDelivery/rest/database/getFood');
//	};
//
//	factory.addFood = function(food) {
//		return $http.post('/WebFoodDelivery/rest/database/addFood', food);
//	};
//	
//	factory.modifyFood = function(food) {
//		return $http.post('/WebFoodDelivery/rest/database/modifyFood', food);
//	};
//	
//	factory.deleteFood = function(volonter) {
//		return $http.delete('/WebFoodDelivery/rest/database/deleteFood', food);
//	};
//	
//	return factory;
//	
//});
//
//
//app.factory('drinkFactory', function($http) {
//		
//	var factory = {};
//	factory.getDrinks= function() {
//		return $http.get('/WebFoodDelivery/rest/database/getDrinks');
//	};
//
//	factory.addDrink = function(drink) {
//		return $http.post('/WebFoodDelivery/rest/database/addDrink', drink);
//	};
//	
//	factory.modifyDrink = function(drink) {
//		return $http.post('/WebFoodDelivery/rest/database/modifyDrink', drink);
//	};
//	
//	factory.deleteDrink = function(drink) {
//		return $http.delete('/WebFoodDelivery/rest/database/getUsers', drink);
//	};
//	
//	return factory;
//	
//});
//
//app.factory('vehicleFactory', function($http) {
//	
//	
//	var factory = {};
//	factory.getvehicle= function() {
//		return $http.get('/WebFoodDelivery/rest/database/getUsers');
//	};
//
//	factory.addVehicle = function(vehicle) {
//		return $http.post('/WebFoodDelivery/rest/database/getUsers', vehicle);
//	};
//	
//	factory.modifyVehicle = function(vehicle) {
//		return $http.post('/WebFoodDelivery/rest/database/modifyVehicle', vehicle);
//	};
//	
//	factory.deleteVehicle = function(vehicle) {
//		return $http.delete('/WebFoodDelivery/rest/database/deleteVehicle', vehicle);
//	};
//	
//	return factory;
//	
//});
