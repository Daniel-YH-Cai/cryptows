import apis.MessageQueue;
import exchangehandlers.TimeAlignHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import timealign.HuobiTimeAlign;
import timealign.OKXTimeAlign;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;

public class TimeAlignApp {
    public static String HuobiURL="wss://api.huobi.pro/ws";
    public static String HuobiSubscribe="{\"sub\": \"market.btcusdt.depth.step1\",\"id\": \"id1\"}";
    public static String OKXURL="wss://ws.okx.com:8443/ws/v5/public";
    public static String OKXSubscribe="{\"op\": \"subscribe\",\"args\": [{\"channel\": \"books\",\"instId\": \"BTC-USDT\"}]}";

    public static void main(String[] args) throws IOException {
        Configurator.setRootLevel(Level.INFO);
        TimeAlignHandler timeAligner=new TimeAlignHandler(MessageQueue.getConnection().createChannel(),2);
        HuobiTimeAlign huobiAPI=new HuobiTimeAlign(TimeAlignApp.HuobiSubscribe,TimeAlignApp.HuobiURL,timeAligner);
        OKXTimeAlign okxAPI=new OKXTimeAlign(TimeAlignApp.OKXSubscribe,TimeAlignApp.OKXURL,timeAligner);

    }
}
