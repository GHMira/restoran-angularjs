package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina,Integer> {
	Porudzbina findById(int idporudzbine);
}
