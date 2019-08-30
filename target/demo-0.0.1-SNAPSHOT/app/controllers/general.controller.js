angular.module("crudApp").controller("GeneralController",GeneralController) ;
GeneralController.inject=['$scope', jeloServis,'konobarServis'];
function GeneralController($scope, jeloServis, konobarServis){
	$scope.jelo={};
	$scope.buttonText="Submit";
	
	var jelo = {
			idJela:1,
			nazivJ: 'a',
			cenaJ: 1,
			kategorijaJ: 'a',
			kolicinaJ: 5
	}
	
	jeloServis.getJela();
	jeloServis.save(jelo);
	jeloServis.updateJelo(jelo);
//	jeloServis.deleteJelo(1);
	jeloServis.getJelo(1);
	$scope.saveJelo=function(){
		if($scope.jelo.nazivJ){
			jeloServis.save($scope.jeloNazivJ);
		}
	}
	
	$scope.updateJeloInit=function(jelo){
		$scope.buttonText="Update";
		$scope.jelo=jelo;
	}
	
//	$scope.deleteJelo=function(jelo){
//		restoran.$delete({idJela:jelo.idJela},function(){
//			$scope.jelo=jeloServis.query();
//			
//		});
//			
//	}

	$scope.buttonText="Submit";
	$scope.konobar={
			idkonobara:1,
			username:'a',
			pass:'a'
			
	}
	konobarServis.logovanje($scope.konobar);
	konobarServis.saveKonobar($scope.konobar);
	konobarServis.getKonobari();
	$scope.konobar={
		idkonobara:1,
		username:'b',
		pass:'a'
	}
	konobarServis.updateKonobar($scope.konobar);
	konobarServis.deleteKonobar('1');
	konobarServis.naplata('15');
	
}


