package ca.lorenz.vote.action;

import ca.lorenz.vote.controller.MoradorController;
import ca.lorenz.vote.model.Morador;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import javax.persistence.EntityManager;

public class LoginAction implements SessionAware {

	private EntityManager em;
	private Map<String, Object> userSession ;

	private String login;
	private String senha;

	public String login() {
		if (login == null) {
			return "loginPage";
		}

		Morador morador = new MoradorController(em).findByID(1);
		userSession.put("USER_SESSION", morador);

		return "success";
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setSession(Map<String, Object> session) {
		userSession = session;
	}
}