var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	
        templateUrl: 'index.html'
    })
    .when("/cinema", {
        templateUrl: 'home.html'
    }) 
});


app.controller('indexCtrl', function($scope, $window) {

	  
});
