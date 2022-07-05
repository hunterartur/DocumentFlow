package com.arturishmaev.documentflow.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "staff")
public class EmployeeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, message = "The name should not be empty and the length and I have at least 2 letters!")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, message = "The name should not be empty and the length and I have at least 2 letters!")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 2, message = "The name should not be empty and the length and I have at least 2 letters!")
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank(message = "The name should not be empty!")
    @Column(name = "post")
    private String post;

    @ManyToOne(cascade = CascadeType.ALL)
    private DepartmentEntity department;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentsAuthor;

    @OneToMany(mappedBy = "executor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentsExecutor;

    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Example email: examples@mail.ru")
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "employees_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    private String password;
    private boolean locked;
    private boolean expired;
    private boolean enabled;

    public EmployeeEntity(String firstName, String lastName, String patronymic
            , String post, DepartmentEntity department, List<AssignmentEntity> assignmentsAuthor
            , List<AssignmentEntity> assignmentsExecutor, String email, Set<RoleEntity> roles
            , String password, boolean locked, boolean expired, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.post = post;
        this.department = department;
        this.assignmentsAuthor = assignmentsAuthor;
        this.assignmentsExecutor = assignmentsExecutor;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.locked = locked;
        this.expired = expired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expired;
    }
}
