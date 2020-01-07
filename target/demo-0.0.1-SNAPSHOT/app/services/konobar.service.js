angular.module('crudApp').factory('konobarServis',
        [ '$injector','$resource', function konobarServis($injector) {

		$http = $injector.get('$http');
		var ulogovan = false;
		var prikazanA= false;
		var brisan=false;
		var podKon=false;
		var naplacen=false;
		
		// $konobarService.ulogovan nije vidljiv na ovom mestu
		// a mora da se promeni vrednost te promenljive u then delu programa (ako je rest poziv bio uspesan)
		// da bi mogli da promenimo 
		function logovanje(username, password){
			console.log("Service: logKonobar");
	
			var httpParams = {
					username:username,
					password:password,
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
//			.catch(function(response){
//				console.log("Error: Service-logovanje " + response.data);
//				throw "Pogresan pin!";
//			})
//			.finally();
		}
		
		
		function getKonobari() {
			var request = {
					method: 'GET',
					url: 'konobar/k'
			};
			
			return $http(request)
			.then(function(response){
				console.log("Service: getKonobari ");
				return response.data;
			});
		}
		
		function saveKonobar(konobar) {
			console.log("Service: saveKonobar");
	
			var data = {
					username:konobar.username,
					pass:konobar.pass
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
		function prikaziAzuriranje(){
			console.log("Service: prikaziAzuriranjeK");
			
			var data = {
					idkonobara:konobar.idkonobara,
					pass:konobar.pass,
					username:konobar.username
			}
			var request = {
					method: 'PUT',
					url: 'konobar/kazuriranje',
					data:JSON.stringify(data)
			};
			
			$http(request)
			.then(function(response){
				console.log("Service: prikaziAzuriranjeK " + response.data);
			})
			.catch(function(response){
				console.log("Error: Service-prikaziAzuriranjeKonobar " + response.data);
			})
			.finally();
			
		}
		function brisanjeK(idkonobara){
			console.log("Service: brisanjeK");
			
			var data = {
					idkonobara:idkonobara
					
			}
			var request = {
					method: 'DELETE',
					url: 'konobar/kbrisanje',
					params:data
			};
			
			return $http(request)
			.then(function(response){
				console.log("Service: brisanjeK ");
				return response.data;
			});
			
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
		function naplati(idPorudzbine){
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
		
		function getAllP(){
			console.log("Service: getAllPorudzbine");
	
			var request = {
					method: 'GET',
					url: 'porudzbina/p'
			};
			
			return $http(request)
			.then(function(response){
				console.log("Service: getAllPorudzbine ");
				return response.data;
			});
			
		}
		
		return  {
			'logovanje':logovanje,
			'saveKonobar' : saveKonobar,
			'getKonobari': getKonobari,
			'updateKonobar':updateKonobar,
			
			'brisanjeK':brisanjeK,
			'naplati':naplati,
			'getAllP': getAllP,
			ulogovan: false,
			prikazanA: false,
			brisan: false,
			podKon: false,
			naplacen: false
		};
	}]);