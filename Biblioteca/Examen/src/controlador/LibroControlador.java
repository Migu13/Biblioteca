/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.Libro;
import modelo.LibroDAO;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author miguel.escudero
 */
public class LibroControlador {
    private LibroDAO libroDAO;
    
    public LibroControlador() {
        this.libroDAO = new LibroDAO();
    }
    
    public void agregarLibro(Libro libro) throws SQLException {
        libroDAO.insertar(libro);
    }
    
    public void actualizarLibro(Libro libro) throws SQLException {
        libroDAO.actualizar(libro);
    }
    
    public void eliminarLibro(int id) throws SQLException {
        libroDAO.eliminar(id);
    }
    
    public List<Libro> listarLibros() throws SQLException {
        return libroDAO.listar();
    }
    
    public Libro buscarLibroPorId(int id) throws SQLException {
        return libroDAO.buscarPorId(id);
    }
}


