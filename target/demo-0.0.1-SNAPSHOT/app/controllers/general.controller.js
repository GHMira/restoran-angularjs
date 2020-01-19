angular.module("crudApp").controller("GeneralController",GeneralController) ;
GeneralController.inject=['$scope', 'jeloServis','konobarServis'];

function GeneralController($scope, jeloServis, konobarServis){
	$scope.jelo={};
	$scope.buttonText="Submit";
	
	$scope.test=["jedan", "dva"]
	
	$scope.konobarServis = konobarServis;
	$scope.jeloServis = jeloServis;
	$scope.iznos = 0;
	
	
	
	console.log($scope.konobarServis.ulogovan);
	
	$scope.findJelo=function findJelo(){
		var idjela = $('*[placeholder="idjela"]').val();
		var kategorijaj = $('*[placeholder="kategorijaj"]').val();
		var nazivj = $('*[placeholder="nazivj"]').val();
		var cenaj = $('*[placeholder="cenaj"]').val();
		var kolicinaj = $('*[placeholder="kolicinaj"]').val();
		if($scope.jelo.nazivJ){
			jeloServis.get($scope.jeloNazivJ);
		$http(request)
		.then(function(response){
			console.log("Service: findJelo " + response.data);
		})
		.catch(function(response){
			console.log("Error: Service-findJelo " + response.data);
		})
		.finally();
		
	}
	}
	$scope.updateJ=function updateJ(){
		var idjela = $('*[name="IdAz"]').val();
		var nazivj = $('*[name="NoviN"]').val();
		var kategorijaj = $('*[name="NovaK"]').val();
		var cenaj = $('*[name="NovaC"]').val();
		var kolicinaj = $('*[name="NovaKo"]').val();
		var data = {
				idjela:idjela,
				kategorijaj:kategorijaj,
				nazivj:nazivj,
				cenaj:cenaj,
				kolicinaj:kolicinaj
		}
		var request = {
				method: 'POST',
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
	
	$scope.findAll = function findAll() {
		var datumdm = $('*[placeholder="YYYY-MM-DD"]').val();
		
		if(datumdm ) {
			
			$scope.jeloServis.prikaziDnevniM(datumdm).then(function(value) {
					$scope.jela = value;
				  console.log(value);
			});
		} else {
			// alert("Unesite ispravan datum!");
		}
	}
	
	$scope.findOneDM = function findOneDM() {
		var datumdm = $('*[placeholder="YYYY-MM-DD"]').val();
		
		if(datumdm ) {
			
			$scope.jeloServis.getOneDM(datumdm).then(function(value) {
					$scope.dm = value;
				  console.log(value);
			});
		} else {
			// alert("Unesite ispravan datum!");
		}
	}
	
	$scope.findJelaDM = function findJelaDM() {
		var datumdm = $('*[placeholder="YYYY-MM-DD"]').val();
		
		if(datumdm ) {
			$scope.jeloServis.prikaziDnevniM(datumdm).then(function(value) {
				  console.log(value);
			});
		} else {
			// alert("Unesite ispravan datum!");
		}
	}
		  // HTTP DELETE- delete country by Id
        $scope.deleteKonobar = function (k) {
        	var idkonobara = $('*[name="BrisanjeK"]').val();
    		$scope.konobarServis.brisanjeK(idkonobara).then(function(value) {
    			
    		});
        }

        /* Private Methods */
        // HTTP GET- get all countries collection
        function _refreshKonobarData() {
            $http({
                method : 'GET',
                url : 'http://localhost:8080/rest/konobar'
            }).then(function successCallback(response) {
                $scope.k = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }
        function _success(response) {
            _refreshyData();
            _clearFormData()
        }
 
        function _error(response) {
            console.log(response.statusText);
        }
 


	if(!$scope.konobarServis.ulogovan) {
		console.log("nije ulogovan");
		$(".list-group-item").each(function(i) {
			console.log("each list-group-item");
			$(this).addClass('disabled');
		});
	}
	
	$scope.update=function update(){
		var username = $('*[name="NoviUser"]').val();
		var password = $('*[name="NoviPass"]').val();
		var id = $('*[name="IdAzur"]').val();
		var data = {
				idkonobara:id,
				pass:password,
				username:username
		}
		var request = {
				method: 'POST',
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
	$scope.updateDM=function updateDM(){
		$scope.jeloServis.updateDnevniMeni($scope.dm.iddm).then(function(value) {
		});
	}
	$scope.brisanjeJl=function brisanjeJl(){
		var idjela = $('*[name="bri"]').val();
		$scope.jeloServis.brisanjeJ(idjela).then(function(value) {
			
		});
	}
	$scope.setDM=function setDM(){
		$scope.jela=[];
		var valueList = [];
		$('#createdm1').each(function() {
		    $(this).find("input[type=checkbox]:checked").each(function() {
		    	var values = [];
		    	$(this).closest("td").siblings("td").each(function() {
		    		values.push($(this).text());
		    	});
		    	valueList.push(values.join(", "));
		    });
		});
		for(var i = 0; i < valueList.length; i++){
			
			$scope.jela.push(valueList[i].split(',')[0]);
		}
		
		$scope.jeloServis.setDM($scope.jela).then(function(value){
			
		});
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
	}
	
	$scope.myfunc = function myfunc() {
		var valueList = [];
		$('#poruci1').each(function() {
		    $(this).find("input[name='check']:checked").each(function() {
		    	var values = [];
		    	$(this).closest("td").siblings("td").each(function() {
		    		values.push($(this).text());
		    	});
		    	valueList.push(values.join(", "));
		    });
		});
		console.log("(" + valueList.join("),(") + ")");
		var idKonobara = $("input[name='idkonobar']").val();
		console.log("ID konobara: " + idKonobara);
		$scope.jeloServis.porucivanje(idKonobara).then(function(value) {
			  console.log(value);
			  $scope.idPorudzbine = value.idporudzbine;
			  for(var i = 0; i < valueList.length; i++){
				  var idJela = valueList[i].split(',')[0];
				  $scope.jeloServis.kreirajStavku($scope.idPorudzbine, idJela).then(function(value) {
				  
				  });
			  }
		});
	}
	
	$scope.myfuncIznos=function myfuncIznos(){
		$scope.iznos=0;
		for(var i=0;i<$scope.jela.length;i++){
			console.log($scope.jela[i].cenaj);
			$scope.iznos += $scope.jela[i].cenaj;
		}
	}
	
	
	/**
	 * Ako $scope.idPoruzbine ima neku vrednost onda se ova metoda koristi za prikazivanje
	 * prethodno kreirane porudzbine (dugme sa strane u aplikaciji);
	 * Ako je $scope.idPorudzbine undefined onda se ova metoda koristi za prikaz stavki porudzbine za naplatu 
	 * */
	$scope.prikaziStavke=function prikaziStavke(){
		$scope.jela= [];
		if($scope.idPorudzbine){
			$scope.jeloServis.prikaziP($scope.idPorudzbine).then(function(value){
				//value.jelo
				for (var i = 0; i < value.length; i++){
					$scope.jela.push(value[i].jelo);
				}
				console.log(value);
				$scope.prikaziP();
	
			});
			$scope.idPorudzbine = undefined;
		} else {
			var valueList = [];
			$('#porudzbina1').each(function() {
			    $(this).find("input[type=radio]:checked").each(function() {
			    	var values = [];
			    	$(this).closest("td").siblings("td").each(function() {
			    		values.push($(this).text());
			    	});
			    	valueList.push(values.join(", "));
			    });
			});
			for(var i = 0; i < valueList.length; i++){
				$scope.jela=[];
				$scope.idPorudzbine = valueList[i].split(',')[0];
				$scope.jeloServis.prikaziP($scope.idPorudzbine).then(function(value){
					//value.jelo
					console.log(value);

					for (var i = 0; i < value.length; i++){
						$scope.jela.push(value[i].jelo);
					}
					$scope.prikaziP();
					$scope.konobarServis.naplacen = true;
				});
				
			}
		}
	}
	
	
	$scope.porudzbine=[];
	$scope.getP=function getP(){
		$scope.konobarServis.getAllP().then(function(value){
			$scope.porudzbine=value;
		});
		$scope.naplati();
	}
	
	$scope.prikaziAzuriranje=function prikaziAzuriranje(){
		$scope.konobari = [];
		$scope.konobarServis.getKonobari().then(function(value) {
			  $scope.konobari = value;
		});
		$scope.konobarServis.prikazanA = true;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.podKon = true;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.prijavljivanje=function prijavljivanje(){
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = true;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.registrovanje=function registrovanje(){
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = true;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.prikaziP=function prikaziP(){
		$scope.jeloServis.prikazanaP = true;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.naplati=function naplati(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.porudzbine=[];
		$scope.konobarServis.getAllP().then(function(value){
			$scope.porudzbine=value;
		});
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=true;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	
	$scope.prikaziJela=function prikaziJela(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=true;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	
	$scope.prikaziDnevniMeni=function prikaziDnevniMeni(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=true;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	
	$scope.porucivanje=function porucivanje(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.jeloServis.porucen=true;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.brisanjeK=function brisanjeK(){
		$scope.konobari = [];
		$scope.konobarServis.getKonobari().then(function(value) {
			  $scope.konobari = value;
		});
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=true;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = true;
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.saveJelo=function saveJelo(){
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=true;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.menjanjeJ=function menjanjeJ(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		})
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=true;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.aDM=function aDM(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		})
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=true;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=false;
	}
	$scope.cDM=function cDM(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=true;
		$scope.jeloServis.obrisan=false;
	}
	$scope.brisanjeJ=function brisanjeJ(){
		$scope.jela = [];
		$scope.jeloServis.getJela().then(function(value) {
			  $scope.jela = value;// smestanje jela vracenih iz baze u niz
									// $scope.jela
		});
		$scope.jeloServis.prikazanaP = false;
		$scope.konobarServis.ulogovan = true;
		$scope.jeloServis.porucen=false;
		$scope.jeloServis.prikaziDnevniMeni=false;
		$scope.jeloServis.prikaziJela=false;
		$scope.konobarServis.brisan=false;
		$scope.konobarServis.prikazanA = false;
		$scope.konobarServis.podKon = false;
		$scope.konobarServis.naplacen=false;
		$scope.jeloServis.saveJ=false;
		$scope.jeloServis.menjan=false;
		$scope.jeloServis.aDM=false;
		$scope.jeloServis.cdm=false;
		$scope.jeloServis.obrisan=true;
	}
	$scope.prijava = function prijava() {
		var username = $('*[placeholder="username"]').val();
		var password = $('*[placeholder="password"]').val();

		if(username && password) {
			var httpParams = {
					username:username,
					password:password,
			}
				
			var request = {
					method: 'GET',
					url: 'konobar/klog',
					params:httpParams
			}
				

			$http(request)
			.then(function(response){
				$scope.konobarServis.ulogovan = true;
				$(".list-group-item").each(function(i) {
					$(this).removeClass('disabled');
				});
				$scope.prijavljivanje();
			}).catch(function(){
				alert("Pogresan username i password!");
			})
					
			
		} else {
			alert("Unesite username i password!");
		}
		
	}
	
	$scope.registrovanje = function registrovanje() {
		var username = $('*[placeholder="username"]').val();
		var password = $('*[placeholder="password"]').val();

		if(username && password) {
			var data = {
					username:username,
					pass:password
			}
			
			var request = {
				method: 'POST',
				url: 'konobar/ksave',
				data: JSON.stringify(data)
			};
			
			$http(request)
			.then(function(response) {
				$scope.konobarServis.ulogovan = true;
				$(".list-group-item").each(function(i) {
					$(this).removeClass('disabled');
				});
			})
			.catch(function(response) {
				alert("Greska!");
			})
			.finally();
		} else {
			alert("Unesite username i password!");
		}
	}
	
	$scope.upisNJ = function upisNJ() {
		//var idjela = $('*[placeholder="idjela"]').val();
		var nazivj = $('*[name="nazivj"]').val();
		var kategorijaj = $('*[name="kategorijaj"]').val();
		var cenaj = $('*[name="cenaj"]').val();
		var kolicinaj = $('*[name="kolicinaj"]').val();

		//if(nazivj && kategorijaj && cenaj && kolicinaj) {
			var data = {
					//idjela:idjela,
					kategorijaj:kategorijaj,
					nazivj:nazivj,
					cenaj:cenaj,
					kolicinaj:kolicinaj
			}
			
			var request = {
				method: 'POST',
				url: 'jelo/jsave',
				data: JSON.stringify(data)
			};
			
			$http(request)
			.then(function(response) {
				$scope.jeloServis.saveJ=true;;
				$(".list-group-item").each(function(i) {
					$(this).removeClass('disabled');
				});
			})
			.catch(function(response) {
				alert("Greska!");
			})
			.finally();
		//} else {
			//alert("Unesite username i password!");
		//}
	}
	
// var promise1 = new Promise(function(resolve, reject) {
// $scope.konobarServis.getKonobari();
// });
	
	$scope.konobari = [];
	$scope.konobarServis.getKonobari().then(function(value) {
		  $scope.konobari = value;
	});
	

	// preuzimanje svih jela koja se nalaze u tabeli Jelo
	$scope.jela = [];
	$scope.jeloServis.getJela().then(function(value) {
		  $scope.jela = value;// smestanje jela vracenih iz baze u niz
								// $scope.jela
	});

		
	
	var jelo = {
			idJela:1,
			nazivJ: 'a',
			cenaJ: 1,
			kategorijaJ: 'a',
			kolicinaJ: 5
	}
	
	/*
	 * jeloServis.getJela(); jeloServis.save(jelo); jeloServis.updateJelo(jelo);
	 * jeloServis.deleteJelo(1);
	 */
	jeloServis.getJelo(1);
	$scope.getJelo=function(){
		if($scope.jelo.nazivJ){
			jeloServis.get($scope.jeloNazivJ);
		}
	}
	
	/*
	 * $scope.saveJelo=function(){ if($scope.jelo.nazivJ){
	 * jeloServis.save($scope.jeloNazivJ); } }
	 * 
	 * $scope.updateJeloInit=function(jelo){ $scope.buttonText="Update";
	 * $scope.jelo=jelo; }
	 * 
	 * $scope.deleteJelo=function(jelo){
	 * restoran.$delete({idJela:jelo.idJela},function(){
	 * $scope.jelo=jeloServis.query();
	 * 
	 * });
	 *  }
	 * 
	 * $scope.buttonText="Submit"; $scope.konobar={ idkonobara:3,
	 * username:'user3', pass:'pass3'
	 *  } konobarServis.saveKonobar($scope.konobar);
	 * konobarServis.getKonobari(); $scope.konobar={ idkonobara:2,
	 * username:'user2', pass:'pass3' }
	 * konobarServis.updateKonobar($scope.konobar);
	 * konobarServis.deleteKonobar('1'); konobarServis.naplata('15');
	 */
// }
}




