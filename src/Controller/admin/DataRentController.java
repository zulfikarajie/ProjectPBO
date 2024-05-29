package Controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Model.AdminModel;
import view.admin.DataRentView;


public class DataRentController {
    AdminModel adminModel;
    DataRentView dataRentView;

    public DataRentController(AdminModel adminModel, DataRentView dataRentView) {
        this.adminModel = adminModel;
        this.dataRentView = dataRentView;
        
        if(adminModel.getOrderRecapData()>0){
            String dataUser[][] = adminModel.readAllRecap();
            dataRentView.table.setModel((
                    new JTable(dataUser, dataRentView.getHeader())).getModel()
            );
            
        }else{
            JOptionPane.showMessageDialog(null, "No Data");
        }
        
        dataRentView.bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dataRentView.dispose();
            }
        });
    }
    
    
}
