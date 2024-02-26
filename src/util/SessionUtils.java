package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static boolean isUserConnected(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Object user = session.getAttribute("user");
        return user != null;
    }
    
}
