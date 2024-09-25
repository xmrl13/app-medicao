package dto;

import enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {

    private String name;
    private String email;
    private Role role;

    public PersonResponseDTO(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }


}
