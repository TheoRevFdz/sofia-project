package com.educibertec.sofiaproject.config;

import com.educibertec.sofiaproject.security.RestAuthenticationEntryPoint;
import com.educibertec.sofiaproject.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors ->
                        cors.configurationSource(request -> {
                            var c = new CorsConfiguration();
                            c.setAllowedOrigins(List.of("*"));
                            c.setAllowedMethods(List.of(HttpMethod.GET.name(),
                                    HttpMethod.POST.name(), HttpMethod.PUT.name(),
                                    HttpMethod.DELETE.name()));
                            c.setAllowedHeaders(List.of("*"));
                            return c;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers("/auth/**",
                                        "/api/fakerData/generate/**",
                                        "/api/auth/createUser"
                                        //"/crud_Productos/**"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated())
        ;

//        http.formLogin(form->form.loginPage("/auth")
//                .successHandler(successHandler())
//                .permitAll()
//        );
        http.formLogin(formLogin -> formLogin.successHandler(successHandler())
                .permitAll());
        http.sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                            .invalidSessionUrl("/login")
                            .maximumSessions(1)
                            .expiredUrl("/login")
                            .sessionRegistry(sessionRegistry());
                    session.sessionFixation().migrateSession();
                }
        );

        //http.securityMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/static/**")));
        //http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new RestAuthenticationEntryPoint()));

//        http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public TokenAuthenticationFilter createTokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    public AuthenticationSuccessHandler successHandler() {
        return (((request, response, authentication) -> response.sendRedirect("/crud_Productos")));
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
