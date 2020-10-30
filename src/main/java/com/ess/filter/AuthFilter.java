package com.ess.filter;


import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.ReflectionUtils;

@Component
public class AuthFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return RequestContext.getCurrentContext().containsKey("error.status_code");
	}

	@Override
	public Object run() throws ZuulException {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			Object e = ctx.get("error.exception");

			if (e != null && e instanceof ZuulException) {
				ZuulException zuulException = (ZuulException)e;
				System.out.println("Zuul failure detected: " + zuulException.getMessage());
				ctx.remove("error.status_code");
				ctx.setResponseBody("Overriding Zuul Exception Body");
				ctx.getResponse().setContentType("application/json");
				ctx.setResponseStatusCode(500); //Can set any error code as excepted
			}
		}
		catch (Exception ex) {
			System.out.println("Exception filtering in custom error filter: "+ ex);
			ReflectionUtils.rethrowRuntimeException(ex);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
