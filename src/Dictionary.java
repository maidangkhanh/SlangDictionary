import java.io.IOException;
import java.util.HashMap;
import java.io.*;


public class Dictionary {
    static HashMap<String, String[]> dict = new HashMap<String, String[]>();

    public static void load(String fileName) throws IOException {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("slang.txt"));
            String line;
            while((line=bufferedReader.readLine())!=null) {
                String[] combo = line.split("`");
                dict.put(combo[0], combo[1].split("\\|"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
