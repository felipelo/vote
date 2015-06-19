package ca.lorenz.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor {

	private static Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String name = invocation.getInvocationContext().getName();
		Map session = ActionContext.getContext().getSession();
		Object object = session.get( "USER_SESSION" );

		System.out.println("Object > " + object + "name > " + name);
		if( (object == null) && !(
			"login".equals(name) || "vote".equals(name) || "error_vote".equals(name) ||
			"proposta_vote".equals(name) || "perguntar".equals(name) )) {
			LOG.debug("Reurning to LOGIN action.");
			System.out.println("LOGIN --------");
			return ActionSupport.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}