package apis;

import exchangehandlers.HuobiHandler;
import exchangehandlers.MessageQueueHandler;
import exchangehandlers.TimeAlignHandler;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Time;

public class ExchangeAPI extends WebSocketListener {
    private final String submitText;
    private final String url;
    protected final MessageQueueHandler handler;

    public ExchangeAPI(String submitText, String url, MessageQueueHandler handler) {
        Request r= new Request.Builder().url(url).build();
        new OkHttpClient().newWebSocket(r,this);
        this.url=url;
        this.submitText=submitText;
        this.handler=handler;
        System.out.println("API created!");
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        super.onOpen(webSocket, response);
        webSocket.send(submitText);
        System.out.println("Open!");
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);


    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);
    }
}
