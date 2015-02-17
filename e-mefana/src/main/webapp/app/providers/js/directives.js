'use strict';

/* Directives */
(function() {

	var providerDirectives = angular.module('providerDirectives', ['providerControllers']);

	providerDirectives.directive('providerHeader',function() {
	  return {
		  restrict :'E',
		  transclude: true,
		  templateUrl : 'partials/provider-header.html',
		  controller: 'IndexController'
	  };
  })
  .directive('providerFooter',function(){
	  return {
		  restrict :'E',
		  transclude: true,
		  templateUrl : 'partials/provider-footer.html'
	  };
  })
  
   .directive('registrationHeader',function(){
	  return {
		  restrict :'E',
		  transclude: true,
		  templateUrl : 'partials/registration-header.html'
	  };
  })
	.directive('providerWhyregister',function(){
		  return {
			  restrict :'E',
			  transclude: true,
			  templateUrl : 'partials/provider-whyregister.html'
			  //controller: 'RegisterController'
		  };
	  });
  

})();