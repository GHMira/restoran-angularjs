package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Jelo;

public interface JeloRepository extends JpaRepository<Jelo,Integer> {
	Jelo findByNazivj(String nazivJ);
	Jelo findById(int idjela);
}
