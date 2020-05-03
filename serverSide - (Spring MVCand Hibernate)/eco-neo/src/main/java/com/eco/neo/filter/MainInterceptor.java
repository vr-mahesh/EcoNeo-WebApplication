//package com.eco.neo.filter;
//
//import java.io.PrintWriter;
//import java.util.Enumeration;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class MainInterceptor implements HandlerInterceptor
//{
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler) throws Exception {
//      
//         PrintWriter out = response.getWriter();
//         System.out.println(request.getHeader("auth"));
//         if(request.getHeader("auth") != null) {
//  int auth = Integer.valueOf(request.getHeader("auth")) ;
//    if(auth == 10) {
//    	return true;
//    }
//    }
//         return false;
//    }
// 
//    @Override
//    public void postHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler,
//            ModelAndView modelAndView) throws Exception {
//      
//    }
// 
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//            HttpServletResponse response, Object handler, Exception exception)
//            throws Exception {
//       
//    }
//}