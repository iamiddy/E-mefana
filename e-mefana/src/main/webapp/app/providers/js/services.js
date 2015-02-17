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