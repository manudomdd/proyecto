/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RutinaDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public RutinaDAO() {
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
     * Metodo para generar una rutina en base a los dias de entrenamiento.
     * @throws SQLException
     */
    
    public void generarRutina() throws SQLException {
        System.out.println("Introduzca el nif del cliente: "); 
        String nifCliente = scanner.nextLine(); 
        System.out.println("Introduzca su nif de entrenador: "); 
        String nifEntrenador = scanner.nextLine(); 
        System.out.println("Introduzca el numero de dias que va a entrenar: "); 
        int numDias = scanner.nextInt(); 
        scanner.nextLine();
    	String sql = "{CALL GenerarRutina(?, ?, ?)}";
        try (CallableStatement cs = databaseConnection.prepareCall(sql)) {
            cs.setString(1, nifCliente);
            cs.setString(2, nifEntrenador);
            cs.setInt(3, numDias);
            
            boolean hasResultSet = cs.execute();
            
            if (hasResultSet) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        System.out.println("Rutina generada:");
                        System.out.println("ID Rutina: " + rs.getString("idRutina"));
                        System.out.println("NIF Cliente: " + rs.getString("nifCliente"));
                        System.out.println("ID Entrenador: " + rs.getString("idEntrenador"));
                        System.out.println("Tipo Rutina: " + rs.getString("tipoRutina"));
                    } else {
                        System.out.println("No se pudo generar la rutina.");
                    }
                }
            } else {
                System.out.println("El procedimiento no devolvió resultados.");
            }
        }
    }
}
