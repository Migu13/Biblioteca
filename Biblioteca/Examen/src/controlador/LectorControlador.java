/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.Lector;
import modelo.LectorDAO;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author miguel.escudero
 */
public class LectorControlador {
    private LectorDAO lectorDAO;
    
    public LectorControlador() {
        this.lectorDAO = new LectorDAO();
    }
    
    public void agregarLector(Lector lector) throws SQLException {
        lectorDAO.insertar(lector);
    }
    
    public void actualizarLector(Lector lector) throws SQLException {
        lectorDAO.actualizar(lector);
    }
    
    public void eliminarLector(int id) throws SQLException {
        lectorDAO.eliminar(id);
    }
    
    public List<Lector> listarLectores() throws SQLException {
        return lectorDAO.listar();
    }
    
    public Lector buscarLectorPorId(int id) throws SQLException {
        return lectorDAO.buscarPorId(id);
    }
}
