package com.ruida.springbootdemo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 输入内容过滤器
 * @author: chenjy
 * @create: 2020-06-18 15:18
 */
@Slf4j
public class InputFilter implements Filter {

    String [] array  = {"习近平","胡锦涛","温家宝","李克强"};

    public List<String> list;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init......");
        list = Arrays.asList(array);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("something before doFilter....");
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("getParameter")){
                    String value = (String) method.invoke(request,args);
                    if(value != null){
                        for(String str:list){
                            if(value.contains(str)){
                                value = value.replace(str,"***");
                            }
                        }
                        return value;
                    }
                }
                return method.invoke(request,args);
            }
        });

        filterChain.doFilter(proxy_req,response);

        log.info("something after doFilter....");
    }

    @Override
    public void destroy() {
        log.info("filter destroy......");
    }

}
