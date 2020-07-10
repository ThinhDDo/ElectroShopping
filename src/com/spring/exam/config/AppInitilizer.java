package com.spring.exam.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppContext.class, WebSecurityConfig.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebMVCConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
