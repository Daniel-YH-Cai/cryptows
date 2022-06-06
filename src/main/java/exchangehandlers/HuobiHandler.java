package exchangehandlers;

import com.rabbitmq.client.Channel;
import okio.ByteString;

import java.io.IOException;

//routing_key = "{platform}.{symbol}".format(platform=platform, symbol=symbol)
//queue = "{exchange}.{routing_key}".format(exchange=exchange,routing_key=routing_key)
//market.btcusdt.depth.step1
//https://api.huobi.pro/market/depth?symbol=btcusdt&depth=5&type=step1
public  class HuobiHandler extends MessageQueueHandler {


    public HuobiHandler(Channel channel) throws IOException {
        super(channel,"btcusdt","huobi","Orderbook");
    }


    @Override
    public void handle(ByteString message)  {
        String result=gzip(message);
        System.out.println(result);
        try {
            channel.basicPublish(exchange,routingKey,null,result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
