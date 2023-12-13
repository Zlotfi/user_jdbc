package entity;

public class User {
    private Integer user_id;
    private String userName;
    private String password;
    private String signup_date;

    public User(Integer user_id, String userName, String password, String signup_date) {
        this.user_id = user_id;
        this.userName = userName;
        this.password = password;
        this.signup_date = signup_date;
    }

    public User() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(String signup_date) {
        this.signup_date = signup_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", signup_date='" + signup_date + '\'' +
                '}';
    }
}
