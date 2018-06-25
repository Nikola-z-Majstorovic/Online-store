var myModule = angular.module('myModule', ['ngRoute', 'ngResource']);

myModule.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

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

}]);

myModule.run(['$resource', '$rootScope', '$window', '$timeout', '$location', '$routeParams', 'appService', 'dataService', function ($resource, $rootScope, $window, $timeout, $location, $routeParams, appService, dataService) {


    appService.init();
    $rootScope.changeView('/testHome');
    //-----------------------------------------------------------------------------------------------------
    $rootScope.$on('$locationChangeStart', function () {
        $rootScope.route = $location.path();
        console.log($rootScope.route);
    });
}]);