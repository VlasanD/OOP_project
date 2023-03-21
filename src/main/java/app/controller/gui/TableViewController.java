package app.controller.gui;

import app.model.Employee;
import app.model.Flight;
import app.service.FlightService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.TableView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class TableViewController {
    private TableView tableView;
    private final FlightService flightService= ServiceSinglePointAccess.getFlightService();
    public void startLogic(Employee employee){
        tableView=new TableView();

        List<Flight> flightList=flightService.findAll();

        Vector<String> tableHeaders=new Vector<String>();

        Vector tableData=new Vector();
        tableHeaders.add("flightId");
        tableHeaders.add("departure");
        tableHeaders.add("destination");
        tableHeaders.add("departureTime");
        tableHeaders.add("arrivalTime");
        tableHeaders.add("duration");
        tableHeaders.add("availableSeats");
        tableHeaders.add("occupiedSeats");
        for(Flight flight:flightList){
            Vector<Object> oneRow=new Vector<Object>();
            oneRow.add(flight.getFlightId());
            oneRow.add(flight.getDeparture());
            oneRow.add(flight.getDestination());
            oneRow.add(flight.getDepartureTime());
            oneRow.add(flight.getArrivalTime());
            oneRow.add(flight.getDuration());
            oneRow.add(flight.getAvailableSeats());
            oneRow.add(flight.getOccupiedSeats());

            tableData.add(oneRow);

        }

        tableView.getTable1().setModel(new DefaultTableModel(tableData,tableHeaders));
        GUIFrameSinglePointAccess.changePanel(tableView.getTableView(),"FLights");

        tableView.getBackButton().addActionListener(e->{
            EmployeeGUIController employeeGUIController = new EmployeeGUIController();
            employeeGUIController.startLogic(employee);
        });
    }

}
