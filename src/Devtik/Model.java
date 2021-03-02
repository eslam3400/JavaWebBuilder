package Devtik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {
    Connection c = null;
    Statement stmt = null;

    Model(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("JDBC:sqlite:db.db");
//            System.out.println("Connected To The DataBase!");
        } catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void readTest(){
        try{
            this.stmt = c.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM test");
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                System.out.println("id: "+id+" name: "+name);
            }
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public String auth(String _username,String _password){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT username FROM users WHERE username = '" +_username+"' AND password = '"+_password+"'");
            String username = null;
            while (r.next()){
                username = r.getString("username");
            }
            return username;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public String getContainerStart(){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT start FROM container");
            String content = null;
            while (r.next()){
                content = r.getString("start");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public String getContainerEnd(){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT end FROM container");
            String content = null;
            while (r.next()){
                content = r.getString("end");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public String[] getComponentData(int _id){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM components WHERE id = "+_id);
            String[] content = new String[5];
            while (r.next()){
                content[0] = r.getString("name");
                content[1] = r.getString("html");
                content[2] = r.getString("css");
                content[3] = r.getString("js");
                content[4] = r.getString("template");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public String getHTMLTemplate(int _id){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT content FROM html WHERE id = "+_id);
            String content = null;
            while (r.next()){
                content = r.getString("content");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "";
    }

    public String getCssTemplate(int _id){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT content FROM css WHERE id = "+_id);
            String content = null;
            while (r.next()){
                content = r.getString("content");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "";
    }

    public String getJsTemplate(int _id){
        try{
            this.stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("SELECT content FROM js WHERE id = "+_id);
            String content = null;
            while (r.next()){
                content = r.getString("content");
            }
            return content;
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "";
    }

    public void closeConnection(){
        try{
            c.close();
            System.out.println("Database connection closed!");
        } catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }
}
