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
                @org.hibernate.annotations.NamedQuery(name = "findEmployeeById", query = "from Employee emp where emp.id = :id"),
                @org.hibernate.annotations.NamedQuery(name = "findAllEmployees", query = "from Employee "),
                @org.hibernate.annotations.NamedQuery(name = "findEmployeeByUsernameAndPassword", query = "from Employee emp where emp.username = :username and emp.password=:password"),
                @org.hibernate.annotations.NamedQuery(name = "findEmployeeByName", query = "from Employee emp where emp.firstName = :firstName and emp.lastName=:lastName")
        }
)

public class Employee {
    public static final Integer PILOT = 1;
    public static final Integer STEWARD = 2;
    public static final Integer MANAGER = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer role;

    @Column
    private String username;

    @Column
    private String password;
}
