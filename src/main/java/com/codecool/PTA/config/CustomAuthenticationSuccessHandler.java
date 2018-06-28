//package com.codecool.PTA.config;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String email = authentication.getName();
//        String role = getRoleAsString(authentication);
//        UserAccount userAccount = userAccountService.getUserAccountByEmail(email);
//
//        HttpSession session = request.getSession();
//        session.setAttribute("email", email);
//        session.setAttribute("role", role);
//
//        if (IsAdmin(authentication)) {
//            session.setAttribute("userId", 0);
//        } else {
//            session.setAttribute("userId", userAccount.getId());
//        }
//
//        response.sendRedirect("/list");
//    }
//}
