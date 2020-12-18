package baseballdb;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.*;
// Class for managing GUI and windows action
public class GUI {
    // Main Window's JFrame Object
    public static JFrame main = new JFrame("Baseball Player Database");
    // Method for detecting text type is int, double or string
    public static char textType(String str){
        // If keyword is emtpy then return a 'n' as nothing
        if(str.isEmpty())
            return 'n';
        // Convert string to char array
        char[] charArr = str.toCharArray();
        // Declare boolean variable for text type
        boolean isNumber = true;
        boolean isDouble = false;
        // Declare int variable for counting decimal point (period) in the text 
        int point = 0;
        for(int i = 0; i < charArr.length; i++){
            // Checking text type
            if(isNumber){
                // If found non-numberic char and it is not a decimal point then break loop and change isNumber to F
                if(!Character.isDigit(charArr[i]) && charArr[i] != '.'){
                    isNumber = false;
                }
                // If found non-numberic char but it is a decimal point and point count is 0
                // then change isDouble to T and point + 1
                else if(charArr[i] == '.' && point < 1){
                    point += 1;
                    isDouble = true;
                }
                // If found another point and point count is 1 or larger then break loop and change isNumber to F
                else if(charArr[i] == '.' && point >= 1){
                    isNumber = false;
                }
            }
        }
        // When isNumber is F return char 's' stand for String
        if(!isNumber)
            return 's';
        // When isDouble is T return char 'f' stand for double
        else if(isDouble)
            return 'f';
        // In other case return 'd' stand for int
        else
            return 'd';
    }
    // ArrayList for containing the player index num of searching result
    public static ArrayList<Integer> search_result;
    // Method For seraching keyword is contain in players data
    public static void search(String key) {
        // Refresh search_result array list
        search_result = new ArrayList<Integer>();
        // Use switch check keyword type, and search them in the right field 
        switch(textType(key)){
            // If keyword is string, then search in name, born, height, team and position
            case 's':
                for(int i = 0; i < Main.playerNum; i++){
                    if(Main.playerList[i].getName().contains(key) 
                            || Main.playerList[i].getBorn().contains(key)
                            || Main.playerList[i].getHeight().contains(key)
                            || Main.playerList[i].getTeam().contains(key)
                            || Main.playerList[i].getPosition().contains(key))
                        search_result.add(i);
                }
                break;
            // If keyword is double, then only search in avg field
            case 'f':
                System.out.println("dddd");
                for(int i = 0; i < Main.playerNum; i++){
                    //if((Main.playerList[i].getAvg().contains(key)));
                    if(Main.playerList[i].getAvg() == Double.valueOf(key));
                    System.out.printf("%d - %.3f - key: %s\n", i, Main.playerList[i].getAvg(), key);
                        search_result.add(i);
                }
                break;
            // If keyword is int, then serach in number and rank field
            case 'd':
                for(int i = 0; i < Main.playerNum; i++){
                    if(Main.playerList[i].getRank() == Integer.valueOf(key)
                            || Main.playerList[i].getNumber() == Integer.valueOf(key))
                        search_result.add(i);
                }
                break;
            // Do nothing to make result array list empty when got a 'n' 
            default:
                break;
        }
    }
    // Method For create and write result file
    public static void output(String key) throws IOException{
        // Set output dirtory
        String dir = "src/";
        // Create a Date and date format object for display the created type in output file name 
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("HH_mm_ss_MM_dd_yyyy");
        String f_name = String.format("Baseball Player Database_%s.txt", sdf.format(d));
        // Create a output txt file
        File f = new File(String.format("%s%s", dir, f_name));
        if(!f.exists()){
            f.createNewFile();
        }
        // Write on output file
        PrintWriter pw = new PrintWriter(f);
        pw.printf("Search keyword: %s\n", key);
        pw.print("==================================\n");
        for(int i = 0; i < search_result.size(); i++){
            int ind = search_result.get(i);
            pw.println(Main.playerList[ind].getData());
            pw.print("==================================\n");
        }
        pw.close();
    }
    // Method for setup the window
    public static void mainWindow() {
        // Set default close operation
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set GUI components
        JLabel search_lb = new JLabel("Keyword:"); // 1
        search_lb.setBounds(10, 50, 70, 30);
        JTextField search_tf = new JTextField(); // 2
        search_tf.setBounds(70, 50, 150, 30);
        JButton search_bt = new JButton("Search!"); // 3
        search_bt.setBounds(230, 50, 100, 30);
        JLabel title = new JLabel("Click search to output result into a txt file"); // 4
        title.setBounds(10, 10, 320, 30);
        // Set buttons action
        search_bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                search(search_tf.getText());
                if(search_result.isEmpty()){
                    JOptionPane.showMessageDialog(main, "No result found, will not output txt file.", "No Result", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        output(search_tf.getText());
                        FileSystemView fsv = FileSystemView.getFileSystemView();
                        JOptionPane.showMessageDialog(main, "The result file is created!", "Output Success", JOptionPane.DEFAULT_OPTION);
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        // Add GUI components into main windows
        main.getContentPane().add(search_lb); // 1
        main.getContentPane().add(search_tf); // 2
        main.getContentPane().add(search_bt); // 3
        main.getContentPane().add(title); // 4
        // Set window size
        main.setSize(400, 150);
        main.setLayout(null);
        main.setVisible(true);
    }
    
}
