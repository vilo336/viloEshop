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

import Eshop.beans.products.Product;

public class ProductDao implements Serializable {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/eshop";

	static final String USER = "root";
	static final String PASS = "";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();

	public ProductDao() {

	}

	public ProductDao(List<Product> products) {
		this.products = products;
	}

	/*
	 * this metod adding a new product to the list
	 * 
	 * @params object of Product
	 */
	public void adding(Product newProduct) throws FileNotFoundException, IOException, ClassNotFoundException {

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();
			String sql = "INSERT INTO product (nazov, kategoria, cena)" + "VALUES ('" + newProduct.getNazov() + "','"
					+ newProduct.getKategoria() + "'," + newProduct.getCena() + ")";
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
		System.out.println("uspesne pridane!");

	}

	/*
	 * this metod removing product from index
	 * 
	 * @params index - index in list where the product will be deleted
	 */
	public void remove(int index) throws ClassNotFoundException, IOException {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost/eshop?user=root&password=");
			stmt = conn.createStatement();
			String sql = "DELETE FROM product WHERE id_product =" + index;

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
		return "" + products + "";
	}

	/*
	 * this metod saving the list of products to file products.txt
	 */

	public void saveToFile() throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("products.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(products);
		oos.close();
	}

	/*
	 * this metod loading the list of products from file products.txt
	 */

	public void loadFromFile() throws ClassNotFoundException, IOException {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "SELECT id_product, nazov, kategoria, cena FROM product";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\nid \t nazov \t \t kategoria\tcena\n");
			while (rs.next()) {

				String id = rs.getString("id_product");
				String name = rs.getString("nazov");
				String category = rs.getString("kategoria");
				String price = rs.getString("cena");

				System.out.println(id + " \t " + name + " \t " + category + " \t" + price);

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
