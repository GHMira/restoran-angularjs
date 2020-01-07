package restoran.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restoran.model.Dnevnimeni;
import restoran.model.Jelo;
import restoran.repository.DnevnimeniRepository;
import restoran.repository.JeloRepository;

@Service
public class JeloService {
	
	@Autowired
	private JeloRepository jeloRepository;
	@Autowired
	private DnevnimeniRepository dnevnimeniRepository;
	
	public JeloService() {
	}
	
	public Jelo save(Jelo jelo) {
		if(jelo.getIdJela()!=0 &&  jeloRepository.existsById(jelo.getIdJela())) {
			throw new EntityExistsException("Vec postoji jelo sa tim id  brojem u bazi");
		}
		return  jeloRepository.save(jelo);
	}
	
	public Jelo update(Jelo jelo) {
		if(jelo.getIdJela()==0 )
			throw new EntityExistsException("Jelo ne postoji u bazi. Nije moguca izmena.");
		return jeloRepository.save(jelo); 
	}
	
	public List<Jelo>findAll(){
		return jeloRepository.findAll();
	}
	
	public Jelo findOne(Integer idJela) {
		return jeloRepository.findById(idJela.intValue());
	}
	
	public void delete(Integer idJela) {
		jeloRepository.deleteById(idJela);
	}
	
	public List<Jelo> prikaziDnevniM(Date date) {
		Dnevnimeni dm = dnevnimeniRepository.findByDatumdm(date);
		return dm.getJelos();
	}
	
	
}
