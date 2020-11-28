package dictionary;

import java.io.IOException;
import java.util.*;
import java.io.*;


public class Dictionary {
    static HashMap<String, List<String>> slangDict = new HashMap<String, List<String>>();
    static HashMap<String, List<String>> meaningDict = new HashMap<String, List<String>>();
    static final String backupSlangDirectory = "backup.txt";
    static final String historyDirectory = "history.txt";

    public void load(String fileName){
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

    public void save(String fileName)
    {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            StringBuffer stringBuffer = new StringBuffer();
            for (String key: slangDict.keySet()){
                stringBuffer.append(key);
                stringBuffer.append("`");
                for(String meaning: slangDict.get(key)){
                    if (slangDict.get(key).indexOf(meaning)!=0) stringBuffer.append("\\|");
                    stringBuffer.append(meaning);
                }
                stringBuffer.append(System.getProperty("line.separator"));
            }
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (Exception e){
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

    public void addSlang(String slang, List<String> meanings){
        slang = slang.toUpperCase();
        if(slangDict.containsKey(slang))
        {
            System.out.println(slang +" has already existed.");
            System.out.println("Choose an action:");
            System.out.println("1 - Overwrite");
            System.out.println("2 - Duplicate");
            System.out.println("Any other keys - Cancel");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice){
                case 1:
                    slangDict.put(slang,meanings);
                    break;
                case 2:
                    List<String> values = slangDict.get(slang);
                    values.addAll(meanings);
                    break;
                default:
                    break;
            }
        }
        else
        {
            slangDict.put(slang,meanings);
        }
    }

    public void editSlang(String slang){
         if(slangDict.containsKey(slang)){
             System.out.println("Enter new meanings:");
             Scanner input = new Scanner(System.in);
             String mean = input.nextLine();
             List<String> list = Arrays.asList(mean.split("\\|"));
             slangDict.put(slang,list);
         }
         else{
             System.out.println(slang+" not in dictionary.");
         }
    }

    public void removeSlang(String slang){
        if (slangDict.containsKey(slang)){
            System.out.println("Confirm remove " + slang +" from dictionary");
            System.out.print("(Y/N)");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            choice = choice.trim();
            if(choice.equals("Y")) {
                slangDict.remove(slang);
                System.out.println(slang + " removed");
            }
        }
        else{
            System.out.println(slang+" not in dictionary.");
        }
    }

    public void resetDictionary(){
        this.load(backupSlangDirectory);
    }


    public String randomSlang(){
        Random random = new Random();
        List<String> keys = new ArrayList<String>(slangDict.keySet());

        return keys.get(random.nextInt(keys.size()));
    }

}
