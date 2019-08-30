angular.module('crudApp').factory('konobarServis',
        [ '$injector','$resource', function konobarServis($injector) {

		$http = $injector.get('$http');

		
		function logovanje(konobar){
			console.log("Service: logKonobar");
	
			var httpParams = {
					username:'user1',
					password:'pass1'
			}
			var request = {
					method: 'GET',
					url: 'konobar/klog',
					params:httpParams
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: logovanje " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-logovanje " + response.data);
			})
			.finally();
		}
		
		
		function getKonobari() {
			var request = {
					method: 'GET',
					url: 'konobar/k'
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: getKonobari " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-getKonobari " + response.data);
			})
			.finally();
		}
		
		function saveKonobar(konobar) {
			console.log("Service: saveKonobar");
	
			var data = {
					idkonobara:konobar.idkonobara,
					pass:konobar.pass,
					username:konobar.username
			}
			
			var request = {
				method: 'POST',
				url: 'konobar/ksave',
				data: JSON.stringify(data)
			};
			
			$http(request)
			.then(function(response) {
				console.log("Service: saveKonobar " + response.data);
			})
			.catch(function(response) {
				console.log("Service: saveKonobar " + response.data);
			})
			.finally();
		}
		function updateKonobar(konobar){
			console.log("Service: updateKonobar");
	
			var data = {
					idkonobara:konobar.idkonobara,
					pass:konobar.pass,
					username:konobar.username
			}
			var request = {
					method: 'PUT',
					url: 'konobar/kupdate',
					data:JSON.stringify(data)
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: updateKonobar " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-updateKonobar " + response.data);
			})
			.finally();
		}
		function deleteKonobar(idKonobara){
			console.log("Service: deleteKonobar");
	
			var httpParams = {
					idkonobara: idKonobara
			}
			var request = {
					method: 'DELETE',
					url: 'konobar/kdel',
					params:httpParams
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: deleteKonobar " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-deleteKonobar " + response.data);
			})
			.finally();
		}
		function naplata(idPorudzbine){
			console.log("Service: naplata");
	
			var httpParams = {
					idPorudzbine: idPorudzbine
			}
			var request = {
					method: 'GET',
					url: 'konobar/knaplata',
					params:httpParams
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: naplata " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-naplata " + response.data);
			})
			.finally();
			
		}
		
		return  {
			'logovanje':logovanje,
			'saveKonobar' : saveKonobar,
			'getKonobari': getKonobari,
			'updateKonobar':updateKonobar,
			'deleteKonobar':deleteKonobar,
			'naplata':naplata
		};
	}]);