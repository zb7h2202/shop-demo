package com.myself.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(filterName = "CostTimeFilter",urlPatterns = "/*")
public class CostTimeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        Long start = System.currentTimeMillis();
        log.info("star url:{}",requestURI);

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("end url:{},cost:{}",requestURI,System.currentTimeMillis()-start);
    }

    @Override
    public void destroy() {

    }
}
