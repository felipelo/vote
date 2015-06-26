package ca.lorenz.vote.action;

import ca.lorenz.vote.controller.PropostaController;
import ca.lorenz.vote.controller.PerguntaController;
import ca.lorenz.vote.controller.MoradorController;
import ca.lorenz.vote.controller.VoteController;
import ca.lorenz.vote.model.Proposta;
import ca.lorenz.vote.model.Pergunta;
import ca.lorenz.vote.model.Morador;
import org.apache.struts2.interceptor.SessionAware;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

public class PropostaAction implements SessionAware {

	private EntityManager em;
	private Map<String, Object> userSession ;

	private Proposta proposta;
	private int hash;
	private List<Proposta> propostas;
	private List<Pergunta> perguntas;
	private List<Morador> moradores;
	private int daysLeft;
	private long voted;
	private long newQuest;

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
				m.setProposta(proposta);
				mServ.save(m);
				m.setHash(m.hashCode());
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
		MoradorController mServ = new MoradorController(em);
		PropostaController serv = new PropostaController(em);
		PerguntaController pergServ = new PerguntaController(em);
		VoteController vServ = new VoteController(em);

		Morador morador = mServ.findByHash(hash);

		//validar se o já foi realizado.
		if ( morador == null || vServ.existsVoto(morador.getProposta().getId(), morador.getId()) ) {
			return "voted";
		}

		proposta = morador.getProposta();
		perguntas = pergServ.findByProposta(proposta);

		return "vote";
	}

	public String show() {
		PropostaController serv = new PropostaController(em);
		PerguntaController pServ = new PerguntaController(em);
		MoradorController mServ = new MoradorController(em);
		VoteController vServ = new VoteController(em);

		//carrega proposta
		proposta = serv.findByID(proposta.getId());
		//carrega perguntas
		perguntas = pServ.findByProposta(proposta);
		//carrega dias que faltam
		Date now = new Date();
		Date encerramento = proposta.getEncerramento();
		if (encerramento != null && now.before(encerramento)) {
			now.setHours(0);
			now.setMinutes(0);
			now.setSeconds(0);
			daysLeft = (int) ((encerramento.getTime() - now.getTime()) / 1000 / 60 / 60 / 24);
		} else {
			daysLeft = 0;
		}
		//carrega % do votacao
		int total = mServ.findByProposta(proposta.getId()).size();
		if (total > 0) {
			voted = vServ.countVotos(proposta.getId()) * 100;
			voted = (long) (voted / total);
		} else {
			voted = 0;
		}
		//carrega novas perguntas
		newQuest = pServ.countNewQuestions(proposta.getId());

		return "show";
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

	public void setHash(int hash) {
		this.hash = hash;
	}

	public int getHash() {
		return this.hash;
	}

	public List<Pergunta> getPerguntas() {
		return this.perguntas;
	}

	public int getDaysLeft() {
		return this.daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	public long getVoted() {
		return this.voted;
	}

	public void setVoted(long voted) {
		this.voted = voted;
	}

	public long getNewQuest() {
		return this.newQuest;
	}

	public void setNewQuest(long newQuest) {
		this.newQuest = newQuest;
	}
}