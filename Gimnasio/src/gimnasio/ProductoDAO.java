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
import java.util.Scanner;
import java.util.UUID;

public class ProductoDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private static Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public ProductoDAO() {
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
     * Metodo para añadir un producto. 
     */
    
    public void añadirProducto() {
        String idProducto = UUID.randomUUID().toString();
    	System.out.println("Nombre del producto: "); 
    	String nombre = scanner.nextLine(); 
    	System.out.println("Precio del producto: "); 
    	double precio = scanner.nextDouble(); 
    	scanner.nextLine(); 
    	System.out.println("Introduzca el stock: "); 
    	int stock = scanner.nextInt(); 
    	
    	String sql = "INSERT INTO productos VALUES (?, ?, ?, ?)"; 
    	
    	try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
    		stmt.setString(1, idProducto);
    		stmt.setString(2, nombre);
    		stmt.setDouble(3, precio);
    		stmt.setInt(4, stock);
    		int rowsAffected = stmt.executeUpdate(); 
    		if (rowsAffected > 0) {
    			System.out.println("Producto añadido al catalogo."); 
    		} else {
    			System.out.println("Hubo un error añadiendo el producto."); 
    		}		
    	} catch (SQLException ex ) {
    		System.out.println("Hubo un error con la base de datos: " + ex.getMessage()); 
    	}
    }
    
    /**
     * Metodo para que los clientes puedan comprar un producto.
     */
    
    public void comprarProducto() {
        mostrarProductos();
        System.out.println("Introduzca el nombre del producto que desea comprar: ");
        String nombreProducto = scanner.nextLine();

        String consulta = "SELECT idProducto, stock, precio FROM productos WHERE nombre = ?";
        try (PreparedStatement selectStmt = databaseConnection.prepareStatement(consulta)) {
            selectStmt.setString(1, nombreProducto);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String idProducto = rs.getString("idProducto");
                int stockActual = rs.getInt("stock");
                double precioUnitario = rs.getDouble("precio");

                System.out.println("Stock disponible: " + stockActual);
                System.out.println("¿Cuántas unidades desea comprar?");
                int cantidad = scanner.nextInt();
                scanner.nextLine(); 

                if (cantidad <= stockActual) {
                    double total = cantidad * precioUnitario;

                    System.out.println("Introduce el NIF del cliente:");
                    String idCliente = scanner.nextLine();

                    System.out.println("Método de pago (Efectivo/Tarjeta/Transferencia):");
                    String metodoPago = scanner.nextLine();

                    String idPago = UUID.randomUUID().toString();

                    String concepto = "Compra de " + cantidad + " x " + nombreProducto;

                    String insertarPago = "INSERT INTO pagos (idPago, idCliente, monto, metodoPago, concepto) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement insertPagoStmt = databaseConnection.prepareStatement(insertarPago)) {
                        insertPagoStmt.setString(1, idPago);
                        insertPagoStmt.setString(2, idCliente);
                        insertPagoStmt.setDouble(3, total);
                        insertPagoStmt.setString(4, metodoPago);
                        insertPagoStmt.setString(5, concepto);

                        int filasInsertadas = insertPagoStmt.executeUpdate();
                        if (filasInsertadas > 0) {
                            System.out.println("Pago registrado correctamente.");
                        } else {
                            System.out.println("Error al registrar el pago.");
                            return;
                        }
                    }

                    String actualizacion = "UPDATE productos SET stock = ? WHERE idProducto = ?";
                    try (PreparedStatement updateStmt = databaseConnection.prepareStatement(actualizacion)) {
                        updateStmt.setInt(1, stockActual - cantidad);
                        updateStmt.setString(2, idProducto);

                        int filasActualizadas = updateStmt.executeUpdate();
                        if (filasActualizadas > 0) {
                            System.out.println("Compra realizada correctamente.");
                        } else {
                            System.out.println("Error al actualizar el stock.");
                        }
                    }
                } else {
                    System.out.println("No hay suficiente stock disponible.");
                }

            } else {
                System.out.println("Producto no encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println("Hubo un error con la base de datos: " + ex.getMessage());
        }
    }
    
    public void renovarMensualidad() {
    	
    }
    
    /**
     * Metodo para mostrar todos los productos de la tienda de suplementacion. 
     */

    public static void mostrarProductos() {
        String sql = "SELECT nombre, precio, stock FROM productos";

        try (Statement stmt = databaseConnection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Catálogo de productos:");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                System.out.println("Producto: " + nombre + " | Precio: " + precio + "€ | Stock: " + stock);
            }

        } catch (SQLException ex) {
            System.out.println("Error al mostrar productos: " + ex.getMessage());
        }
    }
}
