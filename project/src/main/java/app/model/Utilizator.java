package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {@NamedQuery(name = "findUtilizatorByEmail", query = "from Utilizator pers where pers.email = :email"),
                @NamedQuery(name = "findUtilizatorByEmailAndPassword", query = "from Utilizator pers where pers.email = :email and pers.password=:password"),
                @NamedQuery(name = "findUtilizatorById", query = "from Utilizator pers where pers.idUtilizator = :id"),
                @NamedQuery(name = "findAllUtilizatori", query = "from Utilizator")
        }
)
public class Utilizator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilizator;

    @Column
    private String nume;

    @Column
    private String userType;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer idMagazin;

    public Utilizator(String nume, String userType, String email, String password, Integer idMagazin) {
        this.nume = nume;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.idMagazin = idMagazin;
    }
}
