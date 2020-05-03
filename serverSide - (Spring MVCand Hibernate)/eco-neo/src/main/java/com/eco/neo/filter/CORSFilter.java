package com.eco.neo.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.FilterConfig;

@Component
@WebFilter("/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrq = (HttpServletRequest) request;
        HttpServletResponse hrs = (HttpServletResponse) response;
        hrs.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        hrs.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        hrs.setHeader("Access-Control-Allow-Headers", "*");
        hrs.setHeader("Access-Control-Allow-Credentials", "false");
        hrs.setHeader("Access-Control-Max-Age", "4800");
       
       System.out.println(hrq.getRequestURI()); 
       String enterpriselogin= "enterprise/auth/user/";
       String userlogin= "neo/api/adduser/";
       String userRegister = "neo/api/user/add";
       String enterpriseRegister="neo/enterprise/addenterprise";
       String urll= hrq.getRequestURI();
       if(urll.contains(userlogin) || urll.contains(enterpriselogin) || urll.contains(userRegister) || urll.contains(enterpriseRegister)) {
    	   chain.doFilter(request, response);
       }
       else {
    	   if(hrq.getHeader("authtoken") != null) {
    		   	TimeZone tz = TimeZone.getTimeZone("UTC");
    	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    	        df.setTimeZone(tz);
    	        Calendar calendar = Calendar.getInstance();
    	        calendar.setTime(new Date());
    	        calendar.add(Calendar.MINUTE,1);
    	        Date currentdate = calendar.getTime(); 
    	        
    	        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    	        String exp = hrq.getHeader("authtoken");
    	        Date d2;
				try {
					d2 = df1.parse(exp);
					 if(currentdate.compareTo(d2) < 0) {
			   	        	chain.doFilter(request, response);
			   	        }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	       // int auth = Integer.valueOf(hrq.getHeader("auth")) ;
    	       }  
       }
      
    }
    private FilterConfig filterConfig;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }
    

}