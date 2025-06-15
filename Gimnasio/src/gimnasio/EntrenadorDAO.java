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

public class EntrenadorDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private static Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public EntrenadorDAO() {
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
     * Metodo para que los entrenadores se identifican con su nombre de usuario y contraseña.
     * @return
     */
    public boolean autentificacion() {
        System.out.println("Introduzca su nombre de usuario: "); 
        String user = scanner.nextLine(); 
        System.out.println("Introduzca su contraseña: "); 
        String password = scanner.nextLine();
        
        String sql = "SELECT COUNT(*) FROM entrenadores WHERE usuario = ? AND contraseña = ?"; 
        
        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery(); 
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  // Autenticación correcta solo si existe un resultado
            }
            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }       
        return false; 
    }

    /**
     * Metodo para recalcular salarios en funcion de las puntuaciones promedio.
     */
    
    public void recalcularSalarios() {
        String sqlMedia = "SELECT idEntrenador, AVG(puntuacion) AS media FROM valoraciones GROUP BY idEntrenador";
        String sqlActualizar = "UPDATE entrenadores SET salario = salario * 0.95 WHERE nif = ?";
        
        try (
            PreparedStatement stmtMedia = databaseConnection.prepareStatement(sqlMedia);
            PreparedStatement stmtActualizar = databaseConnection.prepareStatement(sqlActualizar);
            ResultSet rs = stmtMedia.executeQuery();
        ) {
            while (rs.next()) {
                String nifEntrenador = rs.getString("idEntrenador");
                double media = rs.getDouble("media");

                System.out.printf("Entrenador %s - Media valoraciones: %.2f%n", nifEntrenador, media);

                if (media <= 5.0) {
                    stmtActualizar.setString(1, nifEntrenador);
                    int filas = stmtActualizar.executeUpdate();
                    if (filas > 0) {
                        System.out.println("Salario reducido un 5% para entrenador con nif: " + nifEntrenador);
                    } else {
                        System.out.println("No se pudo actualizar salario para entrenador con nif: " + nifEntrenador);
                    }
                } else {
                	System.out.println("Este entrenador tiene medias por encima de 5. Se mantiene su salario.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error accediendo a la base de datos: " + e.getMessage());
        }
    }
}
