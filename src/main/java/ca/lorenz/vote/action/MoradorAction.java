package ca.lorenz.vote.action;

import ca.lorenz.vote.controller.MoradorController;
import ca.lorenz.vote.model.Morador;
import java.util.List;
import javax.persistence.EntityManager;

public class MoradorAction {

	private EntityManager em;

	private Morador morador;
	private List<Morador> moradores;

	public String save() {
		MoradorController serv = new MoradorController(em);

		try {
			em.getTransaction().begin();
			serv.save(morador);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		}

		return "success";
	}

	public String list() {
		MoradorController serv = new MoradorController(em);

		moradores = serv.findAll();

		return "success";
	}

	public Morador getMorador() {
		return this.morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public List<Morador> getMoradores() {
		return this.moradores;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}