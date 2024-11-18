package com.user.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.PreparedQuery;
import com.user.model.educationManagement;

public class educationManagementDAO {
	
	private String jdbcURL="jdbc:mysql://localhost3006/userappdb";
	private String jdbcUserName="root";
	private String jdbcPassword="#%Satya919293";
	
	private static final String INSERT_USER_SQL="INSERT INTO User" + "(uname,email,country,passwd)" + "VALUES(?,?,?,?)";
	private static final String SELECT_USER_BY_ID="SELECT * FROM users where id=?;";
	private static final String SELECT_ALL_USERS="select * from users;";
	private static final String DELETE_USERS_SQL="delete from users where id=?;";
	private static final String UPDATE_USERS_SQL="update user set name=?, email=?, country=?, password=? where id=?;";
	
	
	public educationManagementDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() {
		Connection connection=null;
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
		
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return connection;
	}
	
	public void insertUser(educationManagement user) {
		educationManagementDAO dao  = new educationManagementDAO();
		try (Connection connection = dao.getConnection()){
			PreparedStatement preparedStatement = connection.prepareCall(INSERT_USER_SQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(4, user.getPassword());
			
			preparedStatement.executeUpdate();
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public educationManagement selectUser(int id) {
		
		educationManagement user=new educationManagement();
		educationManagement dao=new educationManagement();
		
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
				
			{
			user.setId(id);	
			user.setName(resultSet.getString("uname"));
			user.setEmail(resultSet.getString("email"));
			user.setCountry(resultSet.getString("country"));
			user.setPassword(resultSet.getString("passwd"));
			}
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return user;
		}
		
	public List<educationManagement> selectAllUsers(){
		List<educationManagement> users=new ArrayList<educationManagement>();
		educationManagementDAO dao=new educationManagementDAO();
		try(Connection connection=dao.getConnection()){
			
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String uname=resultSet.getString("uname");
				String email=resultSet.getString("email");
				String country=resultSet.getString("country");
				String password=resultSet.getString("passwd");
				
				users.add(new educationManagement(id,uname,email,country,password));
	
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean deleteUser(int id)
	{
		boolean status=false;
		educationManagementDAO dao=new educationManagementDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USERS_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean updateUser(educationManagement user)
	{
		boolean status=false;
		educationManagementDAO dao=new educationManagementDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USERS_SQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(4, user.getPassword());
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}

		
	}

