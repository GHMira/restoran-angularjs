package restoran.security;



import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and().csrf().disable()
				.headers().disable()
				.authorizeRequests()
					.antMatchers("/app/**").permitAll() //Adding this line solved it
		            .and()
				.formLogin()
					.loginPage("/")
					.permitAll();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    	 .jdbcAuthentication()
         .dataSource(dataSource)
         .passwordEncoder(passwordEncoder())
         .usersByUsernameQuery(
                 "SELECT username, pass from konobar where username = ?")
         .authoritiesByUsernameQuery(
                 "SELECT k.username " +
                 "FROM konobar k " +
                 "WHERE k.username = ? "
             );
    }


    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    	
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
