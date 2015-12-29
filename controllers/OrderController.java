package Eshop.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import Eshop.beans.orders.Order;
import Eshop.dao.OrderDao;
import Eshop.dao.ProductDao;
import Eshop.dao.UserDao;

public class OrderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * this metod creates a new objects from class OrderDao and from class
	 * Scanner to read a new attributes from console metod calls metod adding()
	 * from class OrderDao
	 */
	public boolean add() throws ClassNotFoundException, IOException {

		int idecko = UserDao.id_usera;
		OrderDao orderD = new OrderDao();

		Scanner sc = new Scanner(System.in);
		System.out.println("Zadaj id produktu");
		int id_product = sc.nextInt();
		System.out.println("Zadaj pocet kusov");
		int pocet_kusov = sc.nextInt();
		Order newOrder = new Order(id_product, pocet_kusov);
		orderD.adding(newOrder, idecko);
		return true;
	}

	/*
	 * this metod is calling metod remove() from class OrderDao
	 * 
	 * @params index - index in list where the order will be deleted
	 */

	public void removeAtIndex(int index) throws ClassNotFoundException, IOException {
		OrderDao orderD = new OrderDao();
		orderD.remove(index);
	}

	/*
	 * this metod calls metod toString() from class OrderDao
	 */
	public void listing() throws ClassNotFoundException, IOException {
		OrderDao orderD = new OrderDao();
		orderD.loadFromFile();
		// System.out.println(orderD.toString());
	}

}
