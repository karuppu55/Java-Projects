public class RegistrationDetail
 {
    private String name;
    private String email;
    private long mobile;
    private long uniqueid;
    private String password;
    private double initialpay;

    public RegistrationDetail(String name, String email, long mobile, long uniqueid, String password, double initialpay) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.uniqueid = uniqueid;
        this.password = password;
        this.initialpay = initialpay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public long getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(long uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getInitialpay() {
        return initialpay;
    }

    public void setInitialpay(double initialpay) {
        this.initialpay = initialpay;
    }

    @Override
    public String toString() {
        return "RegistrationDetail{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", uniqueid=" + uniqueid +
                ", password='" + password + '\'' +
                ", initialpay=" + initialpay +
                '}';
    }
}
