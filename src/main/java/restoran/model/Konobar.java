package restoran.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the konobar database table.
 * 
 */
@Entity
@NamedQuery(name="Konobar.findAll", query="SELECT k FROM Konobar k")
public class Konobar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idkonobara;

	private String pass;

	private String username;

	//bi-directional many-to-one association to Porudzbina
	@OneToMany(mappedBy="konobar")
	private List<Porudzbina> porudzbinas;

	public Konobar() {
	}

	public int getIdkonobara() {
		return this.idkonobara;
	}

	public void setIdkonobara(int idkonobara) {
		this.idkonobara = idkonobara;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Porudzbina> getPorudzbinas() {
		return this.porudzbinas;
	}

	public void setPorudzbinas(List<Porudzbina> porudzbinas) {
		this.porudzbinas = porudzbinas;
	}

	public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().add(porudzbina);
		porudzbina.setKonobar(this);

		return porudzbina;
	}

	public Porudzbina removePorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().remove(porudzbina);
		porudzbina.setKonobar(null);

		return porudzbina;
	}

}