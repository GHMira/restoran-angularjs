package restoran.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import restoran.model.Jelo;
import restoran.model.Konobar;
import restoran.model.Porudzbina;
import restoran.model.StavkaPorudzbine;
import restoran.repository.KonobarRepository;
import restoran.repository.PorudzbinaRepository;
import restoran.repository.StavkaPorudzbineRepository;
@Service
public class KonobarService {
	@Autowired
	private KonobarRepository konobarRepository;
	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public KonobarService() {
	}

	public Konobar login(String username, String password) {
		Konobar res = konobarRepository.findByUsername(username);
		if (res != null ) {
				if(passwordEncoder.matches(password, res.getPass())) {
					return res;
				}
		}
		return null;
	}

	public List<Konobar> findAll() {
		return konobarRepository.findAll();
	}
	
	public Konobar findKonobar(int id) {
		return konobarRepository.findById(id);
	}

	public Konobar save(Konobar konobar) {
		if (konobar.getIdkonobara() != 0 && konobarRepository.existsById(konobar.getIdkonobara())) {
			throw new EntityExistsException("Vec postoji jelo sa tim id  brojem u bazi");
		}
		return konobarRepository.save(konobar);
	}

	public void delete(Integer idkonobara) {
		konobarRepository.deleteById(idkonobara);

	}
	public Konobar update(Konobar konobar) {
		if (konobar.getIdkonobara() == 0)
			throw new EntityExistsException("Konobar ne postoji u bazi. Nije moguca izmena.");
		return konobarRepository.save(konobar);
	}


	public Konobar prikaziAzuriranje(Konobar konobar) {
		if (konobar.getIdkonobara() == 0)
			throw new EntityExistsException("Konobar ne postoji u bazi. Nije moguca izmena.");
		return konobarRepository.save(konobar);
	}
	public Konobar brisanjeK(Konobar konobar) {
		if (konobar.getIdkonobara() == 0)
			throw new EntityExistsException("Konobar ne postoji u bazi. Nije moguca izmena.");
		return konobarRepository.save(konobar);
	}

	public boolean naplataRa(int idporudzbine) {
		Porudzbina result = porudzbinaRepository.findById(idporudzbine);
		List<StavkaPorudzbine> sp = result.getStavkaporudzbines();
		
		if( sp ==  null || sp.isEmpty()) {
			return false;
		}
		
		double iznos = 0;
		for (StavkaPorudzbine stav : sp) {
			Jelo jelo = stav.getJelo();
			iznos += jelo.getCenaj().doubleValue();
		}
		result.setIznos(BigDecimal.valueOf(iznos));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date datumnaplate = new Date();
		result.setDatumnaplate(datumnaplate);
		porudzbinaRepository.save(result);
		return true;
	}

}
