package ca.lorenz.vote.action;

import ca.lorenz.vote.controller.PropostaController;
import ca.lorenz.vote.controller.PerguntaController;
import ca.lorenz.vote.controller.MoradorController;
import ca.lorenz.vote.model.Proposta;
import ca.lorenz.vote.model.Pergunta;
import ca.lorenz.vote.model.Morador;
import org.apache.struts2.interceptor.SessionAware;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

public class PropostaAction implements SessionAware {

	private EntityManager em;
	private Map<String, Object> userSession ;

	private Proposta proposta;
	private List<Proposta> propostas;
	private List<Pergunta> perguntas;
	private List<Morador> moradores;

	public String novo() {
		proposta = new Proposta();
		
		return "novo";
	}

	public String novoMoradores() {
		PropostaController serv = new PropostaController(em);
		MoradorController mServ = new MoradorController(em);

		proposta = serv.findByID(proposta.getId());
		moradores = mServ.findByProposta(proposta.getId());
		System.out.println("-=-=-=-=-=-=" + moradores.size());
		for (int x = moradores.size(); x < 32; x++) {
			moradores.add(new Morador());
		}

		return "novo_moradores";
	}

	public String save() {
		PropostaController serv = new PropostaController(em);

		try {
			Morador morador = (Morador) userSession.get("USER_SESSION");
			proposta.setMorador(morador);

			em.getTransaction().begin();
			serv.save(proposta);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		}

		return "success";
	}

	public String saveMoradores() {
		PropostaController serv = new PropostaController(em);
		MoradorController mServ = new MoradorController(em);

		//remover todos os moradores em branco
		List<Morador> temp = new ArrayList<Morador>(moradores.size());
		for (Morador m : moradores) {
			String nome = m.getNome().trim();
			String email = m.getEmail().trim();
			if (!"".equals(nome) && !"".equals(email)) {				
				temp.add(m);
			}
		}
		moradores = temp;

		try {
			em.getTransaction().begin();
			proposta = serv.findByID(proposta.getId());
			for (Morador m : moradores) {
				System.out.println("))))))))"+m);
				m.setProposta(proposta);
				mServ.save(m);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		}

		return "saveMoradores";
	}

	public String list() {
		PropostaController serv = new PropostaController(em);

		propostas = serv.findAll();

		return "list";
	}

	public String vote() {
		PropostaController serv = new PropostaController(em);
		PerguntaController pergServ = new PerguntaController(em);

		proposta = serv.findByID(proposta.getId());
		perguntas = pergServ.findByProposta(proposta);

		return "vote";
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Proposta getProposta() {
		return this.proposta;
	}

	public List<Proposta> getPropostas() {
		return this.propostas;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public List<Morador> getMoradores() {
		return this.moradores;
	}

	public void setSession(Map<String, Object> session) {
		userSession = session;
	}
}