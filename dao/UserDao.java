package Eshop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Eshop.beans.users.User;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	public static int id_usera;

	public UserDao(int id_usera) {
		this.id_usera = id_usera;

	}

	public int whichUser() {

		return this.id_usera;
	}

	/**
	 * @return the id_usera
	 */
	public int getId_usera() {
		return id_usera;
	}

	/**
	 * @param id_usera
	 *            the id_usera to set
	 */
	public void setId_usera(int id_usera) {
		this.id_usera = id_usera;
	}

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/eshop";

	static final String USER = "root";
	static final String PASS = "";

	private List<User> users = new ArrayList<User>();

	public UserDao() {

	}

	public UserDao(List<User> users) {
		this.users = users;
	}

	/*
	 * this metod is opening and writing to file users.txt informations about
	 * new user
	 * 
	 * @params meno, priezvisko, vek, email, login, password
	 */

	public void signingIn(String meno, String priezvisko, int vek, String email, String login, String password)
			throws IOException {

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();
			String sql = "INSERT INTO user (meno, priezvisko, vek, email, login, heslo)" + "VALUES ('" + meno + "','"
					+ priezvisko + "'," + vek + ",'" + email + "','" + login + "','" + password + "')";
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Registracia prebehla uspesne !");

	}

	/*
	 * this metod is checking if exists user with entered login and password
	 * 
	 * @params login, password, sc
	 */

	public boolean loggingIn(String login, String password, Scanner sc) throws IOException {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT id_user, login, heslo FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				setId_usera(rs.getInt("id_user"));
				String first = rs.getString("login");
				String last = rs.getString("heslo");

				if (first.equals(login) && last.equals(password)) {
					return true;
				}

			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return false;
	}

	/*
	 * this metod adding a new product to the list
	 * 
	 * @params object of User
	 */
	public void adding(User newUser) {
		users.add(newUser);

	}

	/*
	 * this metod removing user from index
	 * 
	 * @params index
	 */
	public void remove(int index) {

		users.remove(index);
	}

	/*
	 * this metod is counting number of lines in file, because we need set id to
	 * new user
	 * 
	 * @params filename
	 */

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	@Override
	public String toString() {
		return "UserDao [users=" + users + "]";
	}

}
