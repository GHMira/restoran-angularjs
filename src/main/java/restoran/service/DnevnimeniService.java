package restoran.service;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restoran.model.Dnevnimeni;
import restoran.model.Jelo;
import restoran.repository.DnevnimeniRepository;
@Service
public class DnevnimeniService {
	@Autowired
	private DnevnimeniRepository dnevnimeniRepository;
	
	
	public DnevnimeniService() {
	}
	public List<Dnevnimeni> findAllDM() {
		return dnevnimeniRepository.findAll();
		
	}
	public Dnevnimeni findOneDM(int iddm) {
		return dnevnimeniRepository.findById(iddm);
		
	}
	public Dnevnimeni  findByDatumdm(Date datumdm) {
		return dnevnimeniRepository.findByDatumdm(datumdm);
		
	}

	public Dnevnimeni saveDM(Dnevnimeni dnevnimeni) {
		if(dnevnimeni.getIddm()!=0 &&  dnevnimeniRepository.existsById(dnevnimeni.getIddm())) {
			throw new EntityExistsException("Vec postoji jelo sa tim id  brojem u bazi");
		}
		return  dnevnimeniRepository.save(dnevnimeni);
		
	}

	public Dnevnimeni updateDM(Dnevnimeni dnevnimeni) {
		if(dnevnimeni.getIddm()==0) {
			throw new EntityExistsException(" Nesto je poslo naopako");
		}
		return dnevnimeniRepository.save(dnevnimeni);
	}

	public void deleteDM(Integer iddm) {
		dnevnimeniRepository.deleteById(iddm);
	}
	
	public boolean removeJelo(int idJela, int iddm) {
		Dnevnimeni dm = dnevnimeniRepository.getOne(iddm);
		List<Jelo> jela = dm.getJelos();
		ListIterator<Jelo> iter = jela.listIterator();
		while(iter.hasNext()){
		    if(iter.next().getIdjela() == idJela) {
		        iter.remove();
		    }
		}
		
		dm.setJelos(jela);
		Dnevnimeni result = updateDM(dm);
		
		if( result == null) {
			return false;
		}
		
		return true;
	}

}
