app.controller('ShowCollection', ['$scope', '$http', function($scope, $http) {
	$http.get('/resource')
		.success(function(data, status, headers, config) {
			$scope.data = data;
			angular.forEach($scope.data, function(value, key) {
				console.log(key + " : " + value);
			});
			console.log(data);
		})
		.error(function(data, status, headers, config) {
			alert("error");
		});
}]);