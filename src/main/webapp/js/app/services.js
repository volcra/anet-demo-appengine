'use strict';

define(['angular'], function (angular) {
  angular
    .module('app.services', ['ngResource'])
    .factory('Product', function ($resource, config) {
      return $resource(config.api + '/api/product/:id', { id: '@id' }, {
        update: {
          method: 'PUT'
        }
      });
    });
});
