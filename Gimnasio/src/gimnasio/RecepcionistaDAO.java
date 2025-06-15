/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RecepcionistaDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private static Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public RecepcionistaDAO() {
        this.databaseConnection = conectar();
    }
    
    private Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
            return con;
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Metodo para identificacion de los recepcionistas por usuario y contraseña.
     * @return
     */
    
    public boolean autentificacion() {
        System.out.println("Introduzca su nombre de usuario: "); 
        String user = scanner.nextLine(); 
        System.out.println("Introduzca su contraseña: "); 
        String password = scanner.nextLine();
        
        String sql = "SELECT COUNT(*) FROM recepcionistas WHERE usuario = ? AND contraseña = ?"; 
        
        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery(); 
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  
            }
            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }       
        return false; 
    }

}
    
