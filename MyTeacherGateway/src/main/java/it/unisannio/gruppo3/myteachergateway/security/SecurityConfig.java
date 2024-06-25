package it.unisannio.gruppo3.myteachergateway.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
public class SecurityConfig {

    private final MongoAuthUserDetailService userDetailsService;

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(MongoAuthUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/public/student/**").permitAll()
                        .requestMatchers("/public/teacher/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/myTeacher/users/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/myTeacher/teachers/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/myTeacher/students/").permitAll()
                        .requestMatchers("/myTeacher/lessonsAgendas/").permitAll()
                        .anyRequest().authenticated())
                        .formLogin(formLogin -> formLogin.loginPage("/public/login.html").permitAll())
                        .httpBasic(withDefaults())
                        .csrf(csrf -> csrf.disable())
        ;





        //http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderPlain();
    }

}
