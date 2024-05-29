package Model;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class AdminModel extends Connector {

    public AdminModel() {
    }
    
    public int getOrderRecapData(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `order_recap`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    
    public String[][] readAllRecap(){
        String data[][] = new String[getOrderRecapData()][7];
        try {
            int indexData = 0;  
            String query = "SELECT * FROM `order_recap`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data[indexData][0] = resultSet.getString("order_id");
                data[indexData][1] = resultSet.getString("nama_pelanggan");
                data[indexData][2] = resultSet.getString("jenis_rental");
                data[indexData][3] = resultSet.getString("harga");
                data[indexData][4] = resultSet.getString("waktu_mulai");
                data[indexData][5] = resultSet.getString("waktu_selesai");
                data[indexData][6] = resultSet.getString("user_id");
                indexData++;
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
    }
    
    public int getUserData(){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `users`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            return totalData;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    
    public boolean checkUsername(String username){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `users` WHERE `username`='" + username + "'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            
            if(totalData>0){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
    
    public String[][] readAllUsers(){
        String data[][] = new String[getUserData()][3];
        try {
            int indexData = 0;  
            String query = "SELECT * FROM `users`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data[indexData][0] = resultSet.getString("username");
                data[indexData][1] = resultSet.getString("password");
                data[indexData][2] = resultSet.getString("role");
                indexData++;
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
    }
    
    public void insertUser(String username, String role){
        String roles;
        if(role.equals("Admin")){
            roles = "admin";
        }else{
            roles = "employee";
        }
        try {
            String query = "INSERT INTO `users` (`username`, `password`, `role`) "
                    + "VALUES "
                    + "('" + username + "','123','" + roles + "')";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            JOptionPane.showMessageDialog(null, "Input Success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
        }
    }
    
    public void updateUser(String username, String role, String primary){
        if(!checkUsername(primary)){
            JOptionPane.showMessageDialog(null, "Username not Found");
            return;
        }
        
        String roles;
        if(role.equals("Admin")){
            roles = "admin";
        }else{
            roles = "employee";
        }
        try {
            String query = "UPDATE `users` "
                    + "SET "
                    + "`username`='" + username + "', "
                    + "`role`='" + roles + "' "
                    + "WHERE `username`='" + primary + "'";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            JOptionPane.showMessageDialog(null, "Update Success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
        }
    }
    
    public void deleteUser(String username){
        if(!checkUsername(username)){
            JOptionPane.showMessageDialog(null, "Username not Found");
            return;
        }
        
        try {
            String query = "DELETE FROM `users` WHERE `username`='" + username + "'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            
            JOptionPane.showMessageDialog(null, "Delete Success");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Delete Failed");
        }
    }
    
    public boolean checkOldPass(String username, String password){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `users` WHERE "
                    + "`username`='" + username + "' AND "
                    + "`password`='" + password + "'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            
            if(totalData>0){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
    
    public boolean changePassword(String oldPass, String newPass, String username){
        try {
            if(checkOldPass(username, oldPass)){
                String query = "UPDATE `users` "
                    + "SET "
                    + "`password`='" + newPass + "' "
                    + "WHERE `username`='" + username+ "'";
            
                statement = connection.createStatement();
                statement.executeUpdate(query);

                statement.close();
                JOptionPane.showMessageDialog(null, "Change Success");

                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Wrong Old Password");
            }
            
            return false;           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
            return false;
        }
    }
}
