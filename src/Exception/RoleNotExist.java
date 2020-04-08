package Exception;

public class RoleNotExist extends Exception{
    public RoleNotExist(){
        super("this role doest exist in the system");
    }
}
