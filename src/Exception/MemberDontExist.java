package Exception;

public class MemberDontExist extends Exception{
    public MemberDontExist(){
        super("this mail member doest exist in the system");
    }
}
