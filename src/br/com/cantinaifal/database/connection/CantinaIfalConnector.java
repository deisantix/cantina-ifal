package br.com.cantinaifal.database.connection;


import java.sql.Connection;
import java.sql.SQLException;
import br.com.cantinaifal.database.importer.CantinaIfalDBInstaller;
import javax.swing.JOptionPane;
import javax.swing.JDialog;


public class CantinaIfalConnector {

    public static Connection connect() throws SQLException {
        Connection con = null;
        JOptionPane message = new JOptionPane();
        JDialog dialog = message.createDialog(null, "Message");

        try {
            con = ConnectionFactory.getConnection("cantinaifal");
            message.setMessage("Banco conectado com sucesso!");
            dialog.setVisible(true);
            
            return con;

        } catch (SQLException e1) {

            // bd de auxilio para poder criar o bd correto
            con = ConnectionFactory.getConnection("mysql");

            try {
                message.setMessage("<html><center>" + "Espere um momento, o banco está sendo criado..." + "</center></html>");
                dialog.setVisible(true);

                CantinaIfalDBInstaller.installDB(con);

                message.setMessage("Banco criado com sucesso!");
                dialog.setVisible(true);

            } catch (Exception e2) {
                message.setMessage(e2.getMessage());
                dialog.setVisible(true);
            }

        }

        con = ConnectionFactory.getConnection("cantinaifal");
        message.setMessage("Banco conectado com sucesso!");
        dialog.setVisible(true);

        return con;
    }
}
