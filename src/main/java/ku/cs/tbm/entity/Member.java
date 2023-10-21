package ku.cs.tbm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;

    private String name;
    private String role;


}
