package Email;

import constants.Constants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;

public class ContestListener implements ServletContextListener {
    private BackgroundSender backgroundSender = new BackgroundSender();
    private Timer timer;

    public void contextInitialized(ServletContextEvent sce) {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(backgroundSender, Constants.Constant.EmailDelay, Constants.Constant.EmailDelay);
        }
    }

    public void contextDestroyed(ServletContextEvent sce){
    }
}
