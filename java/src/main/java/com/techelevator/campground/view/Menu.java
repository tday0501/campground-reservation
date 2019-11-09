package com.techelevator.campground.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.campground.model.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (userInput.toLowerCase().equals("q")) {
			System.exit(0);
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	public Object promptUserForString(String prompt) {
		Object choice = "";
		System.out.print(prompt);
		String userInput = in.nextLine();
		choice = userInput;
		return choice;
	}
	
	public Object promptUserForInt(String prompt) {
		Object choice = null;
		System.out.print(prompt);
		String userInput = in.nextLine();
		try {
			choice = Integer.valueOf(userInput);
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (userInput.toLowerCase().equals("q")) {
			System.exit(0);
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	public Object promptUserForDate(String prompt) {
		Object choice = null;
		System.out.print(prompt);
		String userInput = in.nextLine();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate userDate = LocalDate.parse(userInput, formatter);
			choice = userDate;
		} catch (DateTimeParseException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (userInput.toLowerCase().equals("q")) {
			System.exit(0);
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			if (!((String) options[i].toString()).isEmpty()) {
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.println("Q) Quit");
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
}
