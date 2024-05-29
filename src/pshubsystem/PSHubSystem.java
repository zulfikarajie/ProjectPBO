package pshubsystem;
import Controller.LoginController;
import Model.LoginModel;
import view.LoginView;

public class PSHubSystem {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel, loginView);
    }
}
