package restoran.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the jelo database table.
 * 
 */
@Entity
@NamedQuery(name="Jelo.findAll", query="SELECT j FROM Jelo j")
public class Jelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idjela;

	private BigDecimal cenaj;

	@Column(name="id_jela")
	private int idJela;

	private String kategorijaj;

	private BigDecimal kolicinaj;

	private String nazivj;

	//bi-directional many-to-many association to Dnevnimeni
	@ManyToMany
	@JoinTable(
		name="jelamenia"
		, joinColumns={
			@JoinColumn(name="IDJELA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDDM")
			}
		)
	private List<Dnevnimeni> dnevnimenis;

	//bi-directional many-to-one association to Stavkaporudzbine
	@OneToMany(mappedBy="jelo")
	private List<StavkaPorudzbine> stavkaporudzbines;

	public Jelo() {
	}

	public int getIdjela() {
		return this.idjela;
	}

	public void setIdjela(int idjela) {
		this.idjela = idjela;
	}

	public BigDecimal getCenaj() {
		return this.cenaj;
	}

	public void setCenaj(BigDecimal cenaj) {
		this.cenaj = cenaj;
	}

	public int getIdJela() {
		return this.idJela;
	}

	public void setIdJela(int idJela) {
		this.idJela = idJela;
	}

	public String getKategorijaj() {
		return this.kategorijaj;
	}

	public void setKategorijaj(String kategorijaj) {
		this.kategorijaj = kategorijaj;
	}

	public BigDecimal getKolicinaj() {
		return this.kolicinaj;
	}

	public void setKolicinaj(BigDecimal kolicinaj) {
		this.kolicinaj = kolicinaj;
	}

	public String getNazivj() {
		return this.nazivj;
	}

	public void setNazivj(String nazivj) {
		this.nazivj = nazivj;
	}

	public List<Dnevnimeni> getDnevnimenis() {
		return this.dnevnimenis;
	}

	public void setDnevnimenis(List<Dnevnimeni> dnevnimenis) {
		this.dnevnimenis = dnevnimenis;
	}

	public List<StavkaPorudzbine> getStavkaporudzbines() {
		return this.stavkaporudzbines;
	}

	public void setStavkaporudzbines(List<StavkaPorudzbine> stavkaporudzbines) {
		this.stavkaporudzbines = stavkaporudzbines;
	}

	public StavkaPorudzbine addStavkaporudzbine(StavkaPorudzbine stavkaporudzbine) {
		getStavkaporudzbines().add(stavkaporudzbine);
		stavkaporudzbine.setJelo(this);

		return stavkaporudzbine;
	}

	public StavkaPorudzbine removeStavkaporudzbine(StavkaPorudzbine stavkaporudzbine) {
		getStavkaporudzbines().remove(stavkaporudzbine);
		stavkaporudzbine.setJelo(null);

		return stavkaporudzbine;
	}

}