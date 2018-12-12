package model;

import java.sql.*;
import database.Database;

public class Category implements Database{
	protected long id;
	protected String name;
	//id from Database (using autoIncrement)
	
	public Category() {
	}
	public Category(String name) {
		 this.name = name;
	}

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void save() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO category "
            	+ "(name)"
                + " VALUE (?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
	}

	public Category get(long id) {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM category WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setLong (1, id);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	long resultId = result.getLong(1);
        	String resultName = result.getString(2);
        	Category cat = new Category(resultId, resultName);
            conn.close();
            return cat;
        } catch (Exception e) {
          	System.err.println("Category error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}

	public Category get(String name) {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM category WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	long resultId = result.getLong(1);
        	String resultName = result.getString(2);
        	Category cat = new Category(resultId, resultName);
            conn.close();
            return cat;
        } catch (Exception e) {
          	System.err.println("Category error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}

	public void update() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "UPDATE category SET name = ? WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setLong (2, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("Category error!");
          	System.err.println(e.getMessage());
        }
	}

	public void delete() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "DELETE FROM category WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setLong (1, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("Category error!");
          	System.err.println(e.getMessage());
        }
	}
}
