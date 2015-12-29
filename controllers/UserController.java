package Eshop.controllers;

import java.io.IOException;
import java.util.Scanner;
import Eshop.beans.users.User;
import Eshop.dao.UserDao;

public class UserController {

	/*
	 * this metod creates a new objects from class ProductDao and from class
	 * Scanner to read a new attributes from console metod calls metod adding()
	 * from class OrderDao
	 */
	
	public boolean registration() {
		UserDao userD = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("login:");
		String login = sc.nextLine();
		System.out.println("heslo:");
		String password = sc.nextLine();

		return true;
	}

	public void add() throws IOException {
		UserDao userD = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Zadaj login pouzivatela");
		String login = sc.nextLine();
		System.out.println("Zadaj heslo pouzivatela");
		String password = sc.nextLine();
		System.out.println("Zadaj meno pouzivatela");
		String meno = sc.nextLine();
		System.out.println("Zadaj priezvisko pouzivatela");
		String priezvisko = sc.nextLine();
		System.out.println("Zadaj email uzivatela");
		String email = sc.nextLine();
		System.out.println("Zadaj vek uzivatela");
		int vek = sc.nextInt();

		User newUser = new User(1, meno, priezvisko, vek, email, login, password);
		userD.adding(newUser);
		userD.signingIn(meno, priezvisko, vek, email, login, password);
		// sc.close();
	}

	public int logOn() throws IOException {
		UserDao userD = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Zadaj login ");
		String login = sc.nextLine();
		System.out.println("Zadaj heslo ");
		String password = sc.nextLine();
		if (userD.loggingIn(login, password, sc))
			return 2;
		//System.out.println(login+""+password);
		if (login.equals("admin") && password.equals("admin")) { UserDao.id_usera = 0; return 1 ;}
		
		
		
		return 0;

	}
	public boolean isAdmin(String login, String password){
		
		
		return false;
	}
	

	/*
	 * this metod calls metod RemoveAtIndex() from class OrderDao
	 * 
	 * @params index - index in list where the Record will be deleted
	 */
	public void removeAtIndex(int index) {

		UserDao userD = new UserDao();
		userD.remove(index);

	}

	/*
	 * this metod calls metod toString() from class OrderDao
	 */
	public void listing() {
		UserDao userD = new UserDao();
		userD.toString();
	}

}
