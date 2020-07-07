package demoapp.sdemo.security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {

        MyUserDetails admin_user=new MyUserDetails(new User("foo","foo","ROLE_ADMIN",true));
        MyUserDetails user_user=new MyUserDetails(new User("bar","bar","ROLE_USER",true));
        return new InMemoryUserDetailsManager(Arrays.asList(admin_user, user_user));
    }
}
