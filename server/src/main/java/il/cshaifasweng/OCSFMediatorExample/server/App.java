package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException, InterruptedException {
        server = new SimpleServer(3000);

        String thread_name = "serviceThread";
        Thread mails_thread = new Thread(new MailServiceThread(), thread_name);

        mails_thread.start();

        server.listen();
    }
}
