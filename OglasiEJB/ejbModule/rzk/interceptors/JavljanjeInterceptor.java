package rzk.interceptors;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;
import rzk.Statistika;

public class JavljanjeInterceptor {
	@EJB
	Statistika stat;
	@AroundInvoke
	public Object javljanjeIntercept(InvocationContext ctx) throws Exception {
		stat.updateJavljanja();
		return ctx.proceed();
	}
}
