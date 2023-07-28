import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login {

    /*SQL DATABASE INTEGRATION*/
    static final String DB_URL = "jdbc:mysql://localhost/epn_prueba";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "SELECT ID_EST, CI_EST, Password_EST FROM estudiantes";
    private JPanel rootPanel;
    private JTextField userField;
    private JTextField contraseña;
    private JButton ingresarButton;


    public Login() {

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = connection.createStatement();
                    ResultSet rset = stmt.executeQuery(QUERY)){

                        //Variables con datos de SQL y entradas de texto
                        //Usuario
                        String userinput = userField.getText();
                        //Contraseña
                        //String password = String.valueOf(rset.getInt("Password_EST"));
                        //String pass = contraseña.getText();

                        while(rset.next()){
                            String username = String.valueOf(rset.getInt("ID_EST"));

                            if(username.equals(userinput)){
                                System.out.println("\nHola");
                            }else{
                                System.out.println("Adios");
                            }
                        }

                        /*//Validación de datos
                        if(username == userinput){
                            System.out.println("Hola");
                        }else{
                            System.out.println("Adios");
                        }*/

                }catch(SQLException n){
                    throw new RuntimeException(n);
                }
            }
        });
    }

        public static void main (String[]args){
            JFrame frame = new JFrame("Login");
            frame.setContentPane(new Login().rootPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setResizable(false);
            frame.setSize(400, 300);
            frame.setVisible(true);
        }
    }