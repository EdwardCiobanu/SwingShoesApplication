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
        {@NamedQuery(name = "findMagazinById", query = "SELECT DISTINCT mag FROM Magazin mag LEFT JOIN FETCH mag.tipIncaltaminteList WHERE mag.idMagazin = :id"),
                @NamedQuery(name = "findAllMagazine", query = "SELECT DISTINCT mag FROM Magazin mag LEFT JOIN FETCH mag.tipIncaltaminteList")
        }
)
public class Magazin implements Comparable<Magazin>{
    @Id
    private Integer idMagazin;

    @OneToMany
    private List<TipIncaltaminte> tipIncaltaminteList;

    @Override
    public int compareTo(Magazin other) {
        return Integer.compare(this.idMagazin, other.idMagazin);
    }

}
