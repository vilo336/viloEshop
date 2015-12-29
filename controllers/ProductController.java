package Eshop.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import Eshop.beans.products.Product;
import Eshop.dao.ProductDao;

public class ProductController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * this metod creates a new objects from class ProductDao and from class
	 * Scanner to read a new attributes from console metod calls metod adding()
	 * from class ProductDao
	 */
	public void add() throws FileNotFoundException, IOException, ClassNotFoundException {
		ProductDao productD = new ProductDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Zadaj nazov produktu");
		String nazov = sc.nextLine();
		System.out.println("Zadaj kategoriu produktu");
		String kategoria = sc.nextLine();

		System.out.println("Zadaj cenu produktu");
		String cema = sc.nextLine();

		double cena = Double.parseDouble(cema.replace(",", "."));

		Product newProduct = new Product(nazov, kategoria, cena);
		productD.adding(newProduct);
	}

	/*
	 * this metod calls metod remove() from class ProductDao
	 * 
	 * @params index - index in list where the product will be deleted
	 */
	public void removeAtIndex(int index) throws ClassNotFoundException, IOException {

		ProductDao productD = new ProductDao();
		productD.remove(index);

	}

	/*
	 * this metod calls metod toString() from class ProductDao
	 */
	public void listing() throws ClassNotFoundException, IOException {
		ProductDao productD = new ProductDao();
		productD.loadFromFile();
		// System.out.println(productD.toString());
	}

}
