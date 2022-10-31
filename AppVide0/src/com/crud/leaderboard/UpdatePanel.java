package com.crud.leaderboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePanel {
    public UpdatePanel() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId, userName, userScore, userRank;
                userId = statisticIdField.getText();
                userName = usernameField.getText();
                userScore = scoreField.getText();
                userRank = rankField.getText();
                if (!Objects.equals(userId, "") && !Objects.equals(userName, "") && !Objects.equals(userScore, "") &&  !Objects.equals(userRank, "")){
                    try{
                        preparedStatement = Conexion.ConectarnosBD().prepareStatement("UPDATE leaderboard SET usern=?,score=?,rank=? WHERE id=?;");
                        preparedStatement.setString(1, userName);
                        preparedStatement.setString(2, userScore);
                        preparedStatement.setString(3, userRank);
                        preparedStatement.setString(4, userId);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "La informacion ha sido actualizada");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    }catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No se permiten espacios en blanco");
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getMainUpdPanel(){
        return mainUpdPanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel mainUpdPanel;
    private JLabel ttitlePanel;
    private JTextField statisticIdField;
    private JTextField usernameField;
    private JTextField scoreField;
    private JTextField rankField;
    private JButton cancelButton;
    private JButton updateButton;
    private JLabel idTargetLabel;
    private JLabel statisticIdLabel;
    private JLabel secondPanelLabel;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JLabel usernameLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
}
