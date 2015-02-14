(function() {

	var providerControllers = angular.module('providerControllers', ['ngGeolocation']);

	  providerControllers.controller('RegisterController',
			  ['$scope','$rootScope', '$geolocation',function($scope,$rootScope,$geolocation) {

				  
			 $scope.events = [
				                  'guest Events 1', 
				                  'user Events 2', 
				                  'customer Events 3', 
				                  'admin Events 4'
				                ];
			 $scope.services = [
				                  'guest services 1', 
				                  'user services 2', 
				                  'customer services 3', 
				                  'admin services 4'
				                ];
			 $scope.features = [
				                 {
				                	 name : 'Description lists',
				                	 description :[
				                	               'description list is perfect for defining terms',
				                	               'Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.',
				                	               ' Donec id elit non mi porta gravida at eget metus'
				                	              ]
				                 },
				                 {
				                	 name : 'Malesuada porta',
				                	 description :[
				                	               'Etiam porta sem malesuada magna mollis euismod',
				                	               'Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh' ,
				                	               ' ut fermentum massa justo sit amet risus',
				                	              ]
				                 },
				                ];
			 
			  $scope.provider = {
					  venues : [ {name:'',capacity:'',price:''}],
					  features : [ ],
					  services : [ ],
					  events : [ ],
			  };
			  

			$scope.listingRoles = [{
									value : 'MANAGER',
									label : 'Manager'
								}, {
									value : 'OWNER',
									label : 'Owner'
								}, {
									value : 'MARKETING_AGENCY',
									label : 'Marketing Agency'
								}, {
									value : 'OTHER',
									label : 'Other'
								} ];
			  

			 $scope.getCssClasses = function(ngModelContoller) {
				    return {
				      error: ngModelContoller.$invalid && ngModelContoller.$dirty,
				      success: ngModelContoller.$valid && ngModelContoller.$dirty
				    };
				  };
				  
				  $scope.showError = function(ngModelController, error) {
				    return ngModelController.$error[error];
				  };
				  
				  $scope.canGoNext = function() {
					    return $scope.listingForm.$valid && $scope.listingForm.$dirty ;
					};
					  
				  $scope.canSave = function(){
					  return $scope.provider.agree && $scope.canGoNext && $scope.hasEventsToServe && isVenuesWithVenueSpace;
				  }	;  
				  
				  $scope.toJSON = function(obj) {
					    return JSON.stringify(obj, null, 2);
					  };
				 
			  //general information
			  $scope.provider.uselocation = false;
			  
			  $scope.loadmap = function(){
					if($scope.provider.uselocation){
						$scope.$geolocation = $geolocation
						    $geolocation.watchPosition({
						      timeout: 60000,
						      maximumAge: 2,
						      enableHighAccuracy: true
						    });
					}
					   
				};
			 $scope.hasVenue = function(){
				 return $scope.provider.category == "Venues";
			 };
			 
			 $scope.isVenuesWithVenueSpace = function(){
				 if ($scope.provider.venues === undefined) return false;
					
				return  $scope.hasVenue ? $scope.provider.venues.length > 0 : true;
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
					 $scope.provider.services= angular.copy($scope.services);
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
				 $scope.features.push({name :$scope.provider.feature,description:[ ] });
				 $scope.provider.feature='';//re-set to empty
			 }; 
			 
			 $scope.removeFeature = function(index){
				 $scope.features.splice(index,1);
			 };
			  
			$scope.addFeatureDescription = function(index)  {
				 $scope.features[index].description.push('');
			};
			
			$scope.removeFeatureDescription = function(index1,index)  {
				feature = $scope.features[index1].description.splice(index, 1);
				
			};
			
			//photo 
			 $scope.provider.photo;
			 
//		photo =	 {
//				  "filesize": 54836,
//				  "filetype": "image/jpeg",
//				  "filename": "profile.jpg",
//				  "base64":   "/9j/4AAQSkZJRgABAgAAAQABAAD//gAEKgD/4gIcSUNDX1BST0ZJTEUAAQEAAAIMbGNtcwIQA..."
//				}
			
			 
	} ]);
	  
	  providerControllers.controller('IndexController',
			  ['$scope','$rootScope',
			   function($scope,$rootScope) {
	} ]);
	  
	  providerControllers.controller('LoginController',
			  ['$scope','$rootScope','$location', '$cookieStore',
			   function($scope, $rootScope, $location, $cookieStore) {
	} ]);
  

//   providerControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone', function($scope, $routeParams, Phone) {
//   $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
//    $scope.mainImageUrl = phone.images[0];
//  });
//
//  $scope.setImage = function(imageUrl) {
//    $scope.mainImageUrl = imageUrl;
//  }
//}]);

})();

