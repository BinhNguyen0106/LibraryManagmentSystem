
public class User extends Library{
    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public User() {
    }

    @Override
    public String toString() {
        return String.format("name = '%s', id = %d", userName, userId);
    }
}
