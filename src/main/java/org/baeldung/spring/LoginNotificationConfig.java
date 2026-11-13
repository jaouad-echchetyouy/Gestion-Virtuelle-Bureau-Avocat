package org.baeldung.spring;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ua_parser.Parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Configuration
public class LoginNotificationConfig {

    @Bean
    public Parser uaParser() throws IOException {
        return new Parser();
    }

    @Bean
    public DatabaseReader databaseReader() throws IOException {
        ClassPathResource resource = new ClassPathResource("maxmind/GeoLite2-City.mmdb");

        // Copy to temp file because DatabaseReader needs a File, not an InputStream
        // and the resource might be inside a JAR
        File tempFile = File.createTempFile("GeoLite2-City", ".mmdb");
        tempFile.deleteOnExit();

        try (InputStream inputStream = resource.getInputStream()) {
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return new DatabaseReader.Builder(tempFile)
                .build();
    }
}
