package tukutanah.uas.anisashihhatin.com.tukutanah.model;

public class UsersModel {

    private String name ;
    private String email ;
    private String password ;

    public void setName(String name){

        this.name = name ;
    }
    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email ;
    }
    public String getEmail(){
        return email ;
    }
    public void setPassWord(String password){
        this.password = password ;
    }
    public String getPassword(){
        return password ;
    }

    public UsersModel(UsersModel user){

        this.name = user.getName() ;
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    public UsersModel(){

    }
}
