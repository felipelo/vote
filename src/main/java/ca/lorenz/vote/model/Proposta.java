package ca.lorenz.vote.model;

import ca.lorenz.model.PersistenceUnit;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Proposta implements PersistenceUnit<Integer>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String proposta;
	@Temporal(value=TemporalType.DATE)
	private Date encerramento;
	@ManyToOne
	private Morador morador;

	public Proposta() {
		this(null, "", new Date(), new Morador());
	}

	public Proposta(Integer id, String proposta, Date encerramento, Morador morador) {
		this.id = id;
		this.proposta = proposta;
		this.encerramento = encerramento;
		this.morador = morador;
	}

	public Proposta(String proposta, Morador morador) {
		this(null, proposta, new Date(), new Morador());
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProposta() {
		return this.proposta;
	}

	public void setProposta(String proposta) {
		this.proposta = proposta;
	}

	public Date getEncerramento() {
		return this.encerramento;
	}

	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}

	public Morador getMorador() {
		return this.morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", proposta=" + proposta + ", encerramento=" + encerramento + ", morador=" + morador + "]";
	}

}