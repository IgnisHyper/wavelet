import java.net.URI;
import java.util.*;

public class SeachEngine implements URLHandler{
    List<String> stringList = new ArrayList<>();
    
    public String handleRequest(URI url){
        if(url.getPath().equals("/")){
            return "A search engine!";
        }else{
            System.out.println("Path:" + url.getPath());
            if(url.getPath().contains("/add")){
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s") && !stringList.contains(parameters[1])){
                    stringList.add(parameters[1]);
                }
            }else if(url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")){
                    List<String> goodWords = new ArrayList<>();
                    for(String s : stringList){
                        if(s.contains(parameters[1])){
                            goodWords.add(s);
                        }
                    }
                    return goodWords.toString();
                }
            }
        }
        return null;
    }
}
