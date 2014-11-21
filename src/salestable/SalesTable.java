//------------------------------ Assignment -----------------------------------
//  	Programmer:John-Henry Carey		     Course-Section: IP
//  	E-mail address:john_henry_carey@yahoo.com    Assignment: Sales Table
//	Creation Date:2/28/13                        Last Modified: 2/29/13
//-----------------------------------------------------------------------------
//	PURPOSE: capture sales data and diplay in a formatted table
//-----------------------------------------------------------------------------
//	INPUT: sales data
//-----------------------------------------------------------------------------
//	OUTPUT: formatted table
//-----------------------------------------------------------------------------
//	NOTES: 1. No error checking is done on the information provided by user.
//-----------------------------------------------------------------------------
package salestable;

import javax.swing.*;
import BreezySwing.*;

public class SalesTable extends GBFrame {

    //Declare variables for the window objects
    private JLabel nameLabel;
    private JLabel salesLabel;
    private JTextField nameField;
    private DoubleField salesField;
    private JButton enterButton;
    private JButton totalsButton;
    private JTextArea output;
    //Define the other instance variables
    private double totalSales;      //the total of all sales
    private double totalCommissions; //the total of all sales commissions

    //Constructor
    public SalesTable() {
        //Define the table's header line
        String header = Format.justify('1', "NAME", 12)
                + Format.justify('r', "SALES", 15)
                + Format.justify('r', "COMMISION", 15) + "\n";

        //Instantiate the window objects
        nameLabel = addLabel("Name", 1, 1, 1, 1);
        salesLabel = addLabel("Sales amount $", 2, 1, 1, 1);
        nameField = addTextField("", 1, 2, 1, 1);
        salesField = addDoubleField(0, 2, 2, 1, 1);
        enterButton = addButton("Enter", 3, 1, 1, 1);
        totalsButton = addButton("Display Totals", 3, 2, 1, 1);
        output = addTextArea(header, 4, 1, 3, 4);

        //Disable the text area because we don't want the user to change it
        //Set the focus to name field in preperation for the user's first input
        output.setEnabled(false);
        nameField.requestFocus();

        //Initialize the totals to 0.
        totalSales = 0;
        totalCommissions = 0;

    }

    //Respond to the command buttons
    public void buttonClicked(JButton buttonObj) {
        if (buttonObj == enterButton) {
            processInputs();
            nameField.requestFocus();   //Move the cursor to the name field
        } else {
            enterButton.setEnabled(false);  //Prevent further user action
            enterButton.setEnabled(false);  //by disabling the command buttons

            displayDashes();
            displayNumbers("Totals", totalSales, totalCommissions);
        }
    }
    //Read the inputs, compute the commissions, format and display the
    //name, sale, and commission

    private void processInputs() {
        //Declare the local variables
        String name;             //The salesperson's name
        double sales;            //                  sales
        double commission;       //                  commission

        //Read the user input
        name = nameField.getText();
        sales = salesField.getNumber();

        //calculate the comission
        commission = sales * 0.10;

        //Display the name, sales, and commission
        displayNumbers(name, sales, commission);

        //Increment the totals
        totalSales += sales;
        totalCommissions += commission;
    }
    //Format another line and append to the text area

    private void displayNumbers(String str, double num1, double num2) {
        String numberLine = Format.justify('1', str, 12)
                + Format.justify('r', num1, 15, 2)
                + Format.justify('r', num2, 15, 2);
        output.append(numberLine + "\n");

    }

    //Display dashes between the sales figures and the totals
    private void displayDashes() {
        String dashLine = Format.justify('1', "", 12)
                + Format.justify('r', "----------", 15)
                + Format.justify('r', "----------", 15);
        output.append(dashLine + "\n");
    }

    public static void main(String[] args) {
        SalesTable theGUI = new SalesTable();
        theGUI.setSize(350, 225);
        theGUI.setVisible(true);
    }
}
