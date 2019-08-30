package restoran.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.model.Konobar;
import restoran.model.StavkaPorudzbine;
import restoran.service.StavkaPorudzbineService;

@RestController
@RequestMapping("/sp")
public class StavkaPorudzbineResource {
	
	@Autowired
	private StavkaPorudzbineService stavkaporudzbineService;
	
	@RequestMapping(value="spor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StavkaPorudzbine>getAllJela(){
		return stavkaporudzbineService.findAll();
	}
	
	@RequestMapping(value="spsave", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaPorudzbine> createJelo(@RequestBody StavkaPorudzbine stavkaporudzbine)throws URISyntaxException{
		try {
			StavkaPorudzbine result=stavkaporudzbineService.save(stavkaporudzbine);
			return new ResponseEntity<StavkaPorudzbine>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/stavkaporudzbine/"+result.getIdstavke())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="spupdate", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaPorudzbine> updateStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaporudzbine)throws URISyntaxException{
		if(stavkaporudzbine.getIdstavke()==0) {
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.NOT_FOUND);
		}
		try {
			StavkaPorudzbine result=stavkaporudzbineService.update(stavkaporudzbine);
			return new ResponseEntity<StavkaPorudzbine>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/stavkaporudzbine/"+result.getIdstavke())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<StavkaPorudzbine>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="spdel", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteStavkaPorudzbine(@RequestParam Integer idstavke){
		stavkaporudzbineService.delete(idstavke);
		return ResponseEntity.ok().build();
	}
	
}


