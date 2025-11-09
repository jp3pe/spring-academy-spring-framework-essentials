package rewards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

// --------------------------------------------

// TODO-11 (Optional) : Disable 'DataSource' auto-configuration
// - Note that you are using your own 'DataSource' bean now
//   instead of auto-configured one
// - Use 'exclude' attribute of '@SpringBootApplication'
//   excluding 'DataSourceAutoConfiguration' class
// - Run this application and observe a failure
// - Import 'RewardsConfig' class
// - Run this application again and observe a successful execution

// TODO-12 (Optional) : Look in application.properties for the next step.

// TODO-13 (Optional) : Follow the instruction in the lab document.
//           The section titled "Build and Run using Command Line tools".

@SpringBootApplication
@ConfigurationPropertiesScan
public class RewardsApplication {
    static final String SQL = "SELECT count(*) FROM T_ACCOUNT";

    final Logger logger = LoggerFactory.getLogger(RewardsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RewardsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(JdbcTemplate jdbcTemplate) {
        String QUERY = "SELECT count(*) FROM T_ACCOUNT";
        Long numberOfAccounts = jdbcTemplate.queryForObject(QUERY, Long.class);

        return args -> logger.info("Number of accounts: {}", numberOfAccounts);
    }
}
