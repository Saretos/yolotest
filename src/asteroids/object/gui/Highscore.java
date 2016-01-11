package asteroids.object.gui;

/**
 * Created by Tobias on 11.12.2015.
 */
public class Highscore implements Comparable<Highscore>
{
    public String name;
    public int score;
    public int gameID;

    @Override
    public int compareTo(Highscore o)
    {
        if(o.score == this.score) return 0;
        if(o.score > this.score) return 1;
        return -1;
    }

    public Highscore(String name, int score, int gameID){
        this.name = name;
        this.score = score;
        this.gameID = gameID;
    }

    public String getScore()
    {
        return ""+score;
    }
    public String getID() { return ""+gameID; }
    public int getIntID() { return gameID; }
}
