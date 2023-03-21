package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(name = "findFlightByDepartureAndDestination", query = "from Flight fl where fl.departure = :departure and fl.destination= : destination"),
                @org.hibernate.annotations.NamedQuery(name = "findFlightById", query = "from Flight fl where fl.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllFlights", query = "from Flight ")
        }
)

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

    @Column
    private Integer availableSeats;

    @Column
    private Integer occupiedSeats;

    @Column
    private LocalDate departureTime;

    @Column
    private LocalDate arrivalTime;

    @Column
    private Period duration;

    @Column
    private String departure;

    @Column
    private String destination;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Employee> employees;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Passenger> passengers;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Baggage> baggages;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Booking> bookings;

}
