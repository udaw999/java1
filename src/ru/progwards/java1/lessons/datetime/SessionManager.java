package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private List<UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession){
        sessions = new ArrayList<>();
        sessions.add(userSession);
    }

    public UserSession find(String userName){
        for (UserSession session : sessions) {
            if (session.getUserName().equals(userName)) {
               /* LocalDateTime dateTimeUser = session.getLastAccess().plusSeconds(sessionValid);
                LocalDateTime dateTimeNow = LocalDateTime.now();
                if (dateTimeUser.compareTo(dateTimeNow) > 0) {
                    System.out.println("tyt");

                    return session;

                }
                //System.out.println(dateTimeUser.compareTo(dateTimeNow));
                //System.out.println(session);
                //System.out.println("dateTimeUser- " + dateTimeUser);
*/                  session.updateLastAccess();
                return session;
            }
        }
        return null;
    }

    public UserSession get(int sessionHandle){
        for (UserSession session : sessions) {
            if (session.getSessionHandle() == sessionHandle) {
                session.updateLastAccess();
              return session;
            }
        }
        return null;
    }

    public void delete(int sessionHandle){
        for (UserSession session : sessions) {
            if (session.getSessionHandle() == sessionHandle) {
                sessions.remove(session);

            }
        }
    }

    public void deleteExpired(){

        for (UserSession session : sessions) {
            LocalDateTime dateTimeUser = session.getLastAccess().plusSeconds(sessionValid);
                LocalDateTime dateTimeNow = LocalDateTime.now();
                if (dateTimeUser.compareTo(dateTimeNow) < 0) {

                    sessions.remove(session);
                }
        }
    }

    public static void main(String[] args) {
        SessionManager session1 = new SessionManager(300);
        session1.add(new UserSession("VOVA"));
        System.out.println(session1.find("VOVA"));
        //session1.delete(6207);
        System.out.println(session1.sessions);

    }
}
