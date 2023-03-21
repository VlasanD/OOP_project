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
                @org.hibernate.annotations.NamedQuery(name = "findPassengerByName", query = "from Passenger psg where psg.lastName = :lastName and psg.firstName=:firstName"),
                @org.hibernate.annotations.NamedQuery(name = "findPassengerById", query = "from Passenger psg where  psg.idPassenger= :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllPassengers", query = "from Passenger")
        }
)

public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPassenger;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String mail;


}
