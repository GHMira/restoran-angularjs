package restoran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Porudzbina;
import restoran.model.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine,Integer> {
	StavkaPorudzbine findById(int idstavke);
	List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina);
}
