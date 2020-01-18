package GUI;

import Model.Order;
import Model.Product;
import Model.Restaurant;
import Model.Waiter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class OrderPanel extends JPanel {


    DecimalFormat decimalFormat;

    CardLayout cl = new CardLayout();
    JButton newOrderButton = new JButton("New Order");


    Waiter assignedWaiterObject;

    JPanel newOrderPanel = new JPanel();
    JPanel mainOrderPanel = new JPanel();
    JPanel mainOrderPanelUp = new JPanel();
    JPanel mainOrderPanelDown = new JPanel();

    JComboBox<Product> cb;

    JTable jt;

    SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,10,1);
    JSpinner count;

    DefaultTableModel defaultTableModel;

    JButton finalize = new JButton("Finalize");


    public OrderPanel(Restaurant restaurant){
        decimalFormat = new DecimalFormat("0.00");

        assignedWaiterObject = restaurant.assignWaiter();
        TitledBorder border = BorderFactory.createTitledBorder("Add Product");
        TitledBorder border2 = BorderFactory.createTitledBorder("Current Order ");

        mainOrderPanelUp.setBorder(border);
        mainOrderPanelDown.setBorder(border2);
        setLayout(cl);
        newOrderPanel.add(newOrderButton);


        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String assignedWaiterName = assignedWaiterObject.getName();
                JOptionPane.showMessageDialog(null,"Hi I am " + assignedWaiterName + ".\n"+"What would you like to order?");
            }
        });

        //Productları ekle
        Product emptyProduct = null;
        cb = new JComboBox<Product>();;
        cb.addItem((Product) emptyProduct);
        for(int i=0; i< restaurant.getProducts().size();i++){
            cb.addItem(restaurant.getProducts().get(i));

        }


        mainOrderPanel.setLayout(new GridLayout(2,1));
        mainOrderPanel.add(mainOrderPanelUp);
        mainOrderPanel.add(mainOrderPanelDown);
        add(newOrderPanel, "0");
        add(mainOrderPanel,"2");



        mainOrderPanelUp.setLayout(new GridLayout(4,2));
        mainOrderPanelUp.add(new JLabel("Product :"));
        mainOrderPanelUp.add(cb);
        count = new JSpinner(spinnerModel);
        mainOrderPanelUp.add(new JLabel("Count :"));
        mainOrderPanelUp.add(count);

        mainOrderPanelUp.add(new JLabel("Price :"));
        JLabel selectedItemPriceLabel = new JLabel();
        mainOrderPanelUp.add(selectedItemPriceLabel);



        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((Product)cb.getSelectedItem() != null) {
                    Double cost = ((Product)cb.getSelectedItem()).getSellingPrice();
                    selectedItemPriceLabel.setText(decimalFormat.format(cost)+" TL");
                    count.setValue(1);
                }
            }
        });


        mainOrderPanelUp.add(new JLabel());

        JButton addButton = new JButton("Add");
        mainOrderPanelUp.add(addButton);


        addButton.addActionListener(e -> addProductToOrder((Product) cb.getSelectedItem(),(Integer) count.getValue()));

        // Bottom Table Panel


        Object[] column={"Product Name","Count","Price"};
        this.defaultTableModel = new DefaultTableModel(column,0);

        jt = new JTable(this.defaultTableModel);
        JScrollPane sp = new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(600,200));

        JPanel mainOrderPanelDown2 = new JPanel();
        mainOrderPanelDown2.setLayout(new GridLayout(1,4));


        mainOrderPanelDown.setLayout(new BoxLayout(mainOrderPanelDown,BoxLayout.Y_AXIS));
        mainOrderPanelDown2.add(new JPanel());
        mainOrderPanelDown2.add(new JPanel());
        mainOrderPanelDown2.add(new JPanel());
        mainOrderPanelDown2.add(finalize);

        finalize.addActionListener(e -> completeOrder(assignedWaiterObject,jt,restaurant));


        mainOrderPanelDown.add(sp,BorderLayout.NORTH);
        mainOrderPanelDown.add(mainOrderPanelDown2,BorderLayout.SOUTH);


        newOrderButton.addActionListener(e -> cl.show(this,"2"));
    }
    public void addProductToOrder(Product selectedProduct, int count){
        Object[] orderRow = {selectedProduct.getName(), count, selectedProduct.getSellingPrice()*count};
        this.defaultTableModel.addRow(orderRow);
    }

    public void completeOrder(Waiter waiter,JTable jt,Restaurant restaurant){
        Order order = new Order();
        JOptionPane jop = new JOptionPane();
        int message_panel = jop.showOptionDialog(null, "Your order is completed\n", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if(message_panel == JOptionPane.OK_OPTION) {
            cl.show(this,"0");
            for(int k = 0;k < jt.getRowCount();k++) {
                String value = jt.getModel().getValueAt(k, 1).toString();
                int number = Integer.parseInt(value);
                for(int j =0; j<restaurant.getProducts().size();j++){
                    if(restaurant.getProducts().get(j).getName().equals(jt.getModel().getValueAt(k, 0))){
                        for(int i = 0 ; i<number;i++) {
                            order.addProduct(restaurant.getProducts().get(j));
                        }
                    }
                }
            }

            waiter.createOrder(order);
            assignedWaiterObject = restaurant.assignWaiter();
            defaultTableModel.getDataVector().removeAllElements();
            count.setValue(1);

        }
        if(message_panel == JOptionPane.CANCEL_OPTION){
            jop.setVisible(false);
        }

    }
}