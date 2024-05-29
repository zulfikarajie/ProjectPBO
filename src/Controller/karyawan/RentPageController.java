package Controller.karyawan;

import Controller.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import Model.LoginModel;
import Model.PSHubRentDataModel;
import view.LoginView;
import view.karyawan.RentPageView;

public class RentPageController {
    PSHubRentDataModel pshubRentDataModel;
    RentPageView rentPageView;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;
    
    public RentPageController(PSHubRentDataModel pshubRentDataModel, RentPageView rentPageView, String username) {
        this.pshubRentDataModel = pshubRentDataModel;
        this.rentPageView = rentPageView;
        
        rentPageView.setUsername(username);
        rentPageView.priceDisable();
        rentPageView.bOut.setEnabled(false);
        rentPageView.setRemaining(pshubRentDataModel.readRemaining());
        
        rentPageView.bLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginView loginView = new LoginView();
                LoginModel loginModel = new LoginModel();
                LoginController loginController = new LoginController(loginModel, loginView);
                rentPageView.dispose();
                
                JOptionPane.showMessageDialog(null, "Logout Success");
            }
        });
        
        rentPageView.bCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String[] data;
                String OrderId = rentPageView.getOrderId();
                rentPageView.bOut.setEnabled(true);
                if (OrderId.equals("")) {
                    JOptionPane.showMessageDialog(null, "Cannot be empty");
                } else {
                    if (pshubRentDataModel.checkInput(OrderId)) {
                        data = pshubRentDataModel.readRentData(OrderId);
                        rentPageView.setId(data[0]);
                        rentPageView.setPelanggan(data[1]);
                        rentPageView.setJenisRental(data[2]);
                        rentPageView.setIn(data[3]);
                        rentPageView.allDisabled();
                        JOptionPane.showMessageDialog(null, "Read Success");
                    }
                }
            }
        });
        
        rentPageView.bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                rentPageView.clear();
                rentPageView.bOut.setEnabled(false);
                rentPageView.setRemaining(pshubRentDataModel.readRemaining());
            }
        });
        
        rentPageView.bIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // input
                int check = Integer.parseInt(pshubRentDataModel.readRemaining());
                
                if (check <= 0) {
                    JOptionPane.showMessageDialog(null, "Room Full");
                } else {
                    String id = rentPageView.getOrderId();
                    String pelanggan = rentPageView.getPelanggan();
                    String jenisrental = rentPageView.getJenisRental();

                    if (id.equals("") || pelanggan.equals("")) {
                        JOptionPane.showMessageDialog(null, "Cannot be Empty");
                    } else {
                        if (pshubRentDataModel.insertRent(id, pelanggan, jenisrental, username)) {
                            date = new Date();
                            rentPageView.setRemaining(pshubRentDataModel.readRemaining());
                            rentPageView.setIn(formatter.format(date));
                        }
                    }
                }
            }
        });
        
        rentPageView.bAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int id = pshubRentDataModel.checkId();
                id++;
                rentPageView.setIdTrans(String.valueOf(id));
            }
        });
        
        rentPageView.bOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String id = rentPageView.getOrderId();
                System.out.println("id " + id);
                pshubRentDataModel.outRent(id);
                String[] data = pshubRentDataModel.getTimeAndPrice(id);
                rentPageView.priceVisible();
                rentPageView.setPrice("Rp" + data[0]);
                rentPageView.setOut(data[1]);
                rentPageView.setRemaining(pshubRentDataModel.readRemaining());
                rentPageView.bOut.setEnabled(false);
            }
        });
    }
}
