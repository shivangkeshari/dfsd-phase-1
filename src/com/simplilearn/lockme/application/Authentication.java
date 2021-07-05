package com.simplilearn.lockme.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.simplilearn.lockme.model.UserCredentials;
import com.simplilearn.lockme.model.Users;

public class Authentication {
	//input data
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	//output data 
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	//model to store data.
	private static Users users;
	private static UserCredentials userCredentials;
	
	
	public static void main(String[] args) {
		initApp();
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		System.out.println("3 . Exit ");
		int option = keyboard.nextInt();
		int exit = 0;
		while (true)
		{
			switch(option) {
				case 1 :
					registerUser();
					break;
				case 2 :
					loginUser();
					break;
				case 3 :
					exit = 1;
					break;
				default :
					System.out.println("Please select 1 Or 2 Or 3");
					break;
			}
			if (exit==1) {
				break;
			}
			else {
				initApp();
				welcomeScreen();
				System.out.println("1 . Registration ");
				System.out.println("2 . Login ");
				System.out.println("3 . Exit ");
				option = keyboard.nextInt();
			}
		}

		keyboard.close();
		input.close();
	}
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORE CREDENTIALS ");
		System.out.println("3 . DELETE CREDENTIALS ");
		System.out.println("4 . Go Back ");
		int option = keyboard.nextInt();
		int delf=0;
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			case 3 :
				deleteCredentials(inpUsername);
//				lockerInput.close();
//				lockerOutput.close();
//				File myObj = new File("locker-file.txt");
//				System.out.println(myObj.delete());
//				//myObj.delete();
//
//				File oldFile = new File("locker-file-temp.txt");
//				File newFile = new File("locker-file.txt");
//
//				//oldFile.renameTo(newFile);
//				System.out.println(oldFile.renameTo(newFile));
				delf=1;
				break;
			case 4 :
				//signInOptions();
				break;
			default :
				System.out.println("Please select 1 Or 2 or 3");
				break;
		}
		lockerInput.close();
		if(delf==1){
			lockerInput.close();
			lockerOutput.close();
			File myObj = new File("locker-file.txt");
			System.gc();
			//System.out.println(myObj.delete());
			myObj.delete();
			//FileUtils.forceDelete(myObj);
			//FileDeleteStrategy.FORCE.delete(myObj);

			File oldFile = new File("locker-file-temp.txt");
			File newFile = new File("locker-file.txt");

			oldFile.renameTo(newFile);
			//System.out.println(oldFile.renameTo(newFile));
		}

	}
	
	public static void registerUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);

		if(checkExistingUser(users.getUsername())) {
			System.out.println("User Already Existing !");
		}
		else {
			output.println(users.getUsername());
			output.println(users.getPassword());

			System.out.println("User Registration Suscessful !");
		}


		output.close();
		
	}

	public static boolean checkExistingUser(String inpUsername)
	{
		while(input.hasNext()){
			if(input.next().equals(inpUsername)){
				return true;
			}
			else {
				input.next();
			}
		}
		return false;
	}

	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = keyboard.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Login Successful ! 200OK");
					found = true;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
		}
		
	}
	
	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome To LockMe.com		*");
		System.out.println("*   Your Personal Digital Locker	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
	}
	//store credentails
	public static void storeCredentials(String loggedInUser) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER STORE, YOUR CREDS HERE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		userCredentials.setLoggedInUser(loggedInUser);
		
		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);
		
		lockerOutput.println(userCredentials.getLoggedInUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());
		
		System.out.println("YOUR CREDS ARE STORED AND SECURED!");
		lockerOutput.close();		
	}

	//delete credentials
	public static void deleteCredentials(String loggedInUser) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER STORE, DELETE CREDS HERE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");

		userCredentials.setLoggedInUser(loggedInUser);

		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);

		UserCredentials tempU = new UserCredentials();
		PrintWriter lockerOutputTemp=null;

		try {
			File file = new File("locker-file-temp.txt");
			file.createNewFile();
			lockerOutputTemp = new PrintWriter( new FileWriter("locker-file-temp.txt",true));
		}
		catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}

		int addFlag=1;
		int delHappened=0;
		while(lockerInput.hasNext()) {
			addFlag=1;
			tempU.setLoggedInUser(lockerInput.next());
			tempU.setSiteName(lockerInput.next());
//			System.out.println(lockerInput.hasNext());
			if(tempU.getLoggedInUser().equals(userCredentials.getLoggedInUser())) {
				if(tempU.getSiteName().equals(userCredentials.getSiteName())) {
					addFlag=0;
					delHappened=1;
					System.out.println("User Name: "+lockerInput.next());
					System.out.println("User Password: "+lockerInput.next());
				}
			}
			if(addFlag==1) {
				tempU.setUsername(lockerInput.next());
				tempU.setPassword(lockerInput.next());
				lockerOutputTemp.println(tempU.getLoggedInUser());
				lockerOutputTemp.println(tempU.getSiteName());
				lockerOutputTemp.println(tempU.getUsername());
				lockerOutputTemp.println(tempU.getPassword());
			}
		}
		lockerOutputTemp.close();
//		File myObj = new File("locker-file.txt");
//		System.out.println(myObj.delete());
//
//		File oldFile = new File("locker-file-temp.txt");
//		File newFile = new File("locker-file.txt");
//
//		System.out.println(oldFile.renameTo(newFile));

		if(delHappened==1){
			System.out.println("Deleted !!");
		}
		else{
			System.out.println("No Credentials to Delete....");
		}

	}

	//fetch credentials
	public static void fetchCredentials(String inpUsername) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("*   YOUR CREDS ARE 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println(inpUsername);
		
		
		while(lockerInput.hasNext()) {
//			System.out.println(lockerInput.hasNext());
			if(lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: "+lockerInput.next());
				System.out.println("User Name: "+lockerInput.next());
				System.out.println("User Password: "+lockerInput.next());
			}
		}

		lockerInput.close();
	}
	
	public static void initApp() {

		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		
		try {
			//read data from db file
			input = new Scanner(dbFile);
			
			//red data from locker file
			lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboard = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users = new Users();
			userCredentials  = new UserCredentials();
			
			
		} catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}
		
	}

}
