package restoran.rest;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Konobar;
import restoran.service.KonobarService;

@RestController
@RequestMapping("/konobar")
public class KonobarResource {
	
	@Autowired
	private KonobarService konobarService;
	
	@GetMapping("k")
	public List<Konobar>getAllKonobari(){
		List<Konobar> result = konobarService.findAll();
		return result;
	}

	@RequestMapping(value="klog", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konobar> login(@RequestParam("username") String username,@RequestParam("password") String password )throws URISyntaxException{
		Konobar result= konobarService.login(username, password);
		if( result != null) {
			return new ResponseEntity<Konobar>(HttpStatus.OK);
		}
		return new ResponseEntity<Konobar>(HttpStatus.NOT_FOUND);
	}
	

	@RequestMapping(value="ksave", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konobar> createKonobar(@RequestBody Konobar konobar)throws URISyntaxException{
		try {
			Konobar result=konobarService.save(konobar);
			if(result != null) {
				return new ResponseEntity<Konobar>(HttpStatus.OK);
			}
			return new ResponseEntity<Konobar>(HttpStatus.NOT_FOUND);
//			return ResponseEntity.created(new URI("/api/konobar/"+result.getIdkonobara())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Konobar>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="kupdate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konobar> updateKonobar(@RequestBody Konobar konobar)throws URISyntaxException{
		if(konobar.getIdkonobara()==0) {
			return new ResponseEntity<Konobar>(HttpStatus.NOT_FOUND);
		}
		try {
			Konobar result=konobarService.update(konobar);
			if( result != null) {
				return new ResponseEntity<Konobar>(HttpStatus.OK);
			}
			return new ResponseEntity<Konobar>(HttpStatus.UNAUTHORIZED);

//			return ResponseEntity.created(new URI("/konobar/konobar/"+result.getIdkonobara())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Konobar>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="kazuriranje", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konobar> prikaziAzuriranjeK(@RequestBody Konobar konobar)throws URISyntaxException{
		if(konobar.getIdkonobara()==0) {
			return new ResponseEntity<Konobar>(HttpStatus.NOT_FOUND);
		}
		try {
			Konobar result=konobarService.prikaziAzuriranje(konobar);
			if( result != null) {
				return new ResponseEntity<Konobar>(HttpStatus.OK);
			}
			return new ResponseEntity<Konobar>(HttpStatus.UNAUTHORIZED);

//			return ResponseEntity.created(new URI("/konobar/konobar/"+result.getIdkonobara())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Konobar>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="kbrisanje", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konobar> brisanjeK(@RequestBody Konobar konobar)throws URISyntaxException{
		if(konobar.getIdkonobara()==0) {
			return new ResponseEntity<Konobar>(HttpStatus.NOT_FOUND);
		}
		try {
			Konobar result=konobarService.brisanjeK(konobar);
			if( result != null) {
				return new ResponseEntity<Konobar>(HttpStatus.OK);
			}
			return new ResponseEntity<Konobar>(HttpStatus.UNAUTHORIZED);

//			return ResponseEntity.created(new URI("/konobar/konobar/"+result.getIdkonobara())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Konobar>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="kdel", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteKonobar(@RequestParam Integer idkonobara){
		konobarService.delete(idkonobara);
		return ResponseEntity.ok().build();
	}
	@RequestMapping(value="knaplata", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> naplataRacuna(@RequestParam("idPorudzbine") int idPorudzbine) {
		konobarService.naplataRa(idPorudzbine);
		return ResponseEntity.ok().build();
		
	}
	
}




