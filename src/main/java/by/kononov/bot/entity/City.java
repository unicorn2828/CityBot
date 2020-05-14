package by.kononov.bot.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Component
@Entity
@Table(name = "city")
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Length(max = 45, message = "incorrect size of name - max 45 letters")
    @Length(min = 3, message = "incorrect size of name - min 3 letters")
    private String name;
    @NotBlank(message = "please fill the info")
    @Length(max = 1_000, message = "incorrect size of info - max 1000 letters")
    private String info;

    public City(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getInfo(){
        return info;
    }

    public void setInfo(String info){
        this.info = info;
    }


    @Override
    public int hashCode(){
        return Objects.hash(id, info, name);
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof City)) {
            return false;
        }
        City other = (City) obj;
        return id == other.id && Objects.equals(info, other.info) && Objects.equals(name, other.name);
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=")
          .append(id);
        sb.append(", name='")
          .append(name)
          .append('\'');
        sb.append(", info='")
          .append(info)
          .append('\'');
        sb.append('}');
        return sb.toString();
    }
}