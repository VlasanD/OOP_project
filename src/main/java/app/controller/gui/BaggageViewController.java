package app.controller.gui;

import app.model.Baggage;
import app.model.Employee;
import app.service.BaggageService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.BaggageView;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class BaggageViewController {
    private BaggageView tableView;
    private final BaggageService baggageService= ServiceSinglePointAccess.getBaggageService();

    public void startLogic(Employee employee){
        tableView=new BaggageView();

        List<Baggage> baggageList=baggageService.findAll();

        Vector<String> tableHeaders=new Vector<String>();

        Vector tableData=new Vector();
        tableHeaders.add("firstName");
        tableHeaders.add("lastName");
        tableHeaders.add("weight");
        tableHeaders.add("length");
        tableHeaders.add("width");
        tableHeaders.add("height");
        for(Baggage baggage:baggageList){
            Vector<Object> oneRow=new Vector<Object>();
            oneRow.add(baggage.getPassenger().getFirstName());
            oneRow.add(baggage.getPassenger().getLastName());
            oneRow.add(baggage.getWeight());
            oneRow.add(baggage.getLength());
            oneRow.add(baggage.getWidth());
            oneRow.add(baggage.getHeight());

            tableData.add(oneRow);

        }

        tableView.getTable1().setModel(new DefaultTableModel(tableData,tableHeaders));
        GUIFrameSinglePointAccess.changePanel(tableView.getBaggageView(),"FLights");

        tableView.getBackButton().addActionListener(e->{
            EmployeeGUIController employeeGUIController = new EmployeeGUIController();
            employeeGUIController.startLogic(employee);
        });
    }
}
