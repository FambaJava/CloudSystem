/*
 * Created by JFormDesigner on Fri Feb 28 13:15:22 CET 2020
 */

package cloudsystem.gui;

import cloudsystem.Main;
import net.miginfocom.swing.MigLayout;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author unknown
 */
public class CommandLine extends JFrame  {
    private Thread thread;
    public CommandLine() {
        thread = new Thread(()->{
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initComponents();
        });
        thread.start();

    }




    private void radioButton1ActionPerformed(ActionEvent e) {
        this.radioButton1.setSelected(true);
        this.radioButton2.setSelected(false);
        this.radioButton3.setSelected(false);
    }

    private void radioButton2ActionPerformed(ActionEvent e) {
        this.radioButton1.setSelected(false);
        this.radioButton2.setSelected(true);
        this.radioButton3.setSelected(false);
    }


    private void radioButton3ActionPerformed(ActionEvent e) {
        this.radioButton1.setSelected(false);
        this.radioButton2.setSelected(false);
        this.radioButton3.setSelected(true);
    }


    private void radioButton1ItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void radioButton2StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void radioButton2ItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void radioButton3ItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException, ClassNotFoundException, InterruptedException, JSONException, IOException {
        if(textArea1.getText().equalsIgnoreCase("clear")){
            textArea2.setText("");
        }else if(textArea1.getText().equalsIgnoreCase("las")){
            StringBuilder stringBuilder = new StringBuilder();
            Main.getSqlManager().getAllMinecraftServers().forEach(minecraftServer -> {
                stringBuilder.append(minecraftServer.getFullInfo());
            });

            Main.getSqlManager().getAllTeamSpeakServers().forEach(teamSpeakServer -> {
                stringBuilder.append(teamSpeakServer.getFullInfo());
            });
            textArea2.setText("All servers: \n\n" + stringBuilder.toString());
        }else if(textArea1.getText().equalsIgnoreCase("start")){
            Main.start();
        }

        else{
            textArea2.setText("Servers: \nServer-1 dldld");
        }
        textArea1.setText("");
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label1 = new JLabel();
        button1 = new JButton();

        this.radioButton1.setSelected(true);

        //======== this ========
        setTitle("CloudTerminal");
        setMinimumSize(new Dimension(700, 500));
        setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
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
            "[]" +
            "[]" +
            "[]"));

        //---- radioButton1 ----
        radioButton1.setText("Console");
        radioButton1.addItemListener(e -> radioButton1ItemStateChanged(e));
        radioButton1.addActionListener(e -> radioButton1ActionPerformed(e));
        contentPane.add(radioButton1, "cell 0 0");

        //---- radioButton2 ----
        radioButton2.setText("Online Servers");
        radioButton2.addActionListener(e -> radioButton2ActionPerformed(e));
        radioButton2.addChangeListener(e -> radioButton2StateChanged(e));
        radioButton2.addItemListener(e -> radioButton2ItemStateChanged(e));
        contentPane.add(radioButton2, "cell 0 4");

        //---- radioButton3 ----
        radioButton3.setText("Start Server");
        radioButton3.addItemListener(e -> radioButton3ItemStateChanged(e));
        radioButton3.addActionListener(e -> radioButton3ActionPerformed(e));
        contentPane.add(radioButton3, "cell 0 8");

        //======== scrollPane2 ========
        {

            //---- textArea2 ----
            textArea2.setPreferredSize(new Dimension(1000, 500));
            textArea2.setEnabled(false);
            scrollPane2.setViewportView(textArea2);
        }
        contentPane.add(scrollPane2, "cell 6 9");

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setTabSize(100);
            textArea1.setMinimumSize(new Dimension(1000, 23));
            textArea1.setPreferredSize(new Dimension(500, 23));
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1, "cell 6 49 1 12");

        //---- label1 ----
        label1.setText("type in your args:");
        label1.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        contentPane.add(label1, "cell 0 52 1 5");

        //---- button1 ----
        button1.setText("SEND");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 14 55");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) throws ClassNotFoundException, SQLException, JSONException, InterruptedException, IOException {
        new CommandLine();
    }


}
