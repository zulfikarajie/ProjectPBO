package Controller;

import Controller.admin.AdminController;
import Controller.karyawan.RentPageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.AdminModel;
import Model.LoginModel;
import Model.PSHubRentDataModel;
import view.LoginView;
import view.admin.AdminView;
import view.karyawan.RentPageView;

public class LoginController {
    LoginModel loginModel;
    LoginView loginView;

    public LoginController(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        
        loginView.blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = loginView.getUsername();
                String password = loginView.getPassword();
                
                if(loginModel.loginHandler(username, password)){
                    JOptionPane.showMessageDialog(null, "Login Success");
                    if(loginModel.isAdmin(username, password)){
                        AdminView adminView = new AdminView();
                        AdminModel adminModel = new AdminModel();
                        AdminController adminController = new AdminController(adminModel, adminView);
                    }else{
                        PSHubRentDataModel pshubRentDataModel = new PSHubRentDataModel();
                        RentPageView rentPageView = new RentPageView();
                        RentPageController rentPageController = new RentPageController(pshubRentDataModel, rentPageView, username);
                    }
                    loginView.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            }
        });
        
    }
    
    
}
