package org.baeldung;

import org.baeldung.persistence.dao.pfe.BureauAvocatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer{
	@Autowired
	private BureauAvocatRepository bureauAvocatRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	/*
	 * @Override public void run(String... args) throws Exception { BureauAvocat
	 * bureauAvocat=new BureauAvocat(); bureauAvocat.setAdresse("fesss 1233");
	 * bureauAvocat.setNom("avocat");
	 *  bureauAvocatRepository.save(bureauAvocat);
	 * System.err.println("finich");
	 *
	 * }
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

}