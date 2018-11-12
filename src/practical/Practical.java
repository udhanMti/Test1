package practical;
import java.util.*;
class News{
  private String date;
  private String headline;
  private String journalistName;
  private String newsText;
  private String newsType;
  News(String jName,String type){
     this.journalistName=jName;
     this.newsType=type;
  }
  public void setHeadLine(String hLine){
    this.headline=hLine;
  }
  public void setNewsText(String text){
    this.newsText=text;
  }
  public String getNewsType(){
    return newsType;
  }
}

class ServerBase{
    private ArrayList<Server> servers;
    ServerBase(){
      servers=new ArrayList<Server>();
    }
    public void addServer(Server s){
       servers.add(s);
    }
    public void addNews(News n){
       AddNewsToServer(n);
    }
    public void AddNewsToServer(News n){
       String newsType=n.getNewsType();
       for(Server s:servers){
         if(s.getType().equals(newsType)){
           s.addNews(n);
         }
       }
    }
    public void subscribeClients(Client c){
       String clientType=c.getType();
       for(Server s:servers){
         if(s.getType().equals(clientType)){
           s.addObserver(c);
         }
       }
    }
}
class Server extends Observable{
    private String name;
    private String type;
    private ArrayList<News> newsList;
    Server(String name,String type){
       this.name=name;
       this.type=type;
       newsList=new ArrayList<News>();
    }
    public String getType(){ return type;}
    public void addNews(News n){
      newsList.add(n);
      setChanged();
      notifyObservers(n);
    }
}
class GeneralServer extends Server{
    GeneralServer(String name,String type){
       super(name,type);
    }
}
class PoliticsServer extends Server{
    PoliticsServer(String name,String type){
       super(name,type);
    }
}
class BusinessServer extends Server{
    BusinessServer(String name,String type){
       super(name,type);
    }
}
class TechnologyServer extends Server{
    TechnologyServer(String name,String type){
       super(name,type);
    }
}
class ArtServer extends Server{
    ArtServer(String name,String type){
       super(name,type);
    }
}
class SportServer extends Server{
    SportServer(String name,String type){
       super(name,type);
    }
}
class Client implements Observer{
    private String name;
    private String type;
    Client(String name,String type){
      this.name=name;
      this.type=type;
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Got a "+type+" news -("+name+')');
    }
    public String getType(){ return type;}
}
public class Practical {
    public static void main(String[] args) {
       ServerBase sb=new ServerBase(); 
       ArtServer as=new ArtServer("Sirasa","Art");
       GeneralServer gs=new GeneralServer("Derana","General");
       PoliticsServer ps=new PoliticsServer("ITN","Politics");
       SportServer ss=new SportServer("CSN","Sports");
       
       sb.addServer(as);
       sb.addServer(gs);
       sb.addServer(ps);
       sb.addServer(ss);
       
       Client c1=new Client("Chamindu","Art");
       Client c2=new Client("Nipun","Sports");
       Client c3=new Client("Sandun","Politics");
       
       sb.subscribeClients(c1);
       sb.subscribeClients(c2);
       sb.subscribeClients(c3);
       
       News n1=new News("Nimal","Art");
       sb.addNews(n1);
       
       News n2=new News("Kamal","Politics");
       sb.addNews(n2);
       
       News n3=new News("Saman","General");
       sb.addNews(n3);
    }
}