app.controller('ShowCollection', ['$scope', '$http', function($scope, $http) {
	$http.get('/store/resource')
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

	$scope.loadDocument = function(key, value) {
		$http.post("/store/elementcollection", {db: key, collection: value})
			.success(function(data, status, headers, config) {
				$scope.info = data;
				console.log(data);
			}).error(function(data, status, headers, config) {
				console.log("error");
			});
	};

}]);