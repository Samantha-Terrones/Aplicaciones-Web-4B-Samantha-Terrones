package mx.edu.utez.tiendaproductos.excepcion;

public class BussinessException extends RuntimeException{
    public BussinessException(String message){
        super(
                message
        );
    }
}
