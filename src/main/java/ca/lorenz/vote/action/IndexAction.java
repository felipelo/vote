package ca.lorenz.vote.action;

import javax.persistence.EntityManager;

public class IndexAction {

	private EntityManager em;

	public String index() {
		return "success";
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}