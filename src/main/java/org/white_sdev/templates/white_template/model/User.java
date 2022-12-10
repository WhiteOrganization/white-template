package org.white_sdev.templates.white_template.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class User {

    @JsonProperty("first_name")
    String firstName;
    String email;

    public User(@NotNull String firstName, @NotNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "[firstName:" + firstName + "],[email:" + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        try{
            return ((User)obj).firstName.equals(firstName);
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.firstName);
        return hash;
    }

    public static String[][] mapUsers(Collection<User> users){
        List<String[]> data = new ArrayList<>();
        for(User user:users){
            data.add(new String[]{user.getFirstName(), user.getEmail()});
        }
        return data.toArray(new String[data.size()][]);
    }
}
