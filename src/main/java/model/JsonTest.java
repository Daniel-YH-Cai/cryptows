package model;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonTest {
    public static String Huobi="{\"ch\":\"market.btcusdt.depth.step1\",\"ts\":1654478785657,\"tick\":{\"bids\":[[30590.7,1.514014],[30589.5,0.044065],[30589.4,0.180384],[30589.2,0.012096],[30588.3,0.003243],[30588.0,0.002067],[30587.4,0.00989],[30585.7,0.31854],[30585.4,0.05],[30584.8,0.001],[30584.6,0.179689],[30584.4,0.01],[30583.9,0.00921],[30583.2,0.05],[30582.2,0.162],[30581.4,0.2],[30580.9,0.03],[30580.6,0.002202],[30580.1,0.010425],[30579.9,0.06]],\"asks\":[[30590.8,0.5273649249430956],[30592.3,0.03],[30593.0,0.01],[30593.8,0.03],[30593.9,2.0E-4],[30594.3,0.03],[30595.5,3.26E-4],[30595.6,0.431285],[30595.9,0.03],[30596.9,0.082],[30597.0,0.162],[30597.5,0.15],[30598.9,0.392023],[30600.0,0.015813],[30602.0,0.04],[30602.3,0.081],[30602.9,0.033663],[30603.0,0.162],[30603.2,0.16],[30603.3,0.56]],\"version\":155889650771,\"ts\":1654478785000}}";
    public static String OKX="{\"arg\":{\"channel\":\"books\",\"instId\":\"BTC-USDT\"}, \"action\":\"update\", \"data\":[{\"asks\":[[\"29775.8\",\"0.00673203\",\"0\",\"1\"],[\"29777.6\",\"0\",\"0\",\"0\"],[\"29866.1\",\"1.46202234\",\"0\",\"3\"],[\"29917.2\",\"0\",\"0\",\"0\"]], \"bids\":[[\"29720.8\",\"0\",\"0\",\"0\"],[\"29720\",\"0\",\"0\",\"0\"],[\"29714.8\",\"0.00613693\",\"0\",\"1\"],[\"29670.6\",\"0\",\"0\",\"0\"],[\"29668.9\",\"0.02673044\",\"0\",\"2\"],[\"29471.6\",\"0.00001131\",\"0\",\"1\"],[\"29471.2\",\"0.00019782\",\"0\",\"1\"]], \"ts\":\"1654430707830\",\"checksum\":1850565387}]}\n";
    public static void main(String[] args) {
        System.out.println(JsonModifier.modify("huobi",Huobi));
        System.out.println(JsonModifier.modify("okx",OKX));
    }
}
