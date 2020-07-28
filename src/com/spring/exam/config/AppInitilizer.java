package com.spring.exam.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

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
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		String storageLocation = "/img/avata";
		int maxFileSize = 5 * 1024 * 1024; // Maximum individual file
		int maxRequestSize = maxFileSize * 4; // Multiple files in single request
		int fileSizeThreshold = maxFileSize / 2;
		
		MultipartConfigElement multipartConfigElement = 
				new MultipartConfigElement(storageLocation, 
											maxFileSize, 
											maxRequestSize, 
											fileSizeThreshold);
		registration.setMultipartConfig(multipartConfigElement);
	}
}
