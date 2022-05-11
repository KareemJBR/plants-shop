package il.cshaifasweng.OCSFMediatorExample.entities;

public class User {
    private final int user_id;
    private String first_name;
    private String last_name;
    private String user_email;

    public User(int user_id, String first_name, String last_name, String user_email) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_email = user_email;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public void setUser_email(String user_email){
        this.user_email = user_email;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getUser_email(){
        return user_email;
    }

}
