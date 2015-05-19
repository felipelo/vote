package ca.lorenz.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map application = ActionContext.getContext().getApplication();
		Object object = application.get("EMF_SESSION");
		EntityManagerFactory emf = null;
		EntityManager em = null;
		Object action = invocation.getAction();

		if (object == null) {
			emf = Persistence.createEntityManagerFactory("vote");
			application.put("EMF_SESSION", emf);
		} else if (object instanceof EntityManagerFactory) {
			emf = (EntityManagerFactory) object;
		}

		if (emf != null) {
			if (!emf.isOpen()) {
				emf = Persistence.createEntityManagerFactory("vote");
			}

			em = emf.createEntityManager();
			ActionContext.getContext().getValueStack().setValue("em", em);
		}

		String ret = invocation.invoke();

		if (em != null && em.isOpen()) {
			em.close();
		}

		return ret;
	}

}