package com.crud.leaderboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeletePanel {
    public DeletePanel() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userDId;
                userDId = userIdField.getText();
                if (!Objects.equals(userDId, "")){
                    try{
                        preparedStatement = Conexion.ConectarnosBD().prepareStatement("DELETE FROM leaderboard WHERE id=?;");
                        preparedStatement.setString(1, userDId);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "La informacion ha sido eliminada");
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

    private PreparedStatement preparedStatement;

    public JPanel getDeletePanel(){
        return deletePanel;
    }

    private JPanel deletePanel;
    private JLabel JTitle;
    private JPanel JPanelD;
    private JPanel JPanelButton;
    private JTextField userIdField;
    private JButton cancelButton;
    private JButton deleteButton;
}
