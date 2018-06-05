var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/signUp", {
		templateUrl: 'partials/signUp.html'
    }).when("/signIn", {
		templateUrl: 'partials/signIn.html'
    })
});


