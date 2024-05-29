package Model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class PSHubRentDataModel extends Connector implements Hitung{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public PSHubRentDataModel() {
        
    }
    
    public String readRemaining(){
        String data = "";
        try {
            String query = "SELECT * FROM `setting`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data = resultSet.getString("remain");
            }
            statement.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return data;
        }
    }
    
    public int checkId(){
        int data = 0;
        try {
            String query = "SELECT * FROM `order_recap`";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data = resultSet.getInt("order_id");
            }
            statement.close();
            
            return data;
        } catch (Exception e) {
            
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    
    public boolean insertRent(String orderId, String pelanggan, String jenisRental, String user_id){
        Date dateNow = new Date();
        String types;
        if(jenisRental.equals("PS5")){
            types = "PS5";
        }else{
            types = "PS4";
        }
        try {
            String query = "INSERT INTO `order_recap` "
                    + "(`order_id`, `nama_pelanggan`, `jenis_rental`, `waktu_mulai`, `user_id`) "
                    + "VALUES "
                    + "(" + orderId+ ""
                    + ",'"+ pelanggan + "'"
                    + ",'" + jenisRental + "'"
                    + ",'" + formatter.format(dateNow) + "'"
                    + ",'" + user_id + "')";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            
            int remain = Integer.parseInt(readRemaining());
            remain--;
            
            updateRemain(String.valueOf(remain));
            
            JOptionPane.showMessageDialog(null, "Input Success");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed : " + e.getMessage());
            return false;
        }
    }
    
    public void updateRemain(String remain){
        try {
            String query = "UPDATE `setting` "
                    + "SET "
                    + "`remain`=" + remain + "";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public boolean checkInput(String id){
        try {
            int totalData = 0;
            String query = "SELECT * FROM `order_recap` WHERE `order_id`='" + id + "'" 
                    + " AND `harga`=0";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                totalData++;
            }
            statement.close();
            
            if(totalData>0){
                return true;
            }
            
            JOptionPane.showMessageDialog(null, "No Data");
            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
    
    public String[] readRentData(String id){
        String[] data = new String[5];
        try {
            String query = "SELECT * FROM `order_recap` WHERE `order_id`=" + id;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                data[0] = resultSet.getString("order_id");
                data[1] = resultSet.getString("nama_pelanggan");
                data[2] = resultSet.getString("jenis_rental");
                data[3] = resultSet.getString("waktu_mulai");
                data[4] = resultSet.getString("harga");
            }
            statement.close();

            return data;
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Check Failed");
            return null;
        }
    }
    
    public void outRent(String id){
        try {
            String[] data = readRentData(id);
            Date d1 = formatter.parse(data[3]);
            Date d2 = new Date();
            
            long time = d2.getTime() - d1.getTime();
            long diffHours = time / (60 * 60 * 1000);
            
            long total = totalPrice(diffHours, data[2]);
       
            updateRent(total, formatter.format(d2), id);
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public void updateRent(long total, String format, String id){
        try {
            String query = "UPDATE `order_recap` "
                    + "SET "
                    + "`harga`='" + total + "', "
                    + "`waktu_selesai`='" + format + "' "
                    + "WHERE `order_id`=" + id;
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            
            int remain = Integer.parseInt(readRemaining());
            remain++;
            
            updateRemain(String.valueOf(remain));
            
            JOptionPane.showMessageDialog(null, "Update Success");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public String[] getTimeAndPrice(String id){
        String[] data = new String[2];
        try {
            String query = "SELECT * FROM `order_recap` WHERE `order_id`=" + id;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                data[0] = resultSet.getString("harga");
                data[1] = resultSet.getString("waktu_selesai");
            }
            statement.close();

            return data;
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed");
            return null;
        }
    }
    @Override
    public long totalPrice(long time, String jenisRental) {
        long first=0, add=0;
        
        if(jenisRental.equals("PS5")){
            first = 10000;
            add = 10000;
        }else{
            first = 5000;
            add = 5000;
        }
        
        if(time<=1){
            return first;
        }
        
        if(time>1){
            return first + (time*add);
        }
        
        if(time >= 24){
            return first + (24*add);
        }
        
        return 0;
    }
}
