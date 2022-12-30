package fr.gdai.ap.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletConfig extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // 乱码处理
    @Override
    protected Filter[] getServletFilters() {
        // 使用 SpringMVC 提供的字符过滤器
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        // 设置字符过滤器的编码类型为 utf-8
        filter.setEncoding("utf-8");
        return new Filter[]{filter};
    }

}
