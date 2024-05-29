package view.admin;

import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
public class DataRentView extends JFrame{
    JLabel lTitle = new JLabel("View Rent Data");

    public JButton bExit = new JButton("Exit");
    public JTable table;
    DefaultTableModel dtm;
    JScrollPane scrollPane;
    Object[] header = {"order_id", "nama_pelanggan", "jenis_rental", "harga",
                       "waktu_mulai", "waktu_selesai", "user_id"};
    
    public DataRentView(){
        dtm = new DefaultTableModel(header, 0);
        table = new JTable(dtm);
        scrollPane = new JScrollPane(table);

        setSize(700, 450);
        setTitle("Playstation Hub Rent Data");
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(lTitle);
        lTitle.setBounds(300, 20, 200, 20);

        add(scrollPane);
        scrollPane.setBounds(10, 40, 665, 330);

        add(bExit);
        bExit.setBounds(300, 380, 100, 20);
    }

    public Object[] getHeader() {
        return header;
    }
    
    
}
