package app.controller.gui;

import app.model.Baggage;
import app.model.Employee;
import app.model.Flight;
import app.model.Passenger;
import app.service.EmployeeService;
import app.service.FlightService;
import app.service.PassengerService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.EmployeeGUI;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeGUIController {
    private EmployeeGUI employeeGUI;
    private final EmployeeService employeeService = ServiceSinglePointAccess.getEmployeeService();
    private final FlightService flightService = ServiceSinglePointAccess.getFlightService();
    private final PassengerService passengerService = ServiceSinglePointAccess.getPassengerService();

    public void startLogic(Employee employee) {
        employeeGUI = new EmployeeGUI();
        GUIFrameSinglePointAccess.changePanel(employeeGUI.getEmployeePanel(), "Welcome " + employee.getFirstName());
        employeeGUI.getAddEmployeeButton().addActionListener(e -> {
            String firstName = employeeGUI.getFirstNameTextField().getText();
            String lastName = employeeGUI.getLastNameTextField().getText();

            Integer id = Integer.parseInt(employeeGUI.getFlightIdTextField().getText());

            Employee employeeToAdd = employeeService.findByName(firstName, lastName);

            Flight flight = flightService.findById(id);

            flightService.addEmployee(flight, employeeToAdd);
        });
        employeeGUI.getAddPassengerButton().addActionListener(e -> {
            String firstName = employeeGUI.getFirstNameTextField1().getText();
            String lastName = employeeGUI.getLastNameTextField1().getText();

            Integer id = Integer.parseInt(employeeGUI.getFlightIdTextField1().getText());

            Passenger passenger = passengerService.findByName(firstName, lastName);

            Flight flight = flightService.findById(id);

            flightService.addPassenger(flight, passenger);
        });
        employeeGUI.getAddBaggageButton().addActionListener(e->{
             Integer weight=Integer.parseInt(employeeGUI.getWeightTextField().getText());
             Integer length=Integer.parseInt(employeeGUI.getLengthTextField().getText());
             Integer width=Integer.parseInt(employeeGUI.getWidthTextField().getText());
             Integer height=Integer.parseInt(employeeGUI.getHeightTextField().getText());
             Integer idPassenger=Integer.parseInt(employeeGUI.getIdPassengerTextField().getText());
             Integer idFlight=Integer.parseInt(employeeGUI.getFlightIdTextField4().getText());

             Passenger passenger= passengerService.findById(idPassenger);
             Baggage baggage=new Baggage();
             baggage.setWeight(weight);
             baggage.setLength(length);
             baggage.setWidth(width);
             baggage.setHeight(height);
             baggage.setPassenger(passenger);
             Flight flight=flightService.findById(idFlight);

             flightService.addBaggage(flight,baggage);
        });
        employeeGUI.getAddFlight().addActionListener(e -> {
            Integer availableSeats = Integer.parseInt(employeeGUI.getAvailableSeatsTextField().getText());
            Integer occupiedSeats = Integer.parseInt(employeeGUI.getPreOccupiedSeatsTextField().getText());
            LocalDate departureTime = LocalDate.parse(employeeGUI.getDepartureTimeTextField().getText());
            LocalDate arrivalTime = LocalDate.parse(employeeGUI.getArrivalTimeTextField().getText());
            String departure = employeeGUI.getDepartureTextField().getText();
            String destination = employeeGUI.getDestinationTextField().getText();
            Flight flight = new Flight();
            flight.setArrivalTime(arrivalTime);
            flight.setDepartureTime(departureTime);
            flight.setDeparture(departure);
            flight.setDestination(destination);
            flight.setOccupiedSeats(occupiedSeats);
            flight.setAvailableSeats(availableSeats);
            flight.setDuration(Period.between(departureTime, arrivalTime));
            flightService.save(flight);
        });
        employeeGUI.getUpdateFlightButton().addActionListener(e -> {
            Integer id = Integer.parseInt(employeeGUI.getFlightIdTextField2().getText());
            LocalDate departureTime = LocalDate.parse(employeeGUI.getDepartureTimeTextField1().getText());
            LocalDate arrivalTime = LocalDate.parse(employeeGUI.getArrivalTimeTextField1().getText());


            Flight flight = flightService.findById(id);

            flight.setArrivalTime(arrivalTime);
            flight.setDepartureTime(departureTime);
            flight.setDuration(Period.between(departureTime, arrivalTime));

            flightService.update(flight);

        });
        employeeGUI.getDeleteFlightButton().addActionListener(e -> {
            Integer id = Integer.parseInt(employeeGUI.getFlightIdTextField3().getText());
            flightService.delete(flightService.findById(id));
        });
        employeeGUI.getFlightsButton().addActionListener(e -> {
            TableViewController tableViewController = new TableViewController();
            tableViewController.startLogic(employee);
        });
        employeeGUI.getBaggagesButton().addActionListener(e -> {
            BaggageViewController baggageViewController = new BaggageViewController();
            baggageViewController.startLogic(employee);
        });
        employeeGUI.getBookingsButton().addActionListener(e -> {
            BookingViewController bookingViewController= new BookingViewController();
            bookingViewController.startLogic(employee);
        });
    }
}
