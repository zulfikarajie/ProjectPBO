package view.karyawan;

import java.awt.Font;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RentPageView extends JFrame {
    int col1 = 165;
    int col2 = 400;
    
    String[] vType = {"PS5", "PS4"};
    JLabel lTotal = new JLabel("Room Remain");
    JLabel lKaryawan = new JLabel("Karyawan");
    JLabel lOrderId = new JLabel("ID Transaksi");
    JLabel lNamaPelanggan = new JLabel("Nama Pelanggan");
    JLabel lJenisRental = new JLabel("Jenis PS");
    JLabel lHarga = new JLabel("Total Price");
    JLabel lStart = new JLabel("Waktu Mulai");
    JLabel lFinish = new JLabel("Waktu Selesai");
    JLabel lStarttime = new JLabel("");
    JLabel lFinishtime = new JLabel("");
    
    JTextField tfOrderId = new JTextField();
    JTextField tfNamaPelanggan = new JTextField();
    JTextField tfHarga = new JTextField("Rp100.000");
    public JComboBox<String> cbVtype = new JComboBox<>(vType);
    
    public JButton bLogout = new JButton("Logout");
    public JButton bTotal = new JButton("");
    public JButton bIn = new JButton("Start");
    public JButton bOut = new JButton("Finish");
    public JButton bClear = new JButton("Clear");
    public JButton bCheck = new JButton("Check");
    public JButton bAuto = new JButton("Auto");
        
    public RentPageView() {
        Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 30);
        
        setSize(550, 600);
        setTitle("Playstation HUB Rent Menu");
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(lTotal);
        lTotal.setBounds(20, 20, 100, 20);
        add(bTotal);
        bTotal.setBounds(20, 50, 90, 50);
        bTotal.setEnabled(false);
        bTotal.setFont(f2);
        
        add(lStart);
        lStart.setBounds(20, 120, 100, 20);
        
        add(lStarttime);
        lStarttime.setBounds(20, 140, 150, 20);
        
        add(lFinish);
        lFinish.setBounds(20, 180, 100, 20);
        
        add(lFinishtime);
        lFinishtime.setBounds(20, 200, 150, 20);
        
        add(bLogout);
        bLogout.setBounds(col2, 20, 100, 30);
        
        add(bCheck);
        bCheck.setBounds(col2, 240, 100, 30);
        
        add(bClear);
        bClear.setBounds(col2, 280, 100, 30);
        
        add(lKaryawan);
        lKaryawan.setBounds(col1, 20, 200, 20);
        
        add(lOrderId);
        lOrderId.setBounds(col1, 60, 200, 20);
        
        add(tfOrderId);
        tfOrderId.setBounds(col1, 80, 200, 30);
        
        add(bAuto);
        bAuto.setBounds(col2, 80, 100, 30);
        
        add(lNamaPelanggan);
        lNamaPelanggan.setBounds(col1, 120, 200, 20);
        
        add(tfNamaPelanggan);
        tfNamaPelanggan.setBounds(col1, 140, 200, 30);
        
        add(lJenisRental);
        lJenisRental.setBounds(col1, 180, 200, 20);
        
        add(cbVtype);
        cbVtype.setBounds(col1, 200, 200, 30);
        
        add(bIn);
        bIn.setBounds(col1, 240, 100, 30);
        
        add(bOut);
        bOut.setBounds(col1, 280, 100, 30);
        
        add(lHarga);
        lHarga.setBounds(col1, 320, 200, 20);
        
        add(tfHarga);
        tfHarga.setBounds(col1, 340, 200, 30);
        tfHarga.setEditable(false);
    }
    
    public void setUsername(String username){
        lKaryawan.setText(username);
    }
    
    public void setPelanggan(String nama_pelanggan){
        tfNamaPelanggan.setText(nama_pelanggan);
    }
    
    public void setPrice(String harga){
        tfHarga.setText(harga);
    }
    
    public void setId(String order_id){
        tfOrderId.setText(order_id);
    }
    
    public void setIn(String start){
        lStarttime.setText(start);
    }
    
    public void setOut(String finish){
        lFinishtime.setText(finish);
    }
    
    public void setIdTrans(String order_id){
        tfOrderId.setText(order_id);
    }
    
    public void setJenisRental(String jenis_rental){
        if(jenis_rental.equals("ps5")){
            cbVtype.setSelectedIndex(0);
        }else{
            cbVtype.setSelectedIndex(1);
        }
    }
    
    public String getOrderId(){
        return tfOrderId.getText();
    }
    
    public String getPelanggan(){
        return tfNamaPelanggan.getText();
    }
    
    public String getJenisRental(){
        return cbVtype.getItemAt(cbVtype.getSelectedIndex());
    }
    
    
    public void clear(){
        tfNamaPelanggan.setText("");
        tfOrderId.setText("");
        tfHarga.setText("");
        lStarttime.setText("");
        lFinishtime.setText("");
        tfOrderId.setEditable(true);
        tfNamaPelanggan.setEditable(true);
        cbVtype.setEnabled(true);
        bIn.setEnabled(true);
        priceDisable();
    }
    
    public void setRemaining(String remain){
        bTotal.setText(remain);
    }
    
    public void priceVisible(){
        lHarga.setVisible(true);
        tfHarga.setVisible(true);
    }
    
    public void priceDisable(){
        lHarga.setVisible(false);
        tfHarga.setVisible(false);
    }
    
    public void allDisabled(){
        tfOrderId.setEditable(false);
        tfNamaPelanggan.setEditable(false);
        cbVtype.setEnabled(false);
        bIn.setEnabled(false);
    }
    
}
