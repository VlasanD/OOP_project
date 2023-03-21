package app.controller.gui;

import app.model.Baggage;
import app.model.Booking;
import app.model.Employee;
import app.service.BaggageService;
import app.service.BookingService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.BaggageView;
import app.view.BookingView;

import javax.swing.table.DefaultTableModel;
import java.awt.print.Book;
import java.util.List;
import java.util.Vector;

public class BookingViewController {
    private BookingView tableView;
    private final BookingService bookingService= ServiceSinglePointAccess.getBookingService();

    public void startLogic(Employee employee){
        tableView=new BookingView();

        List<Booking> bookingList=bookingService.findAll();

        Vector<String> tableHeaders=new Vector<String>();

        Vector tableData=new Vector();
        tableHeaders.add("idBooking");
        tableHeaders.add("firstName");
        tableHeaders.add("lastName");
        tableHeaders.add("price");
        tableHeaders.add("date");
        for(Booking booking:bookingList){
            Vector<Object> oneRow=new Vector<Object>();
            oneRow.add(booking.getIdBooking());
            oneRow.add(booking.getPassenger().getFirstName());
            oneRow.add(booking.getPassenger().getLastName());
            oneRow.add(booking.getPrice());
            oneRow.add(booking.getDate());

            tableData.add(oneRow);

        }

        tableView.getTable1().setModel(new DefaultTableModel(tableData,tableHeaders));
        GUIFrameSinglePointAccess.changePanel(tableView.getPanel(),"FLights");

        tableView.getBackButton().addActionListener(e->{
            EmployeeGUIController employeeGUIController = new EmployeeGUIController();
            employeeGUIController.startLogic(employee);
        });
    }
}
