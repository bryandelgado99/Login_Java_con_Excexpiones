import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
    private JButton crearNuevoUsuarioButton;
    private JPanel rootPanel;
    private JButton eliminarUnUsuarioButton;
    private JButton actualizarUsuarioButton;
    private JTextField user_input;

    public static int width = 500;
    public static int height = 600;

    public JPanel getPanel1() {
        return rootPanel;
    }

    public void Delete(){
        final String DB_URL = "jdbc:mysql://localhost/epn_prueba";
        final String USER = "root";
        final String PASS = "root_bas3";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS))
        {
            String QUERY = "DELETE FROM estudiantes WHERE ID_EST = '"+ user_input.getText() +"'";

            try(){

            }

        }catch (SQLException X){
            JOptionPane.showMessageDialog(null, "No se puede borrar el usuario, no existe\n" + X.getMessage(), "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Window(){
        JFrame framemenu = new JFrame("Panel de Usuario");
        framemenu.setContentPane(new Menu().rootPanel);
        framemenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framemenu.setSize(width, height);
        framemenu.setResizable(true);
        framemenu.setLocationRelativeTo(null);
        framemenu.pack();
        framemenu.setVisible(true);
    }
}
