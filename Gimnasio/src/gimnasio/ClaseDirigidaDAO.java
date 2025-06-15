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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class ClaseDirigidaDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private static Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public ClaseDirigidaDAO() {
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
     * Metodo para inscribirse en una clase dirigida.
     * @return
     * @throws SQLException
     */
    
    public boolean inscribirseEnClaseDirigida() throws SQLException {
        mostrarClases(); 

        System.out.print("Introduzca su NIF: "); 
        String nif = scanner.nextLine(); 

        System.out.print("Introduzca el ID de la clase: "); 
        String idClase = scanner.nextLine(); 

        System.out.print("Introduzca la fecha de la clase (dd/MM/yyyy): "); 
        String fechaStr = scanner.nextLine(); 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);

        if (!fecha.isAfter(LocalDate.now())) {
            System.out.println("La fecha debe ser futura.");
            return false;
        }

        String sqlClase = "SELECT aforo FROM clasesdirigidas WHERE idClase = ? AND fecha = ?";
        try (PreparedStatement psClase = databaseConnection.prepareStatement(sqlClase)) {
            psClase.setString(1, idClase);
            psClase.setDate(2, java.sql.Date.valueOf(fecha));
            ResultSet rs = psClase.executeQuery();
            if (rs.next()) {
                int aforo = rs.getInt("aforo");
                if (aforo <= 0) {
                    System.out.println("No quedan plazas disponibles para esta clase.");
                    return false;
                }
            } else {
                System.out.println("La clase no existe para esa fecha.");
                return false;
            }
        }

        String sqlCheck = "SELECT COUNT(*) FROM inscripcionesclases WHERE idCliente = ? AND idClase = ? AND fecha = ?";
        try (PreparedStatement psCheck = databaseConnection.prepareStatement(sqlCheck)) {
            psCheck.setString(1, nif);
            psCheck.setString(2, idClase);
            psCheck.setDate(3, java.sql.Date.valueOf(fecha));
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Ya estás inscrito en esta clase.");
                return false;
            }
        }

        String sqlInsert = "INSERT INTO inscripcionesclases (idCliente, idClase, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement psInsert = databaseConnection.prepareStatement(sqlInsert)) {
            psInsert.setString(1, nif);
            psInsert.setString(2, idClase);
            psInsert.setDate(3, java.sql.Date.valueOf(fecha));
            int filas = psInsert.executeUpdate();
            if (filas == 1) {
                System.out.println("Inscripción realizada con éxito.");
                return true;
            } else {
                System.out.println("No se pudo completar la inscripción.");
                return false;
            }
        }
    }
    
    /**
     * Metodo para mostrar todas las clases.
     */
   
    public static void mostrarClases() {
        String sql = "SELECT idClase, nombreClase, idEntrenador, aforo, fecha FROM clasesdirigidas";

		try (Statement stmt = databaseConnection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Clases Dirigidas:");
            System.out.println("ID Clase | Nombre Clase | ID Entrenador | Aforo | Fecha");

            while (rs.next()) {
                String idClase = rs.getString("idClase");
                String nombreClase = rs.getString("nombreClase");
                String idEntrenador = rs.getString("idEntrenador");
                int aforo = rs.getInt("aforo");
                Date fecha = rs.getDate("fecha");

                System.out.printf("%s | %s | %s | %d | %s\n", idClase, nombreClase, idEntrenador, aforo, fecha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
