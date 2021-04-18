package consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {
    @RabbitListener(queues = "link")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    public void receive(String in, int receiver) {
        System.out.println("instance " + receiver + " [x] Received '" + in + "'");
        Long inLinkId = Long.parseLong(in.substring(in.indexOf("linkId")+8, in.indexOf("url")-2));
        String inUrl = in.substring(in.indexOf("url")+6, in.length()-2).replace("\\", "");
        System.out.println(inLinkId+ "aaa"+ inUrl);

    }
}
