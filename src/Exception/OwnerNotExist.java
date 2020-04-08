package Exception;

public class OwnerNotExist extends Exception{
    public OwnerNotExist(){
        super("this owner doest exist in the system");
    }
}
