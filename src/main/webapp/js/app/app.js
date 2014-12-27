'use strict';

define(['angular'], function (angular) {
  return angular
    .module('app', ['app.services', 'app.config'])
    .config(['$routeProvider', function ($routeProvider) {
      $routeProvider
        .when('/home', { templateUrl: 'views/home.html', controller: 'HomeCtrl' })
        .when('/new', { templateUrl: 'views/new.html', controller: 'ProductCtrl' })
        .when('/edit/:id', { templateUrl: 'views/edit.html', controller: 'ProductCtrl' })
        .otherwise({ redirectTo: '/home' });
    }])
    .run(function ($rootScope, $location) {
      $rootScope.$on("$routeChangeSuccess", function(current, previous) {
        if (typeof previous.$$route !== 'undefined')
          ga('send', 'pageview', previous.$$route.templateUrl);
      });
    });
});
