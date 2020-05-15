package by.kononov.bot.config;

import by.kononov.bot.encoder.CustomEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static by.kononov.bot.config.ConfigType.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    private static final String USERS_BY_NAME_QUERY = "SELECT username, password, active FROM user WHERE username = ?";
    private static final String AUTHORITIES_BY_NAME_QUERY = "SELECT u.username, ur.roles FROM user u INNER JOIN user_role ur ON u.id = ur.user_id WHERE u.username = ?";
    @Autowired
    private CustomEncoder customEncoder;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
           .antMatchers(PACKAGE_CSS);
        web.ignoring()
           .antMatchers(PACKAGE_IMAGES);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers(ANY_PAGE, HOME_PAGE, INFO_PAGE, REGISTRATION_PAGE)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage(LOGIN_PAGE)
            .permitAll()
            .and()
            .rememberMe()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PAGE))
            .invalidateHttpSession(true)
            .deleteCookies(DELETE_COOKIES)
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(customEncoder.receivePasswordEncoder())
            .usersByUsernameQuery(USERS_BY_NAME_QUERY)
            .authoritiesByUsernameQuery(AUTHORITIES_BY_NAME_QUERY);
    }
}
