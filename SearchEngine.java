import java.io.IOException;
import java.net.URI;
import java.util.*;

class SearchHandler implements URLHandler{
    List<String> stringList = new ArrayList<>();
    
    public String handleRequest(URI url){
        if(url.getPath().equals("/")){
            return "A search engine!";
        }else{
            System.out.println("Path:" + url.getPath());
            if(url.getPath().contains("/add")){
                String[] parameters = url.getQuery().split("=");
                if(parameters.length == 1){
                    return "Please specify a word to add.";
                }
                if(parameters[0].equals("s") && !stringList.contains(parameters[1])){
                    stringList.add(parameters[1]);
                }else{
                    return "Word has already been added.";
                }
                return String.format("Added %s to the list of searchable words", parameters[1]);
            }else if(url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")){
                    List<String> goodWords = new ArrayList<>();
                    for(String s : stringList){
                        if(s.contains(parameters[1])){
                            goodWords.add(s);
                        }
                    }
                    if(goodWords.size() == 0){
                        return "No words found!";
                    }else{
                        return String.format("Words containing %s:" + goodWords.toString(), parameters[1]);
                    }
                }
            }
        }
        return null;
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchHandler());
    }
}