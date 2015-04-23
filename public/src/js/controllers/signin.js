'use strict';

/* Controllers */
  // signin controller
app.controller('SigninFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    
    // Logout
    $http.get('/logout').success(function(data) {
    	console.log("Success clean session");
    }).error(function(response) {
    	console.log("Error clean session");
    });
    
    $scope.login = function() {
      $scope.authError = null;
      // Try to login
      $http.post('/login', {username: $scope.user.email, password: $scope.user.password})
      .then(function(response) {
        if ( response.data.user === 'error') {
          $scope.authError = 'Email or Password not right';
        }else{
          $state.go('app.dashboard-v1');
        }
      }, function(x) {
        $scope.authError = 'Server Error';
      });
    };
  }])
;