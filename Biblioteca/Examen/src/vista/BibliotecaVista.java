/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.Libro;
import modelo.Lector;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author miguel.escudero
 */
public class BibliotecaVista {
    private Scanner scanner;
    
    public BibliotecaVista() {
        this.scanner = new Scanner(System.in);
    }
    
    public Scanner getScanner() {
    return scanner;
    } 
    
    public void mostrarMenuPrincipal() {
        System.out.println(" --- Sistema de Gestion de Biblioteca ---");
        System.out.println("1. Gestionar Libros");
        System.out.println("2. Gestionar Lectores");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    public void mostrarMenuLibros() {
        System.out.println("--- Gestión de Libros ---");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Editar Libro");
        System.out.println("4. Eliminar Libro");
        System.out.println("5. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }
    
    public void mostrarMenuLectores() {
        System.out.println("--- Gestión de Lectores ---");
        System.out.println("1. Agregar Lector");
        System.out.println("2. Listar Lectores");
        System.out.println("3. Editar Lector");
        System.out.println("4. Eliminar Lector");
        System.out.println("5. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }
    
    public Libro capturarDatosLibro() {
        System.out.println("Ingrese los datos del libro:");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicacion: ");
        int anio = Integer.parseInt(scanner.nextLine());
        
        return new Libro(0, titulo, autor, anio);
    }
    
    public Lector capturarDatosLector() {
        System.out.println("Ingrese los datos del lector:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        return new Lector(0, nombre, email);
    }
    
    public int capturarId(String entidad) {
        System.out.print("Ingrese el ID del " + entidad + ": ");
        return Integer.parseInt(scanner.nextLine());
    }
    
    public void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        System.out.println("Listado de Libros:");
        System.out.println("ID\tTítulo\tAutor\tAño");
        for (Libro libro : libros) {
            System.out.printf("%d\t%s\t%s\t%d%n", 
                libro.getId(), 
                libro.getTitulo(), 
                libro.getAutor(), 
                libro.getAnioPublicacion());
        }
    }
    
    public void mostrarLectores(List<Lector> lectores) {
        if (lectores.isEmpty()) {
            System.out.println("No hay lectores registrados.");
            return;
        }
        
        System.out.println("Listado de Lectores:");
        System.out.println("ID\tNombre\tEmail");
        for (Lector lector : lectores) {
            System.out.printf("%d\t%s\t%s%n", 
                lector.getId(), 
                lector.getNombre(), 
                lector.getEmail());
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println( mensaje);
    }
    
    public void mostrarError(String error) {
        System.err.println("Error: " + error);
    }
    
    public void mostrarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        
        System.out.println("Detalles del Libro:");
        System.out.println("ID: " + libro.getId());
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año de publicacion: " + libro.getAnioPublicacion());
    }
    
    public void mostrarLector(Lector lector) {
        if (lector == null) {
            System.out.println("Lector no encontrado.");
            return;
        }
        
        System.out.println("Detalles del Lector:");
        System.out.println("ID: " + lector.getId());
        System.out.println("Nombre: " + lector.getNombre());
        System.out.println("Email: " + lector.getEmail());
    }
    
    public Libro capturarDatosActualizacionLibro(Libro libroExistente) {
        System.out.println("Ingrese los nuevos datos del libro:");
        
        System.out.print("Titulo [" + libroExistente.getTitulo() + "]: ");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            titulo = libroExistente.getTitulo();
        }
        
        System.out.print("Autor [" + libroExistente.getAutor() + "]: ");
        String autor = scanner.nextLine();
        if (autor.isEmpty()) {
            autor = libroExistente.getAutor();
        }
        
        System.out.print("Año de publicacion [" + libroExistente.getAnioPublicacion() + "]: ");
        String anioStr = scanner.nextLine();
        int anio = anioStr.isEmpty() ? libroExistente.getAnioPublicacion() : Integer.parseInt(anioStr);
        
        return new Libro(libroExistente.getId(), titulo, autor, anio);
    }
    
    public Lector capturarDatosActualizacionLector(Lector lectorExistente) {
        System.out.println("Ingrese los nuevos datos del lector:");
        
        System.out.print("Nombre [" + lectorExistente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            nombre = lectorExistente.getNombre();
        }
        
        System.out.print("Email [" + lectorExistente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (email.isEmpty()) {
            email = lectorExistente.getEmail();
        }
        
        return new Lector(lectorExistente.getId(), nombre, email);
    }
}
