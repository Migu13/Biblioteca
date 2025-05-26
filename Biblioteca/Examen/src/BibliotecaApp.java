/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miguel.escudero
 */
import controlador.LectorControlador;
import controlador.LibroControlador;
import modelo.Lector;
import modelo.Libro;
import vista.BibliotecaVista;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private BibliotecaVista vista;
    private LibroControlador libroControlador;
    private LectorControlador lectorControlador;
    private boolean ejecutando;
    
    public BibliotecaApp() {
        this.vista = new BibliotecaVista();
        this.libroControlador = new LibroControlador();
        this.lectorControlador = new LectorControlador();
        this.ejecutando = true;
    }
    
    public void iniciar() {
        while (ejecutando) {
            vista.mostrarMenuPrincipal();
            int opcion = Integer.parseInt(vista.getScanner().nextLine());
            
            switch (opcion) {
                case 1:
                    gestionarLibros();
                    break;
                case 2:
                    gestionarLectores();
                    break;
                case 3:
                    ejecutando = false;
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarError("Opción no válida.");
            }
        }
    }
    
    private void gestionarLibros() {
        boolean volver = false;
        
        while (!volver) {
            vista.mostrarMenuLibros();
            int opcion = Integer.parseInt(vista.getScanner().nextLine());
            
            try {
                switch (opcion) {
                    case 1:
                        Libro nuevoLibro = vista.capturarDatosLibro();
                        libroControlador.agregarLibro(nuevoLibro);
                        vista.mostrarMensaje("Libro agregado correctamente.");
                        break;
                    case 2:
                        List<Libro> libros = libroControlador.listarLibros();
                        vista.mostrarLibros(libros);
                        break;
                    case 3:
                        int idEditar = vista.capturarId("libro a editar");
                        Libro libroExistente = libroControlador.buscarLibroPorId(idEditar);
                        if (libroExistente != null) {
                            vista.mostrarLibro(libroExistente);
                            Libro libroActualizado = vista.capturarDatosActualizacionLibro(libroExistente);
                            libroControlador.actualizarLibro(libroActualizado);
                            vista.mostrarMensaje("Libro actualizado correctamente.");
                        } else {
                            vista.mostrarError("No se encontró el libro con ID " + idEditar);
                        }
                        break;
                    case 4:
                        int idEliminar = vista.capturarId("libro a eliminar");
                        libroControlador.eliminarLibro(idEliminar);
                        vista.mostrarMensaje("Libro eliminado correctamente.");
                        break;
                    case 5:
                        volver = true;
                        break;
                    default:
                        vista.mostrarError("Opción no válida.");
                }
            } catch (SQLException e) {
                vista.mostrarError("Error de base de datos: " + e.getMessage());
            } catch (NumberFormatException e) {
                vista.mostrarError("Entrada inválida: debe ingresar un número.");
            }
        }
    }
    
    private void gestionarLectores() {
        boolean volver = false;
        
        while (!volver) {
            vista.mostrarMenuLectores();
            int opcion = Integer.parseInt(vista.getScanner().nextLine());
            
            try {
                switch (opcion) {
                    case 1:
                        Lector nuevoLector = vista.capturarDatosLector();
                        lectorControlador.agregarLector(nuevoLector);
                        vista.mostrarMensaje("Lector agregado correctamente.");
                        break;
                    case 2:
                        List<Lector> lectores = lectorControlador.listarLectores();
                        vista.mostrarLectores(lectores);
                        break;
                    case 3:
                        int idEditar = vista.capturarId("lector a editar");
                        Lector lectorExistente = lectorControlador.buscarLectorPorId(idEditar);
                        if (lectorExistente != null) {
                            vista.mostrarLector(lectorExistente);
                            Lector lectorActualizado = vista.capturarDatosActualizacionLector(lectorExistente);
                            lectorControlador.actualizarLector(lectorActualizado);
                            vista.mostrarMensaje("Lector actualizado correctamente.");
                        } else {
                            vista.mostrarError("No se encontró el lector con ID " + idEditar);
                        }
                        break;
                    case 4:
                        int idEliminar = vista.capturarId("lector a eliminar");
                        lectorControlador.eliminarLector(idEliminar);
                        vista.mostrarMensaje("Lector eliminado correctamente.");
                        break;
                    case 5:
                        volver = true;
                        break;
                    default:
                        vista.mostrarError("Opción no válida.");
                }
            } catch (SQLException e) {
                vista.mostrarError("Error de base de datos: " + e.getMessage());
            } catch (NumberFormatException e) {
                vista.mostrarError("Entrada inválida: debe ingresar un número.");
            }
        }
    }
    
    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.iniciar();
    }
}
