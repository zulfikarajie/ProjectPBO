
package Controller.admin;

import Controller.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Model.AdminModel;
import Model.LoginModel;
import view.LoginView;
import view.admin.AdminView;
import view.admin.ChangePassView;
import view.admin.DataRentView;

public class AdminController {
    AdminModel adminModel;
    AdminView adminView;
    String primary = "";

    public AdminController(AdminModel adminModel, AdminView adminView) {
        this.adminModel = adminModel;
        this.adminView = adminView;
        
        if(adminModel.getUserData()>0){
            String dataUser[][] = adminModel.readAllUsers();
            adminView.table.setModel((new JTable(dataUser, adminView.getHeader())).getModel());
        }else{
            JOptionPane.showMessageDialog(null, "No Data");
        }
        
        adminView.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = adminView.table.getSelectedRow();
                String username = adminView.table.getValueAt(row, 0).toString();
                String roles = adminView.table.getValueAt(row, 2).toString();
                primary = username;
                
                adminView.setUsername(username);
                
                if(roles.equals("admin")){
                    adminView.cbRole.setSelectedIndex(0);
                }else{
                    adminView.cbRole.setSelectedIndex(1);
                }
            }
        });
        
        adminView.bLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginView LoginView = new LoginView();
                LoginModel LoginModel = new LoginModel();
                LoginController LoginController = new LoginController(LoginModel, LoginView);
                adminView.dispose();
                
                JOptionPane.showMessageDialog(null, "Logout Success");
            }
        });
        
        adminView.bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = adminView.getUsername();
                String role = adminView.getRole();
                
                if(adminView.getUsername().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    adminModel.insertUser(username, role);
                
                    primary = "";
                    String dataUser[][] = adminModel.readAllUsers();
                    adminView.table.setModel((new JTable(dataUser, adminView.getHeader())).getModel());
                }        
            }
        });
        
        adminView.bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                adminView.setUsername("");
                primary = "";
            }
        });
        
        adminView.bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                String username = adminView.getUsername();
                String role = adminView.getRole();
                
                if(primary.equals("")){
                    primary = username;
                }
                
                if(adminView.getUsername().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    adminModel.updateUser(username, role, primary); 
                    primary = "";
                    String dataUser[][] = adminModel.readAllUsers();
                    adminView.table.setModel((new JTable(dataUser, adminView.getHeader())).getModel());
                }
            }
        });
        
        adminView.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = adminView.getUsername();
                
                if(adminView.getUsername().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    adminModel.deleteUser(username);
                    primary = "";
                    String dataUser[][] = adminModel.readAllUsers();
                    adminView.table.setModel((new JTable(dataUser, adminView.getHeader())).getModel());
                }
                
            }
        });
        
        adminView.bPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = adminView.getUsername();
                
                if(adminView.getUsername().equals("")){
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                }else{
                    if(!adminModel.checkUsername(username)){
                        JOptionPane.showMessageDialog(null, "Username not Found");
                    }else{
                        ChangePassView changePassView = new ChangePassView();
                        ChangePassController ChangePassController = new ChangePassController(changePassView, adminModel, username, AdminController.this);
                    }
                }
            }
        });
        
        adminView.bViewData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DataRentView dataRentView = new DataRentView();
                DataRentController dataRentController = new DataRentController(adminModel, dataRentView);
            }
        });
    }
    
    public void updateTable(){
        if(adminModel.getUserData()>0){
            primary = "";
            String dataUser[][] = adminModel.readAllUsers();
            adminView.table.setModel((new JTable(dataUser, adminView.getHeader())).getModel());
        }else{
            JOptionPane.showMessageDialog(null, "No Data");
        }
    }
    
}
