package restoran.rest;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.model.Porudzbina;
import restoran.model.StavkaPorudzbine;
import restoran.service.JeloService;
import restoran.service.PorudzbinaService;
import restoran.service.StavkaPorudzbineService;

@RestController
@RequestMapping("/sp")
public class StavkaPorudzbineResource {
	
	@Autowired
	private StavkaPorudzbineService stavkaporudzbineService;
	
	@Autowired
	private PorudzbinaService porudzbinaService;
	
	@Autowired
	private JeloService jeloService;
	
	@RequestMapping(value="spor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StavkaPorudzbine>getAllJela(){
		return stavkaporudzbineService.findAll();
	}
	@RequestMapping(value="spp/{idPorudzbine}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StavkaPorudzbine> getStavkeP(@PathVariable("idPorudzbine") int idPorudzbine)throws URISyntaxException{
		try {
			// pronadji stavke koje imaju prosledjenu vrednost kao id porudzbine
			Porudzbina porudzbina=porudzbinaService.findOne(idPorudzbine);
			List<StavkaPorudzbine> stavke=stavkaporudzbineService.getStavkeP(porudzbina);
			return stavke;
			
		}catch(Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="spsave/{idPorudzbine}/{idJela}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaPorudzbine> createJelo(@PathVariable("idPorudzbine") int idPorudzbine, @PathVariable("idJela") int idJela)throws URISyntaxException{
		try {
			StavkaPorudzbine stavkaporudzbine = new StavkaPorudzbine();
			Porudzbina p = porudzbinaService.findOne(idPorudzbine);
			Jelo j = jeloService.findOne(idJela);
			stavkaporudzbine.setJelo(j);
			stavkaporudzbine.setPorudzbina(p);
			
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


