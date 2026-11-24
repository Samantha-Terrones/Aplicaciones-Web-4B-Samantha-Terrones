package mx.edu.utez.tiendaproductos.excepcion;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(
                message
        );
    }
}
