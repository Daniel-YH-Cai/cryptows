package timealign;

import exchangehandlers.MessageQueueHandler;
import exchangehandlers.TimeAlignHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

public class HuobiTimeAlign extends TimeAlignApi {
    private final String market="huobi";



    public HuobiTimeAlign(String submitText, String url, TimeAlignHandler handler) {
        super(submitText, url, handler);


    }


    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString text) {
        super.onMessage(webSocket, text);
        String result= MessageQueueHandler.gzip(text);
        if(result.contains("ping")){
            webSocket.send(result.replace("ping","pong"));
        }
        else{
            handler.timeAlign(market,result);
        }
    }
}
