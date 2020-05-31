package restoran.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the dnevnimeni database table.
 * 
 */
@Entity
@NamedQuery(name="Dnevnimeni.findAll", query="SELECT d FROM Dnevnimeni d")
public class Dnevnimeni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int iddm;

	@Temporal(TemporalType.DATE)
	private Date datumdm;

	//bi-directional many-to-many association to Jelo
	@ManyToMany(mappedBy="dnevnimenis")
	@JsonIgnore
	private List<Jelo> jelos;

	public Dnevnimeni() {
	}

	public int getIddm() {
		return this.iddm;
	}

	public void setIddm(int iddm) {
		this.iddm = iddm;
	}

	public Date getDatumdm() {
		return this.datumdm;
	}

	public void setDatumdm(Date datumdm) {
		this.datumdm = datumdm;
	}

	public List<Jelo> getJelos() {
		return this.jelos;
	}

	public void setJelos(List<Jelo> jelos) {
		this.jelos = jelos;
	}

}