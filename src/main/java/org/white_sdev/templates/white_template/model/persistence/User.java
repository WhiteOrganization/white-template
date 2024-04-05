package org.white_sdev.templates.white_template.model.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "TemplateUser" //User is usually a reserved keyword in DB
        )
@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class User extends WhiteBaseEntity {

    @JsonProperty("first_name")
    @jakarta.persistence.Column(unique = true)
    String firstName;
    String email;

    public User(@NotNull String firstName, @NotNull String email) {
        this.firstName = firstName;
        this.email = email;
    }

    //region Object Overrides
    @Override
    public String toString() {
        return "{" +
                "firstName:" + firstName +
                ", email:" + email +
                "}";
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
    
    //endregion Object Overrides
    
    public static String[][] mapUsers(Collection<User> users){
        List<String[]> data = new ArrayList<>();
        for(User user:users)
            data.add(new String[]{user.getFirstName(), user.getEmail()});
        
        return data.toArray(new String[data.size()][]);
    }

    public void updateWith(User user) {
        if(!Objects.equals(this.firstName, user.firstName)) this.firstName = user.firstName;
        if(!Objects.equals(this.email, user.email)) this.email = user.email;
    }
}
