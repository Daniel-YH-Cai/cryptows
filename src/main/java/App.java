
import apis.ExchangeAPI;
import apis.HuobiAPI;
import apis.MessageQueue;
import exchangehandlers.HuobiHandler;
import exchangehandlers.MessageQueueHandler;
import exchangehandlers.OKXHandler;
import exchangehandlers.TimeAlignHandler;

import java.io.IOException;
import java.util.logging.Logger;


//https://www.okex.com/api/v5/market/books?instId=BTC-USDT&sz=5
public class App {
    public static String HuobiURL="wss://api.huobi.pro/ws";
    public static String HuobiSubscribe="{\"sub\": \"market.btcusdt.depth.step1\",\"id\": \"id1\"}";
    public static String OKXURL="wss://ws.okx.com:8443/ws/v5/public";
    public static String OKXSubscribe="{\"op\": \"subscribe\",\"args\": [{\"channel\": \"books\",\"instId\": \"BTC-USDT\"}]}";

    public static void main(String[] args) throws IOException {
        MessageQueueHandler huobiHandler=new HuobiHandler(MessageQueue.getConnection().createChannel());
        MessageQueueHandler okxHandler=new OKXHandler(MessageQueue.getConnection().createChannel());
        TimeAlignHandler timeAligner=new TimeAlignHandler(MessageQueue.getConnection().createChannel(),2);
        ExchangeAPI okxAPI=new ExchangeAPI(OKXSubscribe,OKXURL,okxHandler);
        ExchangeAPI huobiAPI=new HuobiAPI(HuobiSubscribe,HuobiURL,huobiHandler);
        System.out.println("Non block");
    }
}
