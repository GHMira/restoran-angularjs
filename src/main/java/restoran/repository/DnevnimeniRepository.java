package restoran.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Dnevnimeni;

public interface DnevnimeniRepository extends JpaRepository<Dnevnimeni,Integer> {
	Dnevnimeni findById(int iddm);
	Dnevnimeni findByDatumdm(Date date);

}
