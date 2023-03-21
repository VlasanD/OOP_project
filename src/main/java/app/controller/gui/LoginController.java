package app.controller.gui;

import app.model.Employee;
import app.service.EmployeeService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView loginView;

    private EmployeeService employeeService = ServiceSinglePointAccess.getEmployeeService();

    public void startLogic() {
        loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getLoginPanel(), "Login");

        loginView.getLogInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = loginView.getTextFieldName().getText();
                String password = new String(loginView.getPasswordField().getPassword());

                Employee employee = employeeService.login(name, password);
                if (employee != null) {
                    EmployeeGUIController employeeGUIController = new EmployeeGUIController();
                    employeeGUIController.startLogic(employee);
                } else {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid username or password");
                }
            }
        });
    }
}
