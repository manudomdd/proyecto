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
import java.util.Scanner;
import java.util.UUID;

public class ValoracionDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private static Connection databaseConnection;
	private static Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public ValoracionDAO() {
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
     * Metodo para valorar a un entrenador.
     */
    
    public static void valorarEntrenador() {
    	System.out.println("Introduzca el nif del entrenador a valorar: "); 
    	String nif = scanner.nextLine(); 
    	System.out.println("Introduzca su nif: "); 
    	String nifCliente = scanner.nextLine(); 
    	System.out.println("Introduzca la valoracion (0-10): "); 
    	int valoracion = scanner.nextInt(); 
    	scanner.nextLine(); 
    	System.out.println("Introduzca un comentario: "); 
    	String comentario = scanner.nextLine(); 
    	
    	String sql = "INSERT INTO valoraciones VALUES (?, ?, ?, ?, ?)"; 
    	
    	try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
            String idPago = UUID.randomUUID().toString();
            stmt.setString(1, idPago);
            stmt.setString(2, nifCliente);
            stmt.setString(3, nif);
            stmt.setInt(4, valoracion);
            stmt.setString(5, comentario);
            int rowsAffected = stmt.executeUpdate(); 
            if (rowsAffected > 0) {
            	System.out.println("Registro añadido."); 
            } else {
            	System.out.println("Hubo un error añadiendo el registro."); 
            }

    	} catch (SQLException ex) {
    		System.out.println("Hubo un error con la base de datos: " + ex.getMessage()); 
    	}
    	
    }
    
    /**
     * Metodo para consultar las valoraciones de un entrenador.
     */
    
    public void valoracionesEntrenador() {
        System.out.println("Introduzca su NIF de entrenador: "); 
        String nif = scanner.nextLine(); 
        
        String sql = "SELECT idCliente, puntuacion, comentario FROM valoraciones WHERE idEntrenador = ?"; 
        
        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
            stmt.setString(1, nif);
            ResultSet rs = stmt.executeQuery(); 
            boolean existe = false; 
            
            while (rs.next()) {
                existe = true;
                String idCliente = rs.getString("idCliente");
                int puntuacion = rs.getInt("puntuacion");
                String comentario = rs.getString("comentario");

                System.out.println("Cliente: " + idCliente);
                System.out.println("Puntuación: " + puntuacion);
                System.out.println("Comentario: " + comentario);
                System.out.println("--------------------------");
            }
            
            if (!existe) {
                System.out.println("Usted no tiene valoraciones aún."); 
            }

        } catch (SQLException ex) {
            System.out.println("Hubo un error con la base de datos: " + ex.getMessage()); 
        }
    }
}
