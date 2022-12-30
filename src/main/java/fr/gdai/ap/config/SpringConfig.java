package fr.gdai.ap.config;

import fr.gdai.ap.utils.MyOpenFoodFactsWrapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyOpenFoodFactsWrapper.class})
@ComponentScan({"fr.gdai.ap.service"})
public class SpringConfig {
}