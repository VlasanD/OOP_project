package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findBaggageById", query = "from Baggage bag where bag.idBaggage = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllBaggages", query = "from Baggage ")
        }
)

public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBaggage;

    @Column
    private Integer weight;

    @Column
    private Integer length;

    @Column
    private Integer width;

    @Column
    private Integer height;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Passenger passenger;


}
