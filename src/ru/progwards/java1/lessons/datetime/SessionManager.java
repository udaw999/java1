package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionManager {
    private List<UserSession> sessions = new ArrayList<>();
    private int sessionValid;

    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession){

        if (sessions.contains(userSession) == false){
            sessions.add(userSession);
            System.out.println(sessions.contains(userSession));
        }

    }

    public UserSession find(String userName){
        if (sessions != null){
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
        }

        return null;
    }

    public UserSession get(int sessionHandle){
        if (sessions != null){
            for (UserSession session : sessions) {
                LocalDateTime dateTimeUser = session.getLastAccess().plusSeconds(sessionValid);
                LocalDateTime dateTimeNow = LocalDateTime.now();
                if (dateTimeUser.compareTo(dateTimeNow) > 0) {
                    if (session.getSessionHandle() == sessionHandle) {
                        session.updateLastAccess();
                        return session;
                    }
                }

            }
        }

        return null;
    }

    public void delete(int sessionHandle){
        if (sessions != null){

            Iterator<UserSession> iteratorSesions = sessions.iterator();
            while (iteratorSesions.hasNext()) {
                UserSession sesion = iteratorSesions.next();
                if (sesion.getSessionHandle() == sessionHandle) {
                    iteratorSesions.remove();

                }

            }

        }

    }

    public void deleteExpired(){
        if (sessions != null){

            Iterator<UserSession> iteratorSesions = sessions.iterator();
            while (iteratorSesions.hasNext()) {
                UserSession sesion = iteratorSesions.next();
                LocalDateTime dateTimeUser = sesion.getLastAccess().plusSeconds(sessionValid);
                LocalDateTime dateTimeNow = LocalDateTime.now();
                if (dateTimeUser.compareTo(dateTimeNow) < 0) {
                    iteratorSesions.remove();

                }

            }
//            for (UserSession session : sessions) {
//                LocalDateTime dateTimeUser = session.getLastAccess().plusSeconds(sessionValid);
//                LocalDateTime dateTimeNow = LocalDateTime.now();
//                if (dateTimeUser.compareTo(dateTimeNow) < 0) {
//
//                    sessions.remove(session);
//                }
//            }
        }

    }

    public static void main(String[] args) {
        SessionManager session1 = new SessionManager(300);
        //session1.add(new UserSession("VOVA"));
        //session1.add(new UserSession("KOLY"));
       // System.out.println(session1.find("v"));
        //session1.delete(6207);
        System.out.println("get - " + session1.get(8063));

        System.out.println(session1.sessions);


    }
}
