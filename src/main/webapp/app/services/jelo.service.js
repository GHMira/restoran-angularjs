angular.module('crudApp').factory('jeloServis',
        [ '$injector','$resource', function jeloServis($injector) {
        	
	$http = $injector.get('$http');
	var prikaziJela=false;
	var prikaziDnevniMeni=false;
	var porucen=false;
	var prikazanaP=false;
	var saveJ=false;
	var menjan=false;
	var aDM=false;
	var cdm=false;
	var oobrisan;
	
	function getJela() {
		
		var request = {
				method: 'GET',
				url: 'jelo/j'
		};
		
		return $http(request)
		.then(function(response){
			console.log("Service: getjela ");
			return response.data;
		});
	}
	function getOneDM(datumdm){
		console.log(datumdm);
		var data = {
				datumdm:datumdm
		}
		
		var request = {
			method: 'GET',
			url: 'dm/dmfindOne',
			params: data
		};
		return $http(request)
		.then(function(response){
			console.log("Service: prikaziDnevniMeni ");
			return response.data;
		});
	}
	function prikaziDnevniM(datumdm) {
		console.log(datumdm);
		var data = {
				datumdm:datumdm
		}
		
		var request = {
			method: 'GET',
			url: 'jelo/jdm',
			params: data
		};
		return $http(request)
		.then(function(response){
			console.log("Service: prikaziDnevniMeni ");
			return response.data;
		});
	}
	function updateDnevniMeni(iddm){
		console.log("Service: updateDnevniMeni");
		
		var today = new Date();
		var datumdm = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		
		var data = {
				iddm: iddm,
				datumdm: datumdm
				
		}
		var request = {
				method: 'GET',
				url: 'dm/dmupdate',
				params:data
		};
		
		return $http(request)
		.then(function(response){
			console.log("Service: updateDnevniMeni " + response.data);
			return response.data;
		});
	}
	function porucivanje(idkonobara){
		//console.log(idkonobar);
//		var data = {
//				idkonobara:idkonobar
//		}
		
		var request = {
			method: 'POST',
			url: 'porudzbina/psave/' + idkonobara
		};
		return $http(request)
		.then(function(response){
			console.log("Service: prikaziDnevniMeni ");
			return response.data;
		});
		
	}
	function kreirajStavku(idPorudzbine, idJela){
		
		var request = {
			method: 'POST',
			url: 'sp/spsave/' + idPorudzbine + '/' + idJela
		};
		return $http(request)
		.then(function(response){
			console.log("Service: saveStavka ");
			return response.data;
		});
	}
	function prikaziP(idPorudzbine){
		console.log(idPorudzbine);
		var request = {
				method: 'POST',
				url: 'sp/spp/'+idPorudzbine +'/'
			};
			return $http(request)
			.then(function(response){
				console.log("Service: getStavkePor ");
				return response.data;
			});
		
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
	
	function prikaziJela(){
		console.log("Service: prikaziJela");
		
		var data = {
				idJela: jelo.idJela,
				nazivJ:jelo.nazivJ,
				kategorijaJ: jelo.kategorijaJ,
				kolicinaJ: jelo.kolicinaJ,
				cenaJ: jelo.cenaJ
				
		}
		var request = {
				method: 'PUT',
				url: 'jelo/j',
				data:JSON.stringify(data)
		};
		
		$http(request)
		.then(function(response){
			console.log("Service: prikaziJela " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-prikaziJela " + response.data);
		})
		.finally();
		
	}
	function setDM(idjela){
		var today = new Date();
		var datumdm = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var data = {datumdm:datumdm,
				jelos: idjela
				
				
		}
		var request = {
				method: 'GET',
				url: 'dm/dmsave' ,
				params:data
			};
			return $http(request)
			.then(function(response){
				console.log("Service: setDM ");
				return response.data;
			});
		
	}
	function brisanjeJ(idjela){
		console.log("Service: brisanjeJ");
		
		var data = {		
				idJela: idjela
		}
		var request = {
				method: 'DELETE',
				url: 'jelo/jdel',
				params:data
		};
		
		return $http(request)
		.then(function(response){
			console.log("Service: prikaziAzuriranjeJ " + response.data);
			return response.data;
		});
	}
	
	return  {
		'saveJelo' : saveJelo,
		'getJela': getJela,
		'updateJelo':updateJelo,
		'deleteJelo':deleteJelo,
		'getJelo':getJelo,
		'prikaziDnevniM':prikaziDnevniM,
		'porucivanje':porucivanje,
		'kreirajStavku':kreirajStavku,
		'prikaziP':prikaziP,
		 'getOneDM':getOneDM,
		 'updateDnevniMeni':updateDnevniMeni,
		 'setDM':setDM,
		'brisanjeJ':brisanjeJ,
		prikaziJela:false,
		prikaziDnevniMeni:false,
		porucen:false,
		prikazanaP: false,
		saveJ:false,
		menjan:false,
		aDM:false,
		cdm:false,
		obrisan:false
	};
    	}]);