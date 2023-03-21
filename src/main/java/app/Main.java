package app;

import app.controller.gui.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Properties;


@SpringBootApplication
public class Main {

    private static final String APPLICATION_CONFIGURATION_FILE = "app.configuration.properties";

    public static void main(String[] args) {

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(APPLICATION_CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            properties.load(input);

            // Decide app mode from file
            String mode = properties.getProperty("mode");

            if (mode.equals("web")) {
                SpringApplication.run(Main.class, args);
            } else {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        } catch (Exception ex) {
            System.out.println("Error at starting application...");
            ex.printStackTrace();
        }

    }
}
