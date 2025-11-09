package rewards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "rewards.recipient")
public class RewardsRecipientProperties {
    private String name;
    private String age;
    private String gender;
    private String hobby;

    @Bean
    CommandLineRunner rewardsRecipientPropertiesRunner(RewardsRecipientProperties rewardsRecipientProperties) {
        return args -> {
            System.out.println("rewards-recipient name: " + rewardsRecipientProperties.getName());
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
