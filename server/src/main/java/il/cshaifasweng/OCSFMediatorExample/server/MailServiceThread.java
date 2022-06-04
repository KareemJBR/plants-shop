package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.Timer;

public class MailServiceThread implements Runnable {
    @Override
    public void run() {
        Timer timer = new Timer();
        EmailScheduler emailScheduler = new EmailScheduler();
        timer.scheduleAtFixedRate(emailScheduler, 0, 5000);
    }
}
