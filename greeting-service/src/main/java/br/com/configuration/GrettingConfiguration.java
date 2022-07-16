package br.com.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("gretting-service")
public class GrettingConfiguration {

	private String gretting;
	private String defaultValue;
	
	public GrettingConfiguration() {
	}

	public String getGretting() {
		return gretting;
	}

	public void setGretting(String gretting) {
		this.gretting = gretting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
	
}
