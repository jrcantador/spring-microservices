package br.com.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.configuration.GrettingConfiguration;
import br.com.model.Gretting;

@RestController
public class GrettingController {
    
    private static final String template = "%s, %s!";

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private GrettingConfiguration configuration;
    

    @RequestMapping("/gretting")
    public Gretting gretting(@RequestParam(value = "name", defaultValue = "") String name){    	
    	if(name.isEmpty()) {
    		name = configuration.getDefaultValue();
    	}    	
        return new Gretting(counter.incrementAndGet(), String.format(template, configuration.getGretting(), name));
    }
}
