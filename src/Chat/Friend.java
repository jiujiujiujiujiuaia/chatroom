package Chat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by 87057 on 2018/3/29.
 */
public class Friend {
    private SimpleStringProperty  friendname;
    Friend(String name){
        this.friendname =  new SimpleStringProperty(name);
    }
    public String getfriendname() {
        return friendname.get();
    }

    public void setfriendname(String firstName) {
        this.friendname.set(firstName);
    }

    public StringProperty friendnameProperty() {
        return friendname;
    }

}
