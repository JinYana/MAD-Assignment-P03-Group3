package sg.edu.np.madassignment;

import android.net.Uri;

import java.util.ArrayList;

public class User {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String  getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String  profilepicture) {
        this.profilepicture = profilepicture;
    }


    public int id;
    public String username;
    public String description;
    public String password;
    public String email;
    public int level;
    public String profilepicture;
    public ArrayList<User> friendslist;

    public ArrayList<User> getFriendslist() {
        return friendslist;
    }

    public void setFriendslist(ArrayList<User> friendslist) {
        this.friendslist = friendslist;
    }
}


