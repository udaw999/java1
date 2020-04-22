package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.Random;

public class UserSession {

    private int sessionHandle;
    private String userName;
    private LocalDateTime lastAccess;//последний доступ

    public UserSession(String userName){
        this.userName = userName;
        final Random random = new Random();
        this.sessionHandle = random.nextInt(10000);
        this.lastAccess = LocalDateTime.now();
    }

    public void updateLastAccess(){
        lastAccess = LocalDateTime.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }

    public static void main(String[] args) {
        UserSession userSession = new UserSession("Vasiliy");
        userSession.updateLastAccess();
        System.out.println("lastAccess- " + userSession.lastAccess);
        System.out.println(userSession.getSessionHandle());
        SessionManager ses = new SessionManager(20);

    }
}
