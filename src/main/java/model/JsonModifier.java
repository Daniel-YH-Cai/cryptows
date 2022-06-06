package model;

public class JsonModifier {
    public static String modify(String market,String input){
        if(market.equals("huobi")){
            StringBuilder result=new StringBuilder(input);
            int start=result.indexOf("{\"bids\"");
            return result.substring(start,result.length()-2)+"}";
        }
        else if(market.equals("okx")){
            StringBuilder result=new StringBuilder(input);
            int start=result.indexOf("{\"asks\"");
            int end=result.indexOf(",\"checksum");
            String tmp=result.substring(start,end)+"}";
            tmp=tmp.replaceAll("\",\"",",");
            tmp=tmp.replaceAll("\"]","]");
            tmp=tmp.replaceAll("\\[\"","[");
            return tmp;
        }
        return null;
    }
}
