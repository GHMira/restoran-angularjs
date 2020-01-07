package restoran.rest;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import restoran.model.Jelo;
import restoran.model.Konobar;
import restoran.service.JeloService;

@RestController
@RequestMapping("/jelo")
public class JeloResource {
	
	@Autowired
	private JeloService jeloService;
	
	@GetMapping(value="j")
	public List<Jelo>getAllJela(){
		List<Jelo> result = jeloService.findAll();
		return result;
	}
	
	@RequestMapping(value="jsave", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Jelo> createJelo(@RequestBody Jelo jelo)throws URISyntaxException{
		try {
			Jelo result=jeloService.save(jelo);
			return new ResponseEntity<Jelo>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/jelo/"+result.getIdJela())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Jelo>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="jupdate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Jelo> updateJelo(@RequestBody Jelo jelo)throws URISyntaxException{
		if(jelo.getIdJela()==0) {
			return new ResponseEntity<Jelo>(HttpStatus.NOT_FOUND);
		}
		try {
			Jelo result=jeloService.update(jelo);
			return new ResponseEntity<Jelo>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/jelo/"+result.getIdJela())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Jelo>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value="jdel", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteJelo(@RequestParam("idJela") String idJela){
		jeloService.delete(Integer.parseInt(idJela));
		return ResponseEntity.ok().build();
	}
	@RequestMapping(value="jfindO", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Jelo> findOneJ(@RequestParam Integer idJela){
		jeloService.findOne(idJela);
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(value="jdm", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Jelo>prikaziDnevniM(@RequestParam(value="datumdm") String datumdm)throws ParseException{
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(datumdm);

		List<Jelo> listaJelaNaDnevnomM = jeloService.prikaziDnevniM(date1);
		return listaJelaNaDnevnomM;
	}

	
	
}
