/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DietaDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public DietaDAO() {
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
     * Metodo para generar una dieta.
     * @param nifCliente
     * @throws SQLException
     */
    
    public void generarDieta(String nifCliente) throws SQLException {
        CallableStatement cs = null;
        System.out.println("Seleccione el objetivo del cliente: "); 
        System.out.println("1. Mantener peso."); 
        System.out.println("2. Ganar masa muscular."); 
        System.out.println("3. Perder grasa."); 
        int objetivo = scanner.nextInt(); 
        scanner.nextLine(); 
        System.out.println("Introduzca su nif como entrenador: "); 
        String nifEntrenador = scanner.nextLine();
        try {
            String sql = "{ CALL GenerarDieta(?, ?, ?) }";
            cs = databaseConnection.prepareCall(sql);

            cs.setString(1, nifCliente);
            cs.setInt(2, objetivo);
            cs.setString(3, nifEntrenador);

            cs.execute();
            System.out.println("Dieta generada con éxito para cliente " + nifCliente);
        } finally {
            if (cs != null) cs.close();
        }
    }
    
    /**
     * Metodo para obtener las dietas por nif.
     * @throws SQLException
     */
    
    public void obtenerYMostrarDietaPorNif() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca el NIF del cliente: ");
        String nifCliente = scanner.nextLine();

        String sql = """
            SELECT id, nifCliente, idEntrenador, numCalorias, objetivo, grCarbs, grProt, grGrasas
            FROM Dietas
            WHERE nifCliente = ?
            ORDER BY id DESC
            LIMIT 1
        """;

        try (PreparedStatement ps = databaseConnection.prepareStatement(sql)) {
            ps.setString(1, nifCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n=== Dieta del Cliente ===");
                    System.out.println("ID: " + rs.getString("id"));
                    System.out.println("NIF Cliente: " + rs.getString("nifCliente"));
                    System.out.println("ID Entrenador: " + rs.getString("idEntrenador"));
                    System.out.println("Calorías totales: " + rs.getInt("numCalorias"));
                    System.out.println("Objetivo: " + rs.getString("objetivo"));
                    System.out.println("Carbohidratos: " + rs.getInt("grCarbs") + " g");
                    System.out.println("Proteínas: " + rs.getInt("grProt") + " g");
                    System.out.println("Grasas: " + rs.getInt("grGrasas") + " g");
                } else {
                    System.out.println("No se encontró ninguna dieta para el cliente con NIF: " + nifCliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la dieta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
