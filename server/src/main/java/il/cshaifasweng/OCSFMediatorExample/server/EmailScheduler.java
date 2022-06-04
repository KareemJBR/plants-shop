package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Order;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import static il.cshaifasweng.OCSFMediatorExample.server.SimpleServer.getAllOrders;

public class EmailScheduler extends TimerTask {

    private Calendar current;
    private int cnt = 0;

    @Override
    public void run() {
        current = Calendar.getInstance();
        cnt++;

        if (cnt == 30) {        // checks the time every 30 seconds
            List<Order> all_orders = null;
            try {
                all_orders = getAllOrders();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (all_orders == null)
                return;

            for (int i = all_orders.size() - 1;i >= 0;i--) {
                if (all_orders.get(i).isMessage_sent() || all_orders.get(i).isGot_cancelled() ||
                        all_orders.get(i).isDelivery_for_Client()) {
                    continue;
                }

                int order_d = all_orders.get(i).getOrder_day();
                int order_month = all_orders.get(i).getOrder_month();
                int order_year = all_orders.get(i).getOrder_year();
                int order_hour = all_orders.get(i).getOrder_hour();
                int order_minute = all_orders.get(i).getOrder_minute();

                Calendar order_cal = Calendar.getInstance();
                // months in Calendar start from 0 not 1
                order_cal.set(order_year, order_month - 1, order_d, order_hour, order_minute, 0);

                if (order_cal.before(current)) {
                    String message_body = "Hello " + all_orders.get(i).getCustomer().getFirst_name() + ",\n\n";
                    message_body += "We are pleased to tell that the order you asked has just been delivered.\n\n";
                    message_body += "Regards,\nLilac";

                    // we shall send an email
                    SendMail sendMail = new SendMail(all_orders.get(i).getCustomer().getEmail(),
                            "Order Complete", message_body);

                    sendMail.sendMessage();
                    all_orders.get(i).setMessage_sent();
                }

            }

        }
    }
}
