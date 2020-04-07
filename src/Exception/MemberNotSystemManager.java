package Exception;

public class MemberNotSystemManager extends Exception {
    public MemberNotSystemManager(){
        super("this member don't have the Permissions that needed");
    }
}
