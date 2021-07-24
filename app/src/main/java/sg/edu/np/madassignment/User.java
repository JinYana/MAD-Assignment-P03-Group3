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




    //Aptitude quiz

    public boolean isTakenAptQuiz() {
        return takenAptQuiz;
    }

    public void setTakenAptQuiz(boolean takenAptQuiz) {
        this.takenAptQuiz = takenAptQuiz;
    }

    public int getAptAnimeScore() {
        return aptAnimeScore;
    }

    public void setAptAnimeScore(int aptAnimeScore) {
        this.aptAnimeScore = aptAnimeScore;
    }

    public int getAptComputerScore() {
        return aptComputerScore;
    }

    public void setAptComputerScore(int aptComputerScore) {
        this.aptComputerScore = aptComputerScore;
    }

    public int getAptMathScore() {
        return aptMathScore;
    }

    public void setAptMathScore(int aptMathScore) {
        this.aptMathScore = aptMathScore;
    }

    public int getAptAnimalScore() {
        return aptAnimalScore;
    }

    public void setAptAnimalScore(int aptAnimalScore) {
        this.aptAnimalScore = aptAnimalScore;
    }

    public int getAptMythScore() {
        return aptMythScore;
    }

    public void setAptMythScore(int aptMythScore) {
        this.aptMythScore = aptMythScore;
    }

    public int getAptCartoonScore() {
        return aptCartoonScore;
    }

    public void setAptCartoonScore(int aptCartoonScore) {
        this.aptCartoonScore = aptCartoonScore;
    }

    public int getAptSportScore() {
        return aptSportScore;
    }

    public void setAptSportScore(int aptSportScore) {
        this.aptSportScore = aptSportScore;
    }

    public int getAptVideoGameScore() {
        return aptVideoGameScore;
    }

    public void setAptVideoGameScore(int aptVideoGameScore) {
        this.aptVideoGameScore = aptVideoGameScore;
    }

    public boolean takenAptQuiz;

    public int aptAnimeScore;
    public int aptComputerScore;
    public int aptMathScore;
    public int aptAnimalScore;
    public int aptMythScore;
    public int aptCartoonScore;
    public int aptSportScore;
    public int aptVideoGameScore;

}


