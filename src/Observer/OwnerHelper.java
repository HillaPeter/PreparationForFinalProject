package Observer;
import java.util.Observer;

public class OwnerHelper extends ObservableOwner{
    public OwnerHelper(){

    }

    public void addNewFollower(Observer follower){
        if(follower instanceof ObserverFan){
            addObserver(follower);
        }
    }

    public void notifyFollowers (String message){
        setChanged();
        notifyObservers(message);
    }
}
