package restoran.rest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Dnevnimeni;
import restoran.service.DnevnimeniService;

@RestController
@RequestMapping("/dm")
public class DnevnimeniResource {
	
	@Autowired
	private DnevnimeniService dnevnimeniService;
	
	@RequestMapping(value="dm", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dnevnimeni>getAllJela(){
		return dnevnimeniService.findAllDM();
	}
	@RequestMapping(value="dmfindOne", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> findOneDM(@RequestParam Integer iddm){
		Optional<Dnevnimeni> dm= dnevnimeniService.findOneDM(iddm);
		if(dm != null) {
			return new ResponseEntity<Dnevnimeni>(HttpStatus.OK);
		}
		return  new ResponseEntity<Dnevnimeni>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@RequestMapping(value="dmsave", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> createDnevnimeni(@RequestBody Dnevnimeni dnevnimeni)throws URISyntaxException{
		try {
			Dnevnimeni result=dnevnimeniService.saveDM(dnevnimeni);
			return new ResponseEntity<Dnevnimeni>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/dnevnimeni/"+result.getIddm())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Dnevnimeni>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="dmupdate", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> updateDM(@RequestBody Dnevnimeni dnevnimeni)throws URISyntaxException{
		if(dnevnimeni.getIddm()==0) {
			return new ResponseEntity<Dnevnimeni>(HttpStatus.NOT_FOUND);
		}
		try {
			Dnevnimeni result=DnevnimeniService.updateDM(dnevnimeni);
			return new ResponseEntity<Dnevnimeni>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/dnevnimeni/"+result.getIddm())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Dnevnimeni>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="dmdel", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteDnevnimeni(@RequestParam Integer iddm){
		dnevnimeniService.deleteDM(iddm);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> removeJelo(@RequestParam("idJela") int idJela, @RequestParam("iddm") int iddm) {
		boolean response= dnevnimeniService.removeJelo(idJela, iddm);
		if(response) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}


