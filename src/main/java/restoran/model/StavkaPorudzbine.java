package restoran.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stavkaporudzbine database table.
 * 
 */
@Entity
@NamedQuery(name="Stavkaporudzbine.findAll", query="SELECT s FROM StavkaPorudzbine s")
public class StavkaPorudzbine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idstavke;

	//bi-directional many-to-one association to Porudzbina
	@ManyToOne
	@JoinColumn(name="IDPORUDZBINE")
	private Porudzbina porudzbina;

	//bi-directional many-to-one association to Jelo
	@ManyToOne
	@JoinColumn(name="IDJELA")
	private Jelo jelo;

	public StavkaPorudzbine() {
	}

	public int getIdstavke() {
		return this.idstavke;
	}

	public void setIdstavke(int idstavke) {
		this.idstavke = idstavke;
	}

	public Porudzbina getPorudzbina() {
		return this.porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Jelo getJelo() {
		return this.jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

}