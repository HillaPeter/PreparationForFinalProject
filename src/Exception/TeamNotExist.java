package Exception;

public class TeamNotExist extends Exception{
    public TeamNotExist(){
        super("this team doest exist in the system");
    }
}
