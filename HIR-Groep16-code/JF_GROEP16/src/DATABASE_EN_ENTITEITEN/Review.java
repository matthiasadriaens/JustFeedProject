package DATABASE_EN_ENTITEITEN;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hberouag
 */
public class Review implements Comparable<Review>
{
    private String schrijver ;
    private String text ;
    private double score ;
    private int likes ;

    public Review(String schrijver, String text, double score, int likes) {
        this.schrijver = schrijver;
        this.text = text;
        this.score = score;
        this.likes = likes;
    }

    public String getSchrijver() {
        return schrijver;
    }

    public void setSchrijver(String schrijver) {
        this.schrijver = schrijver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    @Override
    public int compareTo(Review o) {
        if (this.likes > o.likes)
            return -1 ;
        else if (this.likes == o.likes)
            return 0 ;
        else 
            return 1 ;
    }
    
    
}
