package heroku.huelladecarbono.model.UserExceptions;

public class EasyPasswordException extends RuntimeException{
    public EasyPasswordException(String mensajeDeError) {
        System.out.println(mensajeDeError);
    }
}
