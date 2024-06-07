package hangman_prototype;


public class User 
{
    
    private String username;
    private int score = 0;
    
    //Constructor to initialize User object with a username and score
    public User(String un, int sc)
    {
        this.score = sc;
        this.username = un;
        
    }
    //Get method to retreive the user score
    public int getScore() 
    {
        return score;
    }
    // set method to update user scode
    public void setScore(int score) 
    {
        this.score = score;
    }
    
    // method to get username
    public String getUsername() 
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    
}
