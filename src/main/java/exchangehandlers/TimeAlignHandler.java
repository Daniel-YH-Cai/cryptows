package exchangehandlers;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import model.JsonModifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class TimeAlignHandler {
    private final HashMap<String,String> messages;
    private final Channel channel;
    private final int exchangeNumber;
    private final String exchange="timealign";
    private final String routingKey="ticker";
    private static final Logger log= LogManager.getLogger(TimeAlignHandler.class);

    public TimeAlignHandler(Channel channel, int exchangeNumber) throws IOException {
        log.atLevel(Level.ALL);
        this.exchangeNumber=exchangeNumber;
        this.channel=channel;
        this.messages=new HashMap<>();
        String queueName=exchange+"."+routingKey;
        channel.queueDeclare(queueName,false,false,true,null);
        log.info("Queue declared with name: "+queueName);
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);
        log.info("Exchange declared with name: "+exchange);
        channel.queueBind(queueName,exchange,routingKey);
    }


    private  String toJSON(){
        StringBuilder sb=new StringBuilder("{");
        for(Map.Entry<String,String> entry:messages.entrySet()){
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\"");
            sb.append(':');
            sb.append(JsonModifier.modify(entry.getKey(),entry.getValue()));
            sb.append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        String result=sb.toString();
        log.info("Ticker published: "+result);
        return result;
    }

    public void timeAlign(String market,String s)  {
        synchronized (messages){
            String message=messages.get(exchange);
            if(message==null){
                messages.put(market,s);
                if(messages.size()==exchangeNumber){
                    log.debug("Ready to publish");
                    try {
                        channel.basicPublish(exchange,routingKey,null,toJSON().getBytes());
                        messages.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            messages.replace(market,s);
        }

    }

}
