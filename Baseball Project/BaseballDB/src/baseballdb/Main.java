package baseballdb;
import java.io.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
// Main class with the main(String[] args) method
public class Main {
    // Declare a Player class array for storing players data
    public static Player[] playerList;
    // Declare a integer for counting total number of players in database
    public static int playerNum;
    // A setup metod for insert all players data into the Player class array
    public static void setup() throws SAXException, IOException, ParserConfigurationException {
        // Open XML file and use Document class read its data
        File f = new File("src/baseballdb/PlayersData.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(f);
        // Get players number from XML and setup size of playerList
        playerNum = doc.getElementsByTagName("player").getLength();
        playerList = new Player[playerNum];
        // Initial Player objects and add into playerList
        for(int i = 0; i < playerNum; i++){
            playerList[i] = new Player(i);
        }
    }
    // Main class
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // Setup the playerList and playerNum
        setup();
        // Create a GUI for use
        GUI.mainWindow();
    }
}
