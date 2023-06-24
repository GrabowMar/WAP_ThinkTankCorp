package org.grabowskiandgajda.thinktankcorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(ThinkTankCorpRuntimeHints.class)
public class ThinkTankCorpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThinkTankCorpApplication.class, args);
	}

}
