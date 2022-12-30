package fr.gdai.ap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"fr.gdai.ap.controller", "fr.gdai.ap.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
