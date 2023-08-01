import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login{
    private JPanel rootPanel;
    private JTextField userField;
    private JTextField contraseña;
    private JButton ingresarButton;
    private JTextPane textPane;

    /*Estados, conexión y sentencias de SQL*/
    public Connection con;
    public Statement sentencia;
    private ResultSet resultado;

    //Clase principal
    public Login() {

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Login Con = new Login();
                    //Variables del Login
                    String pass = contraseña.getText();
                    String userinput = userField.getText();
                    Con.ConectarBase();

                    String QUERY = "SELECT ID_EST, NOMBRE_EST, EDAD_EST, CIUDAD_EST, Password_EST FROM estudiantes WHERE ID_EST = '"+ userinput +"' AND Password_EST = '" + pass + "'";
                    Con.resultado = Con.sentencia.executeQuery(QUERY);  //Query Read

                    if(Con.resultado.next()){
                        //Obtenemos el nombre del usuario
                        String name = Con.resultado.getString("NOMBRE_EST");
                        JOptionPane.showMessageDialog(null, "Bievenido, " + name + "!", "Inciando sesión...", JOptionPane.INFORMATION_MESSAGE);

                        Window();

                    }else{
                        JOptionPane.showMessageDialog(null, "Usuario y/o contraseña son erróneos. Intente nuevamente!\n", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(SQLException N){
                    JOptionPane.showMessageDialog(null, N.getMessage(), "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main (String[]args){
            JFrame frame = new JFrame("Login");
            frame.setContentPane(new Login().rootPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(400, 350);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
    }
    public static void ConectarBase(){
        try {
            /*SQL DATABASE INTEGRATION*/
            final String DB_URL = "jdbc:mysql://localhost/epn_prueba";
            final String USER = "root";
            final String PASS = "root_bas3";

            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement sentencia = con.createStatement();

        }catch(SQLException EX){
            JOptionPane.showMessageDialog(null, "Error en conectar base de datos MySQL\n" + EX.getMessage(), "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Window(){
        Menu.Window();
    }
}