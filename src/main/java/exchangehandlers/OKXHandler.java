package exchangehandlers;

import com.rabbitmq.client.Channel;

//routing_key = "{platform}.{symbol}".format(platform=platform, symbol=symbol)
//queue = "{exchange}.{routing_key}".format(exchange=exchange,routing_key=routing_key)
//https://www.okex.com/api/v5/market/books?instId=BTC-USDT&sz=5
import java.io.IOException;

public class OKXHandler extends MessageQueueHandler{


    public OKXHandler(Channel channel) throws IOException {
        super(channel,"BTC-USDT","okx","Orderbook");
    }

    @Override
    public void handleString(String message) {
        try {
            channel.basicPublish(exchange,routingKey,null,message.getBytes());
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
