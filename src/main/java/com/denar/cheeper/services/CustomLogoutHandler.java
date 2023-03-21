package com.denar.cheeper.services;

import com.denar.cheeper.datalayer.entities.User;
import com.denar.cheeper.datalayer.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
public class CustomLogoutHandler implements LogoutSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        if (user.isEnabled()) {
            user.setEnabled(false);
            userRepository.save(user);
        }
        try {
            UrlPathHelper url = new UrlPathHelper();
            String path = url.getContextPath(request);

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(path + "/login");
        } catch (IOException e) {
            log.info("{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void logout(HttpServletRequest request,
//                       HttpServletResponse response,
//                       Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        if (user.isEnabled()) {
//            user.setEnabled(false);
//            userRepository.save(user);
//        }
//        try {
//            response.sendRedirect(request.getRequestURL().toString());
//        } catch (IOException e) {
//            log.info("{}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
}
