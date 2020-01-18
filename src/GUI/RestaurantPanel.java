package GUI;

import Model.Order;
import Model.Restaurant;
import Model.Waiter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Arrays;

public class RestaurantPanel extends JPanel {

    DecimalFormat decimalFormat;

    OrderPanel orderPanel;
    CardLayout cl = new CardLayout();
    JPanel mainPanel = new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel listEmployeesPanel = new JPanel();
    JPanel addCookPanel = new JPanel();
    JPanel addWaiterPanel = new JPanel();
    JPanel calculateExpensesPanel = new JPanel();
    JPanel buttonPanel = new JPanel();


    JButton button_1 = new JButton("List Employees");
    JButton button_2 = new JButton("Add Cook");
    JButton button_3 = new JButton("Add Waiter");
    JButton button_4 = new JButton("Calculate Expenses");

    JTextField name_cook;
    JTextField salary_cook;

    JTextField name_waiter;

    JLabel expenses;
    JLabel revenue;
    JLabel profit;


    public RestaurantPanel(Restaurant restaurant) {
        decimalFormat = new DecimalFormat("0.00");
        setLayout(new GridLayout(5, 1, 0, 0));

        add(buttonPanel);
        add(mainPanel);


        mainPanel.setLayout(cl);
        mainPanel.add(blankPanel);
        mainPanel.add(listEmployeesPanel, "1");
        mainPanel.add(addCookPanel, "2");
        mainPanel.add(addWaiterPanel, "3");
        mainPanel.add(calculateExpensesPanel, "4");


        button_1.addActionListener(e -> cl.show(mainPanel, "1"));
        button_2.addActionListener(e -> cl.show(mainPanel, "2"));
        button_3.addActionListener(e -> cl.show(mainPanel, "3"));
        button_4.addActionListener(e -> cl.show(mainPanel, "4"));


        buttonPanel.add(button_1);
        buttonPanel.add(button_2);
        buttonPanel.add(button_3);
        buttonPanel.add(button_4);


        button_1.addActionListener(e -> showListEmployees(listEmployeesPanel, restaurant));

        addCookPanel.setLayout(new GridLayout(3, 2));
        addCookPanel.add(new JLabel("Name:"));
        name_cook = new JTextField();
        addCookPanel.add(name_cook);
        addCookPanel.add(new JLabel("Salary:"));
        salary_cook = new JTextField();
        salary_cook.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if((input < '0' || input > '9') && input != '\b'){
                    e.consume();
                }
            }
        });
        addCookPanel.add(salary_cook);
        addCookPanel.add(new JLabel());
        JButton cook_add = new JButton("Add");
        cook_add.addActionListener(e -> addCooktoRestaurant(restaurant));
        addCookPanel.add(cook_add);


        addWaiterPanel.setLayout(new GridLayout(3, 2));
        addWaiterPanel.add(new JLabel("Name:"));
        name_waiter = new JTextField();
        addWaiterPanel.add(name_waiter);
        addWaiterPanel.add(new JLabel());
        JButton waiter_add = new JButton("Add");
        waiter_add.addActionListener(e -> addWaitertoRestaurant(restaurant));
        addWaiterPanel.add(waiter_add);


        calculateExpensesPanel.setLayout(new GridLayout(3, 2));

        calculateExpensesPanel.add(new JLabel("Expenses:"));
        expenses = new JLabel("E");
        calculateExpensesPanel.add(expenses);

        calculateExpensesPanel.add(new JLabel("Revenue:"));
        revenue = new JLabel("R");
        calculateExpensesPanel.add(revenue);

        calculateExpensesPanel.add(new JLabel("Profit:"));
        profit = new JLabel("P");
        calculateExpensesPanel.add(profit);


        button_4.addActionListener(e -> showCalculateExpenses(restaurant));

    }


    public void addCooktoRestaurant(Restaurant restaurant) {
        if (name_cook.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Enter a name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (salary_cook.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Enter a salary", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            restaurant.addCook(name_cook.getText(), Double.parseDouble(salary_cook.getText()));
            JOptionPane.showMessageDialog(null, "Cook added successfully");
        }
    }

    public void addWaitertoRestaurant(Restaurant restaurant) {
        if (name_waiter.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Enter a name", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            restaurant.addWaiter(name_waiter.getText());
            JOptionPane.showMessageDialog(null, "Waiter added successfully");
        }
    }

    public void showListEmployees(JPanel panel, Restaurant restaurant) {
        panel.removeAll();
        panel.setLayout(new GridLayout(restaurant.getEmployees().size() + 1, 3));

        JLabel id = new JLabel("ID");
        JLabel name = new JLabel("Name");
        JLabel job = new JLabel("Job");

        id.setForeground(Color.RED);
        name.setForeground(Color.RED);
        job.setForeground(Color.RED);

        panel.add(id);
        panel.add(name);
        panel.add(job);
        for (int i = 0; i < restaurant.getEmployees().size(); i++) {
            panel.add(new JLabel(Integer.toString(i + 1)));
            panel.add(new JLabel(restaurant.getEmployees().get(i).getName()));

            panel.add(new JLabel(restaurant.getEmployees().get(i).getClass().getName().substring(6)));
        }
    }

    public void showCalculateExpenses(Restaurant restaurant) {
        double expense_number = restaurant.calculateExpenses();
        double revenue_number = restaurant.calculateRevenue();
        expenses.setText(decimalFormat.format(expense_number)+"TL");
        revenue.setText(decimalFormat.format(revenue_number)+"TL");
        profit.setText(decimalFormat.format((revenue_number - expense_number)) + " TL");
    }
}