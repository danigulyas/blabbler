package com.danigu.web;

import com.danigu.web.config.RootConfig;
import com.danigu.web.config.WebConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Entry point, will be discovered by servlet container if > servlet 3.1.
 * @author dani
 */
public class BlabblerWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public static final Class<?>[] ROOT_CONFIG_CLASSES = new Class<?>[]{ RootConfig.class };
    public static final Class<?>[] SERVLET_CONFIG_CLASSES = new Class<?>[] { WebConfig.class };

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return ROOT_CONFIG_CLASSES;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return SERVLET_CONFIG_CLASSES;
    }
}
