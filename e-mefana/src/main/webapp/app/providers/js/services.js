'use strict';

/* Services */
var providerServices = angular.module('providerServices', ['ngResource']);

providerServices.factory('MetaService', ['$resource',function($resource){
    return $resource('api/metadata', {},{
			    query : {
					method : 'GET',
					headers : {'Accept': 'application/json'},
					isArray: true
			  }
	  });
  
}]);

providerServices.factory('ListingService', ['$resource',function($resource){
    return $resource('api/provider', {},{
			    save : {
					method : 'POST',
					headers : {
						       'Accept': 'application/json',
						       'Content-Type': 'application/json'
						},
					isArray: false
			  }
	  });
  
}]);