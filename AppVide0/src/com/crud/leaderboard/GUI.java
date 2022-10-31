package com.crud.leaderboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI {
    public GUI() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String leadUser, leadScore, leadRank;
                leadUser = usernameField.getText();
                leadScore = scoreField.getText();
                leadRank = rankField.getText();

                try{
                    preparedStatement = Conexion.ConectarnosBD().prepareStatement("INSERT INTO leaderboard (usern, score, rank) VALUES (?,?,?);");
                    preparedStatement.setString(1, leadUser);
                    preparedStatement.setString(2, leadScore);
                    preparedStatement.setString(3, leadRank);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null, "La informacion ha sido insertada");
                }catch (SQLException err){
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, err);
                }
                usernameField.setText("");
                scoreField.setText("");
                rankField.setText("");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(GUI::createUpdateGUI);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(GUI::createDeleteGui);
            }
        });
    }

    public JPanel getMainPanel(){
        showData();
        return mainPanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try{
            Object[] columnTitle = {"LeaderBoard ID","Username","Score","Rank"};
            tableModel = new DefaultTableModel(null, columnTitle);
            table1.setModel(tableModel);

            Connection connection = Conexion.ConectarnosBD();
            Statement stm = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            resultSet = stm.executeQuery("SELECT * FROM leaderboard");
            while (resultSet.next()){
                Object[] data = {
                        resultSet.getString("id"),
                        resultSet.getString("usern"),
                        resultSet.getString("score"),
                        resultSet.getString("rank"),
                };
                tableModel.addRow(data);
            }
        }catch (SQLException err){
            throw new RuntimeException(err);
        }
    }

    private static void createUpdateGUI(){
        UpdatePanel updateUI = new UpdatePanel();
        JPanel updateRoot = updateUI.getMainUpdPanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(updateRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private static void createDeleteGui(){
        DeletePanel deleteUI = new DeletePanel();
        JPanel deleteRoot = deleteUI.getDeletePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(deleteRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

    private JPanel mainPanel;
    private JLabel jTitleMain;
    private JTextField usernameField;
    private JTextField scoreField;
    private JTextField rankField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel userLaber;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private JPanel JFirstPanel;
    private JPanel JSecondPanel;
    private JPanel JThirdPanel;
    private JTable table1;
}
