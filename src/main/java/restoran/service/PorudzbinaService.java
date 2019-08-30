package restoran.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restoran.model.Porudzbina;
import restoran.repository.PorudzbinaRepository;
@Service
public class PorudzbinaService {
	
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	public PorudzbinaService() {
	}
	public List<Porudzbina> findAll() {
		return porudzbinaRepository.findAll();
	}

	public Porudzbina save(Porudzbina porudzbina) {
		if(porudzbina.getIdporudzbine()!=0 &&  porudzbinaRepository.existsById(porudzbina.getIdporudzbine())) {
			throw new EntityExistsException("Vec postoji porudzbina sa tim id  brojem u bazi");
		}
		return  porudzbinaRepository.save(porudzbina);
	}

	public void delete(Integer idporudzbine) {
		porudzbinaRepository.deleteById(idporudzbine);
		
	}

	public Porudzbina update(Porudzbina porudzbina) {
		if(porudzbina.getIdporudzbine()==0 )
			throw new EntityExistsException("Porudzbina ne postoji u bazi. Nije moguca izmena.");
		return porudzbinaRepository.save(porudzbina); 
	}
	public Porudzbina findOne(Integer idporudzbine) {
		Optional<Porudzbina> op = porudzbinaRepository.findById(idporudzbine);
		Porudzbina p = op.get();
		if( p == null) {
			return null;
		}
		return p;
		
		
	}

}
