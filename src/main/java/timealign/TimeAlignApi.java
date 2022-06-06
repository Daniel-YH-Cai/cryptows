package timealign;

import exchangehandlers.MessageQueueHandler;
import exchangehandlers.TimeAlignHandler;
import okhttp3.*;
import okio.ByteString;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class TimeAlignApi extends WebSocketListener {
    private final String submitText;
    private final String url;
    protected final TimeAlignHandler handler;
    private static final Logger log = LogManager.getLogger(TimeAlignApi.class);


    public TimeAlignApi(String submitText, String url, TimeAlignHandler handler) {
        log.debug("Trying to create API");
        Request r= new Request.Builder().url(url).build();
        new OkHttpClient().newWebSocket(r,this);
        this.url=url;
        this.submitText=submitText;
        this.handler=handler;
        log.info("Time align API created!");
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        webSocket.send(submitText);
        try {
            log.debug("Subscribed: "+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
