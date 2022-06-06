package timealign;

import exchangehandlers.MessageQueueHandler;
import exchangehandlers.TimeAlignHandler;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OKXTimeAlign extends TimeAlignApi {
    private final String market="okx";


    public OKXTimeAlign(String submitText, String url, TimeAlignHandler handler) {
        super(submitText, url, handler);
    }


    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        if(text.contains("asks")){
            handler.timeAlign(market,text);
        }
    }
}
