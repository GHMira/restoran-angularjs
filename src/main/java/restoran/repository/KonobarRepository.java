package restoran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Konobar;

public interface KonobarRepository extends JpaRepository<Konobar,Integer> {
	Konobar findById(int idkonobara); 
	List<Konobar> findByUsernameAndPass(String username, String password);
	Konobar findByUsername(String username);

}
