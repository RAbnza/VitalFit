package controller;

public class SessionManager {
    private static SessionManager instance;
    private String username;

    // Private constructor to enforce singleton pattern
    private SessionManager() {}

    // Get the single instance of SessionManager
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Set the username for the session
    public void setUsername(String username) {
        this.username = username;
    }

    // Get the username for the session
    public String getUsername() {
        return username;
    }

    // Clear session data
    public void clearSession() {
        username = null;
    }
}
