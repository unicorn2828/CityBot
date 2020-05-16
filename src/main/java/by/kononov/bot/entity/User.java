package by.kononov.bot.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "user")
public class User{
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Username can't be empty")
    @Length(max = 45, message = "incorrect length of username - max 45 letters")
    private String username;

    @NotBlank(message = "Password can't be empty")
    @Length(max = 45, message = "incorrect length of password - max 45 letters")
    private String password;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email isn't correct")
    private String email;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=")
          .append(id);
        sb.append(", userName='")
          .append(username)
          .append('\'');
        sb.append(", password='")
          .append(password)
          .append('\'');
        sb.append(", active=")
          .append(active);
        sb.append(", roles=")
          .append(roles);
        sb.append('}');
        return sb.toString();
    }
}
