import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dictionary.*;
public class Main {

    public static void run() throws IOException {
        Dictionary slangDictionary = new Dictionary();
        slangDictionary.load("slang.txt");
        int selection;
        Scanner input = new Scanner(System.in);
        // Menu
        System.out.println("Choose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Search meanings of a slang");
        System.out.println("2 - Search slangs by meaning");
        System.out.println("3 - Show search history");
        System.out.println("4 - Add slang word");

        selection = input.nextInt();
        input.nextLine();
        switch (selection)
        {
            case 1:
                System.out.println("Enter slang to search:");
                String query1;
                query1  = input.nextLine();
                List<String> meanings = slangDictionary.searchSlang(query1);
                if(meanings==null)
                {
                    System.out.println("Slang \"" + query1 + "\" is not found");
                }
                else {
                    System.out.print(query1);
                    StringBuilder res = new StringBuilder();
                    res.append(": ");
                    for (String i : meanings) {
                        if (meanings.indexOf(i) != 0) res.append(" | ");
                        res.append(i);
                    }
                    System.out.println(res);
                    slangDictionary.saveSlangSearchHistory(query1,res);
                }
                break;

            case 2:
                System.out.println("Enter meanings to search:");
                String query2;
                query2  = input.nextLine();
                List<String> slangs = slangDictionary.searchMeaning(query2);
                if(slangs==null){
                    System.out.println("Meanings \"" + query2 + "\" is not found");
                }
                else {
                    System.out.print(query2);
                    StringBuilder res = new StringBuilder();
                    res.append(": ");
                    for (String i : slangs) {
                        if (slangs.indexOf(i) != 0) res.append(" | ");
                        res.append(i);
                    }
                    System.out.println(res);
                }
                break;

            case 3:
                System.out.println("Search history:");
                System.out.println("Slang: Meanings");
                slangDictionary.showHistory();
                break;

            case 4:
                System.out.println("Enter a slang:");
                String query4_1 = input.nextLine();
                System.out.println("Enter its meanings:");
                String query4_2 = input.nextLine();
                List<String> list4 = Arrays.asList(query4_2.split("\\|"));
                slangDictionary.addSlang(query4_1,list4);
                break;

            case 5:

                break;

            case 6:

                break;

            case 7:

                break;

            case 8:

                break;

            case 9:

                break;

            case 10:

                break;
            case 0:
                break;
            default:
                break;
        }

    }


    public static void main(String[] args) throws IOException {
        run();
    }
}
