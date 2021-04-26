package com.chauyiu1994.livibankans.security.oauth;

import com.chauyiu1994.livibankans.security.UserDetailsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public AccessTokenRequestFilter(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        GithubUserModel githubUserModel = null;

        if (requestTokenHeader != null && requestTokenHeader.contains("Access Token")) {
            String provider = StringUtils.substringBefore(requestTokenHeader, "Access Token").trim();
            String accessToken = StringUtils.substringAfter(requestTokenHeader, "Access Token").trim();
            githubUserModel = getUserByAccessToken(provider, accessToken);
        }

        if (githubUserModel != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String username = githubUserModel.getLogin();
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByAccessToken(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    public GithubUserModel getUserByAccessToken(String provider, String accessToken) {

        try {
            switch (provider) {
                case "Github":
                    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                    headers.add("Content-Type", "application/json");
                    headers.add("Authorization", "Token " + accessToken);
                    ResponseEntity<GithubUserModel> entity = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, new HttpEntity<>(headers), GithubUserModel.class);
                    return entity.getBody();

                default:
                    logger.warn("Unsupported oAuth provider: " + provider);
            }
        } catch (Exception e) {
            logger.warn("Exception occurs while fetching user info", e);
        }

        return null;
    }
}
