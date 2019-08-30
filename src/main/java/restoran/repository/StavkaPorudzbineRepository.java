package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine,Integer> {
	StavkaPorudzbine findById(int idstavke);
}
