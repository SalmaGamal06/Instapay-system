public class User {
    private String userName;
    private String password;
    private String email;
    private String mobileNumber;
    //private InstapayAccount instapayAccount;

    public User(String userName, String password, String email, String mobileNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    //public InstapayAccount getInstapayAccount() {
    //    return instapayAccount;
    //}
    //public void setInstapayAccount(InstapayAccount instapayAccount) {
    //    this.instapayAccount = instapayAccount;
    //}
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
