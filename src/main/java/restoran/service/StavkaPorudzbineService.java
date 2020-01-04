package restoran.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restoran.model.Porudzbina;
import restoran.model.StavkaPorudzbine;
import restoran.repository.StavkaPorudzbineRepository;
@Service
public class StavkaPorudzbineService {
	@Autowired
	private StavkaPorudzbineRepository stavkaporudzbineRepository;
	
	
	public StavkaPorudzbineService() {
	}
	public List<StavkaPorudzbine> findAll() {
		return stavkaporudzbineRepository.findAll();
	}

	public StavkaPorudzbine save(StavkaPorudzbine stavkaporudzbine) {
		if(stavkaporudzbine.getIdstavke()!=0 &&  stavkaporudzbineRepository.existsById(stavkaporudzbine.getIdstavke())) {
			throw new EntityExistsException("Vec postoji stavka sa tim id  brojem u bazi");
		}
		return  stavkaporudzbineRepository.save(stavkaporudzbine);
	}

	public StavkaPorudzbine update(StavkaPorudzbine stavkaporudzbine) {
		if(stavkaporudzbine.getIdstavke()==0 )
			throw new EntityExistsException("Stavka ne postoji u bazi. Nije moguca izmena.");
		return stavkaporudzbineRepository.save(stavkaporudzbine); 
	}

	public void delete(Integer idstavke) {
		stavkaporudzbineRepository.deleteById(idstavke);
		
	}
	public List<StavkaPorudzbine> getStavkeP(Porudzbina porudzbina) {
		return stavkaporudzbineRepository.findByPorudzbina(porudzbina);
	}

}
