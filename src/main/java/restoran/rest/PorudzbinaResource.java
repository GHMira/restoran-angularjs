package restoran.rest;
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

import restoran.model.Porudzbina;
import restoran.service.PorudzbinaService;

@RestController
@RequestMapping("/porudzbina")
public class PorudzbinaResource {
	
	@Autowired
	private PorudzbinaService porudzbinaService;
	
	@RequestMapping(value="p", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Porudzbina>getAllPorudzbine(){
		return porudzbinaService.findAll();
	}
	
	@RequestMapping(value="psave", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Porudzbina> createPorudzbina(@RequestBody Porudzbina porudzbina)throws URISyntaxException{
		try {
			Porudzbina result=porudzbinaService.save(porudzbina);
			return new ResponseEntity<Porudzbina>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/porudzbina/"+result.getIdporudzbine())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Porudzbina>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="pupdate", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Porudzbina> updatePorudzbina(@RequestBody Porudzbina porudzbina)throws URISyntaxException{
		if(porudzbina.getIdporudzbine()==0) {
			return new ResponseEntity<Porudzbina>(HttpStatus.NOT_FOUND);
		}
		try {
			Porudzbina result=porudzbinaService.update(porudzbina);
			return new ResponseEntity<Porudzbina>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/porudzbina/"+result.getIdporudzbine())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Porudzbina>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="pdel", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePorudzbina(@RequestParam Integer idporudzbine){
		porudzbinaService.delete(idporudzbine);
		return ResponseEntity.ok().build();
	}
	@RequestMapping(value="pfone", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Porudzbina> findOne(@RequestParam Integer idporudzbine){
		porudzbinaService.findOne(idporudzbine);
		return ResponseEntity.ok().build();
		
	}
	
}


