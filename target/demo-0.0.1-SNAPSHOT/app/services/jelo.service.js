angular.module('crudApp').factory('jeloServis', jeloServis);
jeloServis.$inject = [ '$resource', '$injector'];
function jeloServis($resource, $injector) {
	
	$http = $injector.get('$http');
	
	function getJela() {
		
		var request = {
				method: 'GET',
				url: 'jelo/j'
		};
		
		$http(request)
		.then(function(response){
			console.log("Service: getJela " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-getJela " + response.data);
		})
		.finally();
	}
	
	function saveJelo(jelo) {
		console.log("Service: saveJelo");

		var data = {
				idJela: jelo.idJela,
				nazivJ: jelo.nazivJ,
				cenaJ: jelo.cenaJ,
				kategorijaJ: jelo.kategorijaJ,
				kolicinaJ: jelo.kolicinaJ
		}
		
		var request = {
			method: 'POST',
			url: 'jelo/jsave',
			data: JSON.stringify(data)
		};
		
		$http(request)
		.then(function(response) {
		})
		.catch(function(response) {
		})
		.finally();
	}
	function updateJelo(jelo){
		console.log("Service: updateJelo");

		var data = {
				idJela: jelo.idJela,
				nazivJ: jelo.nazivJ,
				cenaJ: jelo.cenaJ,
				kategorijaJ: jelo.kategorijaJ,
				kolicinaJ: jelo.kolicinaJ
		}
		var request = {
				method: 'PUT',
				url: 'jelo/jupdate',
				data:JSON.stringify(data)
		};
		
		$http(request)
		.then(function(response){
			console.log("Service: updateJelo " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-updateJelo " + response.data);
		})
		.finally();
	}
	function deleteJelo(idJela){
		console.log("Service: deleteJelo");

		var httpParams = {
				idJela:'5'
		}
		var request = {
				method: 'DELETE',
				url: 'jelo/jdel',
				params:httpParams
		};
		
		$http(request)
		.then(function(response){
			console.log("Service: deleteJelo " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-deleteJelo " + response.data);
		})
		.finally();
	}
	function getJelo(idJela){
		console.log("Service: getJelo");

		var httpParams = {
				idJela:1
		}
		var request = {
				method: 'GET',
				url: 'jelo/jfindO',
				params:httpParams
		};
		
		$http(request)
		.then(function(response){
			console.log("Service: getJelo " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-getJelo " + response.data);
		})
		.finally();
		
	}
	
	return  {
		'save' : saveJelo,
		'getJela': getJela,
		'updateJelo':updateJelo,
		'deleteJelo':deleteJelo,
		'getJelo':getJelo
	};
}