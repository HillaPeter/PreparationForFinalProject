package Exception;

public class MemberNotExist extends Exception{
    public MemberNotExist(){
        super("this mail member doest exist in the system");
    }
}
