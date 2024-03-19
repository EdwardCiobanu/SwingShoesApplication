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
        {@NamedQuery(name = "findIncaltaminteByProducator", query = "from Incaltaminte pers where pers.producator = :producator"),
                @NamedQuery(name = "findIncaltaminteByNume", query = "from Incaltaminte pers where pers.nume = :nume"),
                @NamedQuery(name = "findAllIncaltaminteByProducator", query = "from Incaltaminte pers where pers.producator = :producator"),
                @NamedQuery(name = "findAllIncaltaminteByPret", query = "from Incaltaminte pers where pers.pret = :pret"),
                @NamedQuery(name = "findIncaltaminteById", query = "from Incaltaminte pers where pers.idIncaltaminte = :id"),
                @NamedQuery(name = "findAllIncaltaminte", query = "from Incaltaminte")
        }
)
public class Incaltaminte implements Comparable<Incaltaminte>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIncaltaminte;

    @Column
    private String nume;

    @Column
    private String producator;

    @Column
    private Integer pret;



    public Incaltaminte(String nume, String producator, Integer pret) {
        this.nume = nume;
        this.producator = producator;
        this.pret = pret;
    }

    @Override
    public int compareTo(Incaltaminte other) {
        return Integer.compare(this.pret, other.pret);
    }
}
