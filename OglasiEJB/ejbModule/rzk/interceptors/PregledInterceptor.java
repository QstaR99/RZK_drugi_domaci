package rzk.interceptors;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;
import rzk.Statistika;

public class PregledInterceptor {
	@EJB
	Statistika stat;
	@AroundInvoke
	public Object interceptPregledi(InvocationContext ctx) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<Ogla> oglasi = (List<Ogla>)ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		
		for(Ogla o:oglasi) {
			stat.updatePregledi(o);
		}
		
		
		return ctx.proceed();
	}
}
