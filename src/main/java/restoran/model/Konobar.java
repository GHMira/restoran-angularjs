package restoran.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the konobar database table.
 * 
 */
@Entity
@NamedQuery(name="Konobar.findAll", query="SELECT k FROM Konobar k")
public class Konobar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idkonobara", unique = true, nullable = false)
	private int idkonobara;

	private String pass;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	//bi-directional many-to-one association to Porudzbina
	@JsonIgnore
	@OneToMany(mappedBy="konobar", cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
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