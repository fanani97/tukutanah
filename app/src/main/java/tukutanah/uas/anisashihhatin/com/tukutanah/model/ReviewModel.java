package tukutanah.uas.anisashihhatin.com.tukutanah.model;

public class ReviewModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String name, comment;

    public ReviewModel(String name, String comment){
        this.name = name;
        this.comment = comment;
    }
}