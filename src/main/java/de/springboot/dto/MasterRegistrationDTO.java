package de.springboot.dto;

import de.springboot.model.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterRegistrationDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Set<Specification> specifications;
}