var myModule = angular.module('myModule', ['ngRoute', 'ngResource']);

myModule.config(['$routeProvider', '$httpProvider','$locationProvider', function ($routeProvider, $httpProvider,$locationProvider) {
	$locationProvider.hashPrefix('');
    //#region Routes 
    $routeProvider
        .when('/login',
            {
                controller: 'loginCtrl',
                templateUrl: 'view/login.html',
                resolve: {}
            })
        .when('/home',
            {
                controller: 'homeCtrl',
                templateUrl: 'view/home.html',
                resolve: {}
            })
        .when('/registration',
            {
                controller: 'regCtrl',
                templateUrl: 'view/registration.html',
                resolve: {}
            })
        .when('/restaurant/:restaurantId',
            {
                controller: 'restaurantCtrl',
                templateUrl: 'view/restaurant.html',
                resolve: {}
            })
         .when('/testHome',
            {
                controller: 'testHomeCtrl',
                templateUrl: 'view/testHome.html',
                resolve: {}
            })
         .when('/vehicles',
            {
                controller: 'vehicleCtrl',
                templateUrl: 'view/vehicle.html',
                resolve: {}
            })
         .when('/users',
            {
                controller: 'userCtrl',
                templateUrl: 'view/user.html',
                resolve: {}
            })
       

}]);

myModule.run(['$resource', '$rootScope', '$window', '$timeout', '$location', '$routeParams', 'appService', 'dataService', function ($resource, $rootScope, $window, $timeout, $location, $routeParams, appService, dataService) {


    appService.init();
    $rootScope.changeView('/login');
    //-----------------------------------------------------------------------------------------------------
    $rootScope.$on('$locationChangeStart', function () {
        $rootScope.route = $location.path();
    });
}]);