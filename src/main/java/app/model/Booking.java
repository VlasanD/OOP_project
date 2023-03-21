package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findBookingById", query = "from Booking bck where bck.idBooking = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllBookings", query = "from Booking "),
                @org.hibernate.annotations.NamedQuery(name = "findBookingByPassenger", query = "from Booking bck where bck.passenger = :passenger"),
        }
)

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;

    private Integer price;

    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Passenger passenger;

}
