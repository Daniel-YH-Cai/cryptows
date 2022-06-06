package apis;

import exchangehandlers.MessageQueueHandler;
import jdk.swing.interop.SwingInterOpUtils;
import okhttp3.WebSocket;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HuobiAPI extends ExchangeAPI{

    public HuobiAPI(String submitText, String url, MessageQueueHandler handler) {
        super(submitText, url, handler);
    }
    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
        String result=MessageQueueHandler.gzip(bytes);
        System.out.println(result);
        if(result.contains("ping")){
            StringBuilder pong=new StringBuilder(result);
            System.out.println("Ponged");
            webSocket.send(result.replace("ping","pong"));
        }
        else{
            handler.handle(bytes);
        }
    }
}
