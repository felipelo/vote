package ca.lorenz.vote.model;

import ca.lorenz.model.PersistenceUnit;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Morador implements PersistenceUnit<Integer>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	@ManyToOne
	private Proposta proposta;

	public Morador() {
		/* !!! proposta deve ser nula, caso contrário entra em LOOP infinito!!! */
		this(null, "", "", null);
	}

	public Morador(String nome, String email, Proposta proposta) {
		this(null, nome, email, proposta);
	}

	public Morador(Integer id, String nome, String email, Proposta proposta) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.proposta = proposta;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Proposta getProposta() {
		return this.proposta;
	}

	@Override
	public String toString() {
		return "Morador [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}