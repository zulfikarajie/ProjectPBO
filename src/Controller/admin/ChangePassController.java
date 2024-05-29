package Controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.AdminModel;
import view.admin.ChangePassView;

public class ChangePassController {
    ChangePassView changePassView;
    AdminModel adminModel;

    public ChangePassController(ChangePassView changePassView, AdminModel adminModel, String username, AdminController ac) {
        this.changePassView = changePassView;
        this.adminModel = adminModel;
        
        changePassView.setUsername(username);
        
        changePassView.bchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String oldPass = changePassView.getOldPassword();
                String newPass = changePassView.getNewPassword();
                
                if(adminModel.changePassword(oldPass, newPass, username)){
                    changePassView.dispose();
                    ac.updateTable();
                }
            }
        });
        
    }
}
