package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Dnevnimeni;
import restoran.model.Jelo;

public interface DnevnimeniRepository extends JpaRepository<Dnevnimeni,Integer> {
	Dnevnimeni findById(int iddm);
}
