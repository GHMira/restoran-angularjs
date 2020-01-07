package restoran.rest;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Dnevnimeni;
import restoran.model.Jelo;
import restoran.service.DnevnimeniService;
import restoran.service.JeloService;

@RestController
@RequestMapping("/dm")
public class DnevnimeniResource {
	
	@Autowired
	private DnevnimeniService dnevnimeniService;
	
	@Autowired
	private JeloService jeloService;
	
	@RequestMapping(value="dm", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dnevnimeni>getAllJela(){
		return dnevnimeniService.findAllDM();
	}
	@RequestMapping(value="dmfindOne", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> findOneDM(@RequestParam(value="datumdm") String datumdm) throws ParseException{
		 Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(datumdm);  

		Dnevnimeni dm= dnevnimeniService.findByDatumdm(date1);
		if(dm != null) {
			return new ResponseEntity<Dnevnimeni>(dm, HttpStatus.OK);
		}
		return  new ResponseEntity<Dnevnimeni>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@RequestMapping(value="dmsave", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> createDnevnimeni(@RequestParam(value="datumdm") String datumdm, @RequestParam(value="jelos") List<Integer>jelos)throws URISyntaxException{
		try {
			Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(datumdm);
			Dnevnimeni dm=new Dnevnimeni();
			dm.setDatumdm(date1);
			Dnevnimeni result=dnevnimeniService.saveDM(dm);
			List<Jelo> l=null;
			for(Integer i:jelos) {
				Jelo j=jeloService.findOne(i);
				List<Dnevnimeni> ldm=j.getDnevnimenis();
				ldm.add(result);
				j.setDnevnimenis(ldm);
				jeloService.update(j);
				l=result.getJelos();
				if(l==null) {
					l=new ArrayList<Jelo>();
				}
				l.add(j);
			}
			
			result.setJelos(l);
			dnevnimeniService.updateDM(result);

			return new ResponseEntity<Dnevnimeni>(result, HttpStatus.OK);
			//return ResponseEntity.created(new URI("/api/dnevnimeni/"+result.getIddm())).body(result); 
		} catch (Exception e) {
			return new ResponseEntity<Dnevnimeni>(HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value="dmupdate", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dnevnimeni> updateDM(@RequestParam(value="iddm") int iddm, @RequestParam(value="datumdm") String datumdm)throws URISyntaxException{
		Dnevnimeni dnevnimeni = dnevnimeniService.findOneDM(iddm);
		 if(dnevnimeni == null) {
			 return new ResponseEntity<Dnevnimeni>(HttpStatus.NOT_FOUND);
		 }
		try {
			Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(datumdm);
			dnevnimeni.setDatumdm(date1);
			Dnevnimeni result=dnevnimeniService.updateDM(dnevnimeni);
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


