package Eshop.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Eshop.beans.orders.Order;

public class OrderDao implements Serializable {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/eshop";

	static final String USER = "root";
	static final String PASS = "";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new ArrayList<Order>();

	public OrderDao() {
	}

	public OrderDao(List<Order> orders) {
		this.orders = orders;
	}

	/*
	 * this metod adding a new order to the list
	 * 
	 * @params object of Order
	 */
	public void adding(Order newOrder, int idecko) throws ClassNotFoundException, IOException {
		UserDao user = new UserDao();
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();

			String sql1 = "SELECT cena FROM product WHERE id_product =" + newOrder.getId_product();
			ResultSet rs = stmt.executeQuery(sql1);
			double cena = 0;
			while (rs.next()) {
				cena = rs.getDouble("cena");
			}

			String sql = "INSERT INTO `order`(`id_user`, `id_product`, `pocet_kusov`, `cena`)" + " VALUES (" + idecko
					+ "," + newOrder.getId_product() + "," + newOrder.getPocet_kusov() + ","
					+ newOrder.getPocet_kusov() * cena + ");";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");

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

		System.out.println("Pridane");

	}

	/*
	 * this metod is removing order from file order.txt
	 * 
	 * @params index - index in list where the order will be deleted
	 */

	public void remove(int index) throws ClassNotFoundException, IOException {

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();
			String sql = "DELETE FROM order WHERE id_order =" + index;

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
		System.out.println("uspesne vymazane!");

	}

	@Override
	public String toString() {
		return "Orders [orders=" + orders + "]";
	}

	/*
	 * this metod saving the list of orders to file orders.txt
	 */

	public void saveToFile() throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("orders.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(orders);
		oos.close();
	}

	/*
	 * this metod loading the list of orders from file orders.txt
	 */

	public void loadFromFile() throws ClassNotFoundException, IOException {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT `id_order`, `id_user`, `id_product`, `pocet_kusov`, `cena` FROM `order`";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\nid_obj\tid_user\tid_product\tkusy\tcena");
			while (rs.next()) {

				int idOrder = rs.getInt("id_order");
				int idUser = rs.getInt("id_user");
				int idProduct = rs.getInt("id_product");
				int pocetKusov = rs.getInt("pocet_kusov");
				double cena = rs.getDouble("cena");

				System.out.println(idOrder + "\t" + idUser + "\t" + idProduct + "\t\t" + pocetKusov + "\t" + cena);
			}
			System.out.println("\n");
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

	}

}
