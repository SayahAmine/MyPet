package model.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private String username;
    private String password;

    private String secretQuestion;
    private String secretAnswer;

    public Admin() {}

    public Admin(String username, String password, String secretQuestion, String secretAnswer) {
        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }
}
