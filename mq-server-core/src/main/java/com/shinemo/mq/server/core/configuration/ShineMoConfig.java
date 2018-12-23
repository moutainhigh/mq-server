package com.shinemo.mq.server.core.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by harold on 11/21/17.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "shinemo")
public class ShineMoConfig {
	
	
	@Valid
	@NotNull
	private Application application;

	@Valid
	@NotNull
	private Jce jce;

	@Valid
	@NotNull
	private Mq mq;

	@Data
	public static class Application {
		@NotNull
		private String domain;
		@NotNull
		private Map<String, Integer> siteId;
	}

	@Data
	public static class Jce {
		@NotNull
		private Map<String, String> siteDomain;
	}

	@Data
	public static class Mq {
		@NotNull
		private String address;
		
	}
}
