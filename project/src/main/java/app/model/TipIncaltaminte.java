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
        {@NamedQuery(name = "findTipIncaltaminteById", query = "from TipIncaltaminte pers where pers.idTipIncaltaminte = :id"),
                @NamedQuery(name = "findAllTipIncaltaminteByDisponibilitate", query = "from TipIncaltaminte pers where pers.disponibilitate = :disponibilitate"),
                @NamedQuery(name = "findAllTipIncaltaminte", query = "from TipIncaltaminte")
        }
)
public class TipIncaltaminte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipIncaltaminte;

    @Column
    private String culoare;

    @Column
    private Integer numar;

    @Column
    private Integer disponibilitate;

    @OneToOne
    private Incaltaminte incaltaminte;


    public TipIncaltaminte(Incaltaminte incaltaminte, String culoare, Integer numar, Integer disponibilitate) {
        this.incaltaminte = incaltaminte;
        this.culoare = culoare;
        this.numar = numar;
        this.disponibilitate = disponibilitate;
    }

    public TipIncaltaminte(String culoare, Integer numar, Integer disponibilitate) {
        this.culoare = culoare;
        this.numar = numar;
        this.disponibilitate = disponibilitate;
    }



}
