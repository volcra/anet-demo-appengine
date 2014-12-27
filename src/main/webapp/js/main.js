/*
 * Copyright 2014 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
require.config({
  shim: {
    'angular': {
      exports: 'angular'
    },
    'angular_resource': {
      deps: ['angular']
    },
    'bootstrap': {
      deps: ['jquery']
    },
    'spin': {
      exports: 'Spinner'
    }
  },
  paths: {
    angular: 'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min',
    angular_resource: 'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular-resource.min',
    jquery: 'http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min',
    bootstrap: '../lib/bootstrap.min',
    spin: '../lib/spin.min'
  },
  baseUrl: 'js/app'
});

require([
  'angular',
  'jquery',
  'spin',
  'angular_resource',
  'bootstrap',
  'app',
  'controllers',
  'services',
  'config'
], function (angular, $, Spinner) {
  /* Spin
   -------------------------------------------------- */
  new Spinner({
    lines: 11, // The number of lines to draw
    length: 20, // The length of each line
    width: 11, // The line thickness
    radius: 20, // The radius of the inner circle
    corners: 1, // Corner roundness (0..1)
    rotate: 0, // The rotation offset
    direction: 1, // 1: clockwise, -1: counterclockwise
    color: '#000', // #rgb or #rrggbb
    speed: 1, // Rounds per second
    trail: 60, // Afterglow percentage
    shadow: true, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'spinner', // The CSS class to assign to the spinner
    zIndex: 2e9, // The z-index (defaults to 2000000000)
    top: 'auto', // Top position relative to parent in px
    left: 'auto' // Left position relative to parent in px
  }).spin($('.spinner-wrapper').get(0));

  angular.bootstrap(document, ['app']);

  $(document).on('click.menu', 'div.nav-collapse.in a', function (e) {
    $(e.target).parents('div.nav-collapse').collapse('toggle');
  });
});
