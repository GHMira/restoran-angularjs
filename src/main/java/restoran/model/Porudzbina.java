package restoran.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the porudzbina database table.
 * 
 */
@Entity
@NamedQuery(name="Porudzbina.findAll", query="SELECT p FROM Porudzbina p")
public class Porudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idporudzbine;

	@Temporal(TemporalType.DATE)
	private Date datumnaplate;

	private BigDecimal iznos;

	//bi-directional many-to-one association to Konobar
	@ManyToOne
	@JoinColumn(name="IDKONOBARA")
	private Konobar konobar;

	//bi-directional many-to-one association to Stavkaporudzbine
	@OneToMany(mappedBy="porudzbina")
	@JsonIgnore
	private List<StavkaPorudzbine> stavkaporudzbines;

	public Porudzbina() {
	}

	public int getIdporudzbine() {
		return this.idporudzbine;
	}

	public void setIdporudzbine(int idporudzbine) {
		this.idporudzbine = idporudzbine;
	}

	public Date getDatumnaplate() {
		return this.datumnaplate;
	}

	public void setDatumnaplate(Date datumnaplate) {
		this.datumnaplate = datumnaplate;
	}

	public BigDecimal getIznos() {
		return this.iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public Konobar getKonobar() {
		return this.konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public List<StavkaPorudzbine> getStavkaporudzbines() {
		return this.stavkaporudzbines;
	}

	public void setStavkaporudzbines(List<StavkaPorudzbine> stavkaporudzbines) {
		this.stavkaporudzbines = stavkaporudzbines;
	}

	public StavkaPorudzbine addStavkaporudzbine(StavkaPorudzbine stavkaporudzbine) {
		getStavkaporudzbines().add(stavkaporudzbine);
		stavkaporudzbine.setPorudzbina(this);

		return stavkaporudzbine;
	}

	public StavkaPorudzbine removeStavkaporudzbine(StavkaPorudzbine stavkaporudzbine) {
		getStavkaporudzbines().remove(stavkaporudzbine);
		stavkaporudzbine.setPorudzbina(null);

		return stavkaporudzbine;
	}

}