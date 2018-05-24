var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	
        templateUrl: 'home.html'
    })
    .when("/cinema", {
    	
        templateUrl: 'a.html'
    }) 
});


app.controller('indexCtrl', function($scope, $window) {

	var type = $window.sessionStorage.getItem('usertype');
	
  
    
});
