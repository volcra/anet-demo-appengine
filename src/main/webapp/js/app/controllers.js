'use strict';

define(['app', 'jquery'], function (app, $) {
  app
    .controller('MenuCtrl', ['$scope', '$location', function ($scope, $location) {
      $scope.menu = {
        items: [
          {
            text: 'Inicio', url: '#/home'
          }, {
            text: 'Salir', url: '/logout'
          }
        ]
      }

      $scope.getMenuItemClassStyle = function (item) {
        return $location.url().search(item.url.replace('#', '')) == 0 ? 'active' : '';
      }
    }])
    .controller('HomeCtrl', ['$scope', '$routeParams', 'Product', function ($scope, $routeParams, Product) {
      $('.loading').toggle();
      $scope.data = Product.query({}, function () { $('.loading').toggle(); }, function () { $('.loading').toggle(); });
      $scope.success = $routeParams.success;
    }])
    .controller('ProductCtrl', ['$scope', '$routeParams', '$location', 'Product',
      function ($scope, $routeParams, $location, Product) {
        $scope.item = {};
        $scope.isEdit = false;

        if (typeof $routeParams.id != 'undefined') {
          $scope.item = Product.get({ id: $routeParams.id });
          $scope.isEdit = true;
          $scope.success = $routeParams.from == '/new' && $routeParams.success;
        }

        $scope['new'] = function () {
          $location.path('/new').search({});
          $scope.$apply();
        }

        $scope.edit = function () {
          $location.path('/edit/' + $scope.item.id).search({});
          $scope.$apply();
        }

        $scope.save = function () {
          $scope.success = false;
          if (typeof $scope.item.id == 'undefined') {
            Product.save({}, $scope.item, function (item) {
              $scope.success = true;
              var from = $location.path();
              $location.path('/edit/' + item.id).search({ from: from, success: true });
            }, function () {
              $scope.success = false;
              $scope.failure = true;
            });
          } else {
            $scope.item.$update(function (item) {
              $scope.success = true;
            }, function () {
              $scope.success = false;
              $scope.failure = true;
            });
          }
        }

        $scope['delete'] = function () {
          $('#warning').modal('hide');
          $scope.item.$remove(function () {
            $scope.success = true;
            var from = $location.path();
            $location.replace().path('/home').search({ from: from, success: true });
          }, function () {
            $scope.success = false;
            $scope.failure = true;
          });
        }
      }]);
});
