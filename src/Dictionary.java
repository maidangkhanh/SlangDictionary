import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.*;


public class Dictionary {
    static HashMap<String, String[]> dict = new HashMap<String, String[]>();

    public void load(String fileName) throws IOException {
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

    public String[] searchSlang(String slang){
        String[] defs = {};
        if (dict.containsKey(slang))
        {
            defs = dict.get(slang);
        }
        return defs;
    }

    public ArrayList<String> searchDefinition(String def){
        ArrayList<String> slangs = new ArrayList<String>();
        for(String i:dict.keySet())
        {
            String[] values = dict.get(i);
            for(String j:values)
            {
                if(j.equals(def))
                    slangs.add(i);
            }
        }
        return slangs;
    }



}
