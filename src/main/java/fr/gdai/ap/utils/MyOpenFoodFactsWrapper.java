package fr.gdai.ap.utils;

import org.springframework.context.annotation.Bean;
import pl.coderion.service.OpenFoodFactsWrapper;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

public class MyOpenFoodFactsWrapper {

    @Bean
    OpenFoodFactsWrapper getOpenFoodFactsWrapper() {
        return new OpenFoodFactsWrapperImpl();
    }

}
