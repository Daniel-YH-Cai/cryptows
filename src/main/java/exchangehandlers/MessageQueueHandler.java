package exchangehandlers;

import com.rabbitmq.client.Channel;
import okio.ByteString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class MessageQueueHandler {
    protected final Channel channel;
    protected final String symbol;
    protected final String platform;
    protected final String exchange;
    protected final String routingKey;

    public MessageQueueHandler(Channel channel, String symbol, String platform, String exchange) throws IOException {
        this.channel = channel;
        this.symbol = symbol;
        this.platform = platform;
        this.exchange = exchange;
        this.routingKey= platform+"."+symbol;
        String queueName=exchange+"."+routingKey;
        channel.queueDeclare(queueName,false,false,true,null);
        channel.queueBind(queueName,exchange,routingKey);
    }

    public static void zlib(ByteString message) {
        Inflater decompress = new Inflater();
        decompress.setInput(message.asByteBuffer());
        byte[] buffer = new byte[1024];
        try {
            decompress.inflate(buffer);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    public static String gzip(ByteString message) {
        var byteIn = new ByteArrayInputStream(message.toByteArray());
        try {
            var gzipIn = new GZIPInputStream(byteIn);
            ByteArrayOutputStream decompressedBytes = new ByteArrayOutputStream();
            int n = -1;
            byte[] buff = new byte[256];
            while ((n = gzipIn.read(buff)) > 0) {
                decompressedBytes.write(buff, 0, n);
            }
            String result = String.valueOf(decompressedBytes);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public void handle(ByteString message){
        System.out.println("Wrong message type: Should be String");
    }

    public void handleString(String message){
        System.out.println("Wrong message type: Should be Bytes");
    }
}
