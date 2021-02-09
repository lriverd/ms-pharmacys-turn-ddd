package cl.duamit.config;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 05-08-2020
 * @since 1.0.0 - 05-08-2020
 */
@Repository
public class DataBaseUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseUpdater.class);

    public static final String VERBOSE = "verbose";
    private static final String CHANGELOG_LOCATION = "classpath:/db/changelog/changelog-master.xml";

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Execute the update of liquibase to generate the database
     *
     * @return spring implementation of liquibase
     */
    @Bean
    public SpringLiquibase liquibase() {

        Resource resource = resourceLoader.getResource(CHANGELOG_LOCATION);
        Assert.state(resource.exists(), "Unable to find file: " + CHANGELOG_LOCATION);

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(CHANGELOG_LOCATION);
        liquibase.setShouldRun(shouldExecuteLiquibase());

        Map<String, String> params = new HashMap<>();
        params.put(VERBOSE, Boolean.TRUE.toString());
        liquibase.setChangeLogParameters(params);

        return liquibase;
    }

    private boolean shouldExecuteLiquibase() {
        String prop = ApplicationContextProvider
                .getApplicationContext()
                .getEnvironment()
                .getProperty("datasource.updateLiquibase", "true");
        boolean updateLiquibase = Boolean.parseBoolean(prop);
        LOGGER.info(":::::::Update Liquibase -> {}", prop);
        return updateLiquibase;
    }

}
