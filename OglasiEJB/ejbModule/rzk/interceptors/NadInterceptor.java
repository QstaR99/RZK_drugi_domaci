package rzk.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class NadInterceptor {
	@AroundInvoke
	public Object interceptAll(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName()+": metoda je interceptovana");
		return ctx.proceed();
	}
}
