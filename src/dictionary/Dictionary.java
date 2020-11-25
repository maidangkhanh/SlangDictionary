package dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.*;
import java.util.List;


public class Dictionary {
    static HashMap<String, List<String>> slangDict = new HashMap<String, List<String>>();
    static HashMap<String, List<String>> meaningDict = new HashMap<String, List<String>>();
    static final String slangDirectory = "slang.txt";
    static final String historyDirectory = "history.txt";
    static final String meaningsDirectory = "meanings.txt";

    public void load(String fileName) throws IOException {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line=bufferedReader.readLine())!=null) {
                if(line.contains("`"))
                {
                    // Create slangDict
                    String[] combo = line.split("`");
                    String key = combo[0];
                    String[] meanings = combo[1].split("\\|");
                    for(int i =0; i<meanings.length; i++){
                        meanings[i]=meanings[i].trim();
                    }
                    List<String> values = Arrays.asList(meanings);
                    slangDict.put(key, values);

                    // Create meaningDict
                    for(String i: values){
                        List<String> slang;
                        if(!meaningDict.containsKey(i)){
                            slang = new ArrayList<>();
                            slang.add(key);
                            meaningDict.put(i,slang);
                        }
                        else{
                            slang = meaningDict.get(i);
                            slang.add(combo[0]);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchSlang(String slang){
        List<String> res = null;
        if (slangDict.containsKey(slang))
        {
            res = slangDict.get(slang);
        }
        return res;
    }

    public List<String> searchMeaning(String meaning){
        List<String> res = null;
        if (meaningDict.containsKey(meaning))
        {
            res = meaningDict.get(meaning);
        }
        return res;
    }

    public void saveSlangSearchHistory(String query, StringBuilder result) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(historyDirectory));
        String stringBuffer = query +
                result +
                System.getProperty("line.separator");
        bufferedWriter.append(stringBuffer);
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    public void showHistory() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(historyDirectory));
        String line;
        while((line=bufferedReader.readLine())!=null) {
            System.out.println(line);
        }
    }



    public boolean addSlang(String slang, String meaning) {

        if (slangDict.containsKey(slang)) {

        } else {
            List<String> meanings = new ArrayList<>();
            meanings.add(meaning);
            slangDict.put(slang, meanings);
            return true;
        }
        return false;
    }

    public void showAll()
    {

    }

}
