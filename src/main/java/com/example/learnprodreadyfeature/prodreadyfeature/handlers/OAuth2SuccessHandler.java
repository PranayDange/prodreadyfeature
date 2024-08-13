package com.example.learnprodreadyfeature.prodreadyfeature.handlers;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import com.example.learnprodreadyfeature.prodreadyfeature.services.JwtService;
import com.example.learnprodreadyfeature.prodreadyfeature.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        logger.info(oAuth2User.getAttribute("email"));

        User user = userService.getUserByEmail(email);
        if (user == null) {
            User newUser = User.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .build();

            user = userService.save(newUser);
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        // cookie.setSecure(); this should be only used by https request
        response.addCookie(cookie);

        String frontEndUrl = "http://localhost:8080/home.html?token=" + accessToken;

        getRedirectStrategy().sendRedirect(request, response, frontEndUrl);

    }
}
