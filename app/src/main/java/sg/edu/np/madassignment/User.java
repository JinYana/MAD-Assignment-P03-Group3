package sg.edu.np.madassignment;

import android.net.Uri;

import java.util.ArrayList;

public class User implements Comparable<User>{

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


    public int gameAnimecorrect;
    public int gameComputercorrect;
    public int gameMathcorrect;
    public int gameAnimalcorrect;
    public int gameMythcorrect;
    public int gameCartooncorrect;
    public int gameSportcorrect;
    public int gameVideoGamecorrect;

    public int gameAnimeanswered;
    public int gameComputeranswered;
    public int gameMathanswered;
    public int gameAnimalanswered;
    public int gameMythanswered;
    public int gameCartoonanswered;
    public int gameSportanswered;

    public int getGameAnimecorrect() {
        return gameAnimecorrect;
    }

    public void setGameAnimecorrect(int gameAnimecorrect) {
        this.gameAnimecorrect = gameAnimecorrect;
    }

    public int getGameComputercorrect() {
        return gameComputercorrect;
    }

    public void setGameComputercorrect(int gameComputercorrect) {
        this.gameComputercorrect = gameComputercorrect;
    }

    public int getGameMathcorrect() {
        return gameMathcorrect;
    }

    public void setGameMathcorrect(int gameMathcorrect) {
        this.gameMathcorrect = gameMathcorrect;
    }

    public int getGameAnimalcorrect() {
        return gameAnimalcorrect;
    }

    public void setGameAnimalcorrect(int gameAnimalcorrect) {
        this.gameAnimalcorrect = gameAnimalcorrect;
    }

    public int getGameMythcorrect() {
        return gameMythcorrect;
    }

    public void setGameMythcorrect(int gameMythcorrect) {
        this.gameMythcorrect = gameMythcorrect;
    }

    public int getGameCartooncorrect() {
        return gameCartooncorrect;
    }

    public void setGameCartooncorrect(int gameCartooncorrect) {
        this.gameCartooncorrect = gameCartooncorrect;
    }

    public int getGameSportcorrect() {
        return gameSportcorrect;
    }

    public void setGameSportcorrect(int gameSportcorrect) {
        this.gameSportcorrect = gameSportcorrect;
    }

    public int getGameVideoGamecorrect() {
        return gameVideoGamecorrect;
    }

    public void setGameVideoGamecorrect(int gameVideoGamecorrect) {
        this.gameVideoGamecorrect = gameVideoGamecorrect;
    }

    public int getGameAnimeanswered() {
        return gameAnimeanswered;
    }

    public void setGameAnimeanswered(int gameAnimeanswered) {
        this.gameAnimeanswered = gameAnimeanswered;
    }

    public int getGameComputeranswered() {
        return gameComputeranswered;
    }

    public void setGameComputeranswered(int gameComputeranswered) {
        this.gameComputeranswered = gameComputeranswered;
    }

    public int getGameMathanswered() {
        return gameMathanswered;
    }

    public void setGameMathanswered(int gameMathanswered) {
        this.gameMathanswered = gameMathanswered;
    }

    public int getGameAnimalanswered() {
        return gameAnimalanswered;
    }

    public void setGameAnimalanswered(int gameAnimalanswered) {
        this.gameAnimalanswered = gameAnimalanswered;
    }

    public int getGameMythanswered() {
        return gameMythanswered;
    }

    public void setGameMythanswered(int gameMythanswered) {
        this.gameMythanswered = gameMythanswered;
    }

    public int getGameCartoonanswered() {
        return gameCartoonanswered;
    }

    public void setGameCartoonanswered(int gameCartoonanswered) {
        this.gameCartoonanswered = gameCartoonanswered;
    }

    public int getGameSportanswered() {
        return gameSportanswered;
    }

    public void setGameSportanswered(int gameSportanswered) {
        this.gameSportanswered = gameSportanswered;
    }

    public int getGameVideoGameanswered() {
        return gameVideoGameanswered;
    }

    public void setGameVideoGameanswered(int gameVideoGameanswered) {
        this.gameVideoGameanswered = gameVideoGameanswered;
    }

    public int gameVideoGameanswered;



    @Override
    public int compareTo(User user) {
        if (level > user.level) {
            return -1;
        }
        else if (level <  user.level) {
            return 1;
        }
        else {
            return 0;
        }
    }

}


