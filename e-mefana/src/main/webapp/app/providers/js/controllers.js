(function() {

	var providerControllers = angular.module('providerControllers', ['ngGeolocation']);

	  providerControllers.controller('RegisterController',
			  ['$scope',
			   '$rootScope', 
			   '$geolocation',
			   '$state',
			   '$filter',
			   'MetadataService',
			   'ListingService', 
			   function($scope,$rootScope,$geolocation,$state,$filter,MetadataService,ListingService) {
			
		     $scope.phone_pattern=/^((\+)|(00)|(\*)|())[0-9]{10,14}((\#)|())$/;	
		     
		     MetadataService.$promise.then(function(result){
		    	 //$scope.meatadata = angular.toJson(result, true);PROVIDER_REP_ROLES
		    	 $scope.cities = $filter('filter')(result, { key: "CITIES" })[0].value;
		    	 $scope.listingRoles = $filter('filter')(result, { key: "PROVIDER_REP_ROLES" })[0].value;
		    	 $scope.countries = $filter('filter')(result, { key: "COUNTRIES" })[0].value;
		    	 $scope.provider_categories = $filter('filter')(result, { key: "PROVIDER_CATEGORIES" })[0].value;
		    	 $scope.events = $filter('filter')(result, { key: "EVENTS" })[0].value;
		    	 $scope.services = $filter('filter')(result, { key: "SERVICES" })[0].value;
		    	 
		     });
			
			 $scope.features = [];
			 
			  $scope.provider = {
					  venues : [ ],
					  features : [ ],
					  services : [ ],
					  events : [ ],
			  };
			  

			 $scope.getCssClasses = function(ngModelContoller) {
				    return {
				      error: ngModelContoller.$invalid && ngModelContoller.$dirty,
				      success: ngModelContoller.$valid && ngModelContoller.$dirty
				    };
				  };

				  
				  $scope.showError = function(ngModelController, error) {
				    return ngModelController.$error[error];
				  };
				  
				  /**
				   * 
				   */
				  $scope.canGoNext = function() {
					    return $scope.listingForm.$valid && $scope.listingForm.$dirty ;
					};
					  
				  $scope.canSave = function(){
					  return $scope.provider.agree && $scope.canGoNext() && $scope.hasEventtypes() && $scope.isVenuesWithVenueSpace();
				  }	;  
				  
				  $scope.toJSON = function(obj) {
					    return JSON.stringify(obj, null, 2);
					  };
				 
			  //general information
			  $scope.provider.uselocation = false;
			  
			  $scope.loadmap = function(){
					if($scope.provider.uselocation){
						$scope.$geolocation = $geolocation;
						// basic usage
					    $geolocation.getCurrentPosition().then(function(location) {
					      $scope.provider.location = location.coords;
					    });
						    $geolocation.watchPosition({
						      timeout: 60000,
						      maximumAge: 2,
						      enableHighAccuracy: true
						    });
					}
					   
				};
			 $scope.hasVenue = function(){
				 if ($scope.provider.category === undefined) return false;
				 if ($scope.provider.category.type === undefined) return false;
				 return $scope.provider.category.type === "Venues";
			 };
			 
			 /*
			  * Returns true for no Venues providers
			  */
			 $scope.isVenuesWithVenueSpace = function(){
				 if (!$scope.hasVenue()|| $scope.provider.venues === undefined) return true;
					return $scope.provider.venues.length > 0;	
			 };
			 
			 $scope.hasServices = function(){
				 if($scope.provider.services === undefined) return false;
					
				 return $scope.provider.services.length > 0;
			 };
			 
			 $scope.hasEventtypes= function(){
				 if ($scope.provider.events === undefined) {
						return false;
					}
					
				 return $scope.provider.events.length > 0;
			 };
			 
			 $scope.addVenue = function(){
				 $scope.provider.venues.push({name : '',capacity : '',price : ''});
			 };
			 
			 $scope.removeVenue = function(index){
				 $scope.provider.venues.splice(index, 1);
			 };
			 
			 // services
			 $scope.checkAll = function(type) {
				 if(type == "E"){
					 
					 $scope.provider.events = angular.copy($scope.events);
				 }
					 
				 if(type == "S"){

					 //filter by provider category & then copy {providerTypes:provider.category}
					 $scope.provider.services= angular.copy($filter('filter')($scope.services, {providerTypes:$scope.provider.category}));
				 }
				 
				 if(type == "F"){
					 $scope.provider.features= angular.copy($scope.features);
				 }
					 
			 };
			 $scope.uncheckAll = function(type) {
				 if(type == "E"){$scope.provider.events = []; }
				 if(type == "F"){$scope.provider.features = []; }
				 if(type == "S"){$scope.provider.services = []; }
			    
			    
			  };
			  
			  //Features
			  $scope.provider.feature ='';
			  $scope.provider.agree = false;
			  
			 $scope.addFeature = function(){
				 $scope.provider.features.push({name :$scope.provider.feature,description:[ ] });
				 $scope.provider.feature='';//re-set to empty
			 }; 
			 
			 $scope.removeFeature = function(index){
				 $scope.provider.features.splice(index,1);
			 };
			  
			$scope.addFeatureDescription = function(index)  {
				 $scope.provider.features[index].description.push('');
			};
			
			$scope.removeFeatureDescription = function(index1,index)  {
				feature = $scope.provider.features[index1].description.splice(index, 1);
				
			};
			
			//photo 
			 $scope.provider.photo;
			 
//		photo =	 {
//				  "filesize": 54836,
//				  "filetype": "image/jpeg",
//				  "filename": "profile.jpg",
//				  "base64":   "/9j/4AAQSkZJRgABAgAAAQABAAD//gAEKgD/4gIcSUNDX1BST0ZJTEUAAQEAAAIMbGNtcwIQA..."
//				}
			 
			 $scope.provider.name="";
		$scope.submitListing = function(){
			$scope.httpResponse = {};
			$scope.listingSubmission = ListingService.save({},$scope.provider, function(httpResponse,responseHeaders){
				$scope.httpResponse = httpResponse; // think of returning listing code , for future correspondence
				$scope.provider = {};
				$state.go("register.registered");
			},function(httpResponse){
				$scope.httpResponse = httpResponse.data;
				$state.go("register.start");
			});
			
		};	 
		
			 
	} ]);
	  
	  providerControllers.controller('IndexController',
			  ['$scope','$rootScope',
			   function($scope,$rootScope) {
	} ]);
	  
	  providerControllers.controller('LoginController',
			  ['$scope','$rootScope','$location', '$cookieStore',
			   function($scope, $rootScope, $location, $cookieStore) {
	} ]);

})();

