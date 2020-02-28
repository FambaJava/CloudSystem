/*
 * Created by JFormDesigner on Fri Feb 28 11:59:31 CET 2020
 */

package cloudsystem.gui;

import net.miginfocom.swing.MigLayout;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author unknown
 */
public class MasterGUI extends JFrame {
    public MasterGUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException, InterruptedException, JSONException, IOException {
        String user = this.formattedTextField1.getText();
        String password = this.passwordField1.getText();
        if(user.equals("admin") && password.equals("admin")){
            JOptionPane.showMessageDialog(null, "You are now logged in!", "InfoBox: " + "Successful", JOptionPane.INFORMATION_MESSAGE);
            new CommandLine();
            this.hide();
        }else{
            JOptionPane.showMessageDialog(null, "Your datas are Wrong!", "InfoBox: " + "Error", JOptionPane.ERROR_MESSAGE);
            this.formattedTextField1.setText("");
            this.passwordField1.setText("");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label2 = new JLabel();
        formattedTextField1 = new JFormattedTextField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("CloudSystem - Login");
        label1.setFont(new Font("Devanagari MT", Font.PLAIN, 28));
        contentPane.add(label1, "cell 0 0");

        //---- label2 ----
        label2.setText("USER:");
        contentPane.add(label2, "cell 0 2");
        contentPane.add(formattedTextField1, "cell 0 4");

        //---- label3 ----
        label3.setText("PASSWORD:");
        contentPane.add(label3, "cell 0 6");
        contentPane.add(passwordField1, "cell 0 8");

        //---- button1 ----
        button1.setText("LOGIN TO CLOUD");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 0 12");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JFormattedTextField formattedTextField1;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new MasterGUI();
    }
}
