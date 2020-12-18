package baseballdb;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;


public class Player {
    private final int rank;
    private final String name;
    private final String born;
    private final String height;
    private final String team;
    private final int number;
    private final String position;
    private final double avg;

    protected Player(int index) throws SAXException, IOException, ParserConfigurationException {
        // Open XML file and use Document class read its data
        File f = new File("src/baseballdb/PlayersData.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(f);
        // Get specify players data from XML
        Element player = (Element)doc.getElementsByTagName("player").item(index);
        // Sorting data into object's variables
        this.rank = Integer.valueOf(player.getAttribute("rank"));
        this.name = player.getAttribute("name");
        this.born = player.getAttribute("born");
        this.height = player.getAttribute("height");
        this.team = player.getAttribute("team");
        this.number = Integer.valueOf(player.getAttribute("number"));
        this.position = player.getAttribute("position");
        //this.avg = player.getAttribute("avg");
        this.avg = Double.valueOf(player.getAttribute("avg"));
    }
    // Accessor methods
    public int getRank(){
        return this.rank;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getBorn(){
        return this.born;
    }
    
    public String getHeight(){
        return this.height;
    }
    
    public String getTeam(){
        return this.team;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public String getPosition(){
        return this.position;
    }
    
    public double getAvg(){
        return this.avg;
    }
    public String getData(){
        return String.format("Player: %s\nRank: %d\nDate of Birth: %s\nHeight: %s\nTeam: %s\nPlayer Number: %d\nPosition: %s\nAvg: %.3f\n",
                this.name, this.rank, this.born, this.height, this.team, this.number, this.position, this.avg);
    }
    // Method for displaying players data
    public void showData(){
        System.out.printf("Player: %s\n", this.name);
        System.out.printf("Rank: %d\n", this.rank);
        System.out.printf("Date of Birth: %s\n", this.born);
        System.out.printf("Height: %s\n", this.height);
        System.out.printf("Team: %s\n", this.team);
        System.out.printf("Player Number: %d\n", this.number);
        System.out.printf("Position: %s\n", this.position);
        System.out.printf("Avg: %.3f\n", this.avg);
    }
}
