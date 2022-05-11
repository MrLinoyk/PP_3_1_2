package ru.kata.spring.boot_security.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "roles")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToMany (mappedBy = "roles")
    @Transient
    private Collection <User> users;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
