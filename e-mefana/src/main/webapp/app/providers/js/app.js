'use strict';
angular.module('providerApp', [
        'ui.bootstrap',
		'ui.router',
		'ngCookies', 
		'checklist-model',
        'providerControllers',
        'providerFilters',
        'providerServices',
        'providerDirectives'
     ])
	.config(
			[ '$stateProvider', '$urlRouterProvider', '$httpProvider', function($stateProvider,$urlRouterProvider, $httpProvider) {
				
				$stateProvider
					.state('home', {
					    url: '/',
					    templateUrl: 'provider-index.html',
					    controller: 'IndexController'
					  })
					  .state('register', {
					    url: '/registration',
					    templateUrl: 'partials/provider-signup.html',
					    controller:'RegisterController'
					  })
					  .state('register.start', {
					    url: '/listing-general-info',
					    templateUrl: 'partials/provider-general-info.html'
					  })
					  .state('register.features', {
					    url: '/listing-features',
					    templateUrl: 'partials/provider-features.html'
					  })
					   .state('register.location', {
					    url: '/provider-location',
					    templateUrl: 'partials/provider-location.html'
					  })
					   .state('register.services', {
					    url: '/provider-service-offering',
					    templateUrl: 'partials/provider-services.html'
					  })
					  .state('login', {
					    url: '/login',
					    templateUrl: 'partials/provider-signin.html',
					    controller: 'LoginController'
					  });
				
				 $urlRouterProvider.otherwise('/');
			
  }])
  .run([
        '$rootScope', 
        '$location',
        '$state', 
        '$stateParams',
        '$cookieStore',
        function($rootScope, $location,$state, $stateParams,$cookieStore) {
        	// It's very handy to add references to $state and $stateParams to the $rootScope
            // so that you can access them from any scope within your applications.For example,
            // <li ng-class="{ active: $state.includes('contacts.list') }"> will set the <li>
            // to active whenever 'contacts.list' or one of its decendents is active.
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;
        }
        ]);




