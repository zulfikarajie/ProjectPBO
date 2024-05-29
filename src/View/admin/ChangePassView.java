package view.admin;

import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ChangePassView extends JFrame{
    JLabel ltitle = new JLabel("Change Password Login");
    
    JLabel lusername = new JLabel("Username");
    JLabel fusername = new JLabel();
    
    JLabel lopassword = new JLabel("Old Password");
    JPasswordField fopassword = new JPasswordField();
    
    JLabel lnpassword = new JLabel("New Password");
    JPasswordField fnpassword = new JPasswordField();
    
    public JButton bchange = new JButton("Change Password");
    
    public ChangePassView() {
        setSize(800, 600);
        setTitle("Change Password");
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(ltitle);
        add(lusername);
        add(lopassword);
        add(lnpassword);
        add(fusername);
        add(fopassword);
        add(fnpassword);
        add(bchange);
        
        ltitle.setBounds(345, 50, 200, 50);
        
        lusername.setBounds(275, 120, 100, 30);
        fusername.setBounds(275, 150, 250, 30);
        
        lopassword.setBounds(275, 180, 100, 30);
        fopassword.setBounds(275, 210, 250, 30);
        
        lnpassword.setBounds(275, 240, 100, 30);
        fnpassword.setBounds(275, 270, 250, 30);
        
        bchange.setBounds(300, 320, 200, 30);
    }
    
    public void setUsername(String username){
        fusername.setText(username);
    }
    
    public String getOldPassword(){
        return String.valueOf(fopassword.getPassword());
    }
    
    public String getNewPassword(){
        return String.valueOf(fnpassword.getPassword());
    }
}
