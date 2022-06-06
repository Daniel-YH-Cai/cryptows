package apis;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import apis.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MessageQueue {
    private static Logger log= LogManager.getLogger(MessageQueue.class.getName());
    private static Connection connection=MessageQueue.init();

    private static Connection init()  {

        String[] exchanges={"Orderbook", "Trade", "Kline", "Asset", "Index"};
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection= null;
        try {
            connection = factory.newConnection();
            Channel channel=connection.createChannel();
            for(String exchange:exchanges){
                channel.exchangeDeclare(exchange,BuiltinExchangeType.TOPIC);
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        log.info("Connected to ampq:"+connection.toString());
        return connection;
    }
    public static Connection getConnection(){
        return connection;
    }



}
