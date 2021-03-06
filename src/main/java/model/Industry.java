package model;

import java.sql.*;
import java.util.ArrayList;
import database.Database;

public class Industry implements Database{

	protected String id, name;

	public Industry(){
	}
	
	public Industry(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public void save() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO industry "
            	+ "(name, id)"
                + " VALUE (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
	}

	// PENYEBAB ERROR
	public ArrayList <String> index() {
		try {
			ArrayList <String> indices = new ArrayList <>();
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT id FROM industry";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet result = preparedStmt.executeQuery();
            result.first();
            indices.add(result.getString("id"));
            while (result.next()) {
            	indices.add(result.getString("id"));
            }
            conn.close();
            return indices;
        } catch (Exception e) {
          System.err.println("Industry error!");
          System.err.println(e.getMessage());
          return null;
        }	
	}
	// END PENYEBAB ERROR

	public Industry get(String id) {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM industry WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, id);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	String resultName = result.getString(2);
        	String resultId = result.getString(1);
        	Industry ind = new Industry(resultName, resultId);
            conn.close();
            return ind;
        } catch (Exception e) {
          	System.err.println("Industry error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}

	public void update() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "UPDATE industry SET name = ? WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("Industry error!");
          	System.err.println(e.getMessage());
        }
	}

	public void delete() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "DELETE FROM industry WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("Industry error!");
          	System.err.println(e.getMessage());
        }
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
