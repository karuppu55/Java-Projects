public class Account {
	static int count;
    private long id;
    private long uniqueid;
    private String email;
    private String password;
    private double realmoney;
    private double zcoins;

    public Account(long id, long uniqueid, String email, String password, double realmoney, double zcoins) {
        this.id = id;
        this.uniqueid = uniqueid;
        this.email = email;
        this.password = password;
        this.realmoney = realmoney;
        this.zcoins = zcoins;
        count++;
    }
    static{
    	count=0;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(long uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(double realmoney) {
        this.realmoney = realmoney;
    }

    public double getZcoins() {
        return zcoins;
    }

    public void setZcoins(double zcoins) {
        this.zcoins = zcoins;
    }
}
