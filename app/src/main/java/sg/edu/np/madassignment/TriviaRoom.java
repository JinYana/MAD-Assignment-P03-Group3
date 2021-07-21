package sg.edu.np.madassignment;

import java.util.ArrayList;

public class TriviaRoom {

    public int Category;

    public ArrayList<User> Participants;


    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public ArrayList<User> getParticipants() {
        return Participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        Participants = participants;
    }



}
