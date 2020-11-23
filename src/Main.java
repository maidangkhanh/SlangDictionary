import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
//        try{
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("slang.txt"));
//            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
//            String line;
//            while((line=bufferedReader.readLine())!=null)
//            {
//                String[] combo = line.split("`");
//                for(String i: combo)
//                    System.out.println(i);
//                sb.append(line);      //appends line to string buffer
//                sb.append("\n");     //line feed
//            }
//
//            System.out.println("Contents of File: ");
////            System.out.println(sb.toString());
//            //returns a string that textually represents the object
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        String s = "123467890`qwertyuiop";
        String[] a = s.split("`");
        String[] b = s.split("\\|");

        for(String i:a)
            System.out.println(i);
        for(String i:b)
            System.out.println(i);
        System.out.println(s);

    }
}
