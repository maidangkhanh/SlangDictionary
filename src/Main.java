import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import dictionary.*;
public class Main {
    static Dictionary slangDictionary = new Dictionary();
    public static void run() throws IOException {
        int selection;
        Scanner input = new Scanner(System.in);
        try {
            // Menu
            System.out.println("Choose from these choices");
            System.out.println("-------------------------");
            System.out.println("1 - Search meanings of a slang");
            System.out.println("2 - Search slang by meaning");
            System.out.println("3 - Show search history");
            System.out.println("4 - Add slang word");
            System.out.println("5 - Edit a slang word");
            System.out.println("6 - Remove a slang word");
            System.out.println("7 - Reset dictionary");
            System.out.println("8 - Random slang word");
            System.out.println("9 - Quiz: What's that Slang");
            System.out.println("10 - Quiz: What's that Mean");
            System.out.println("0 - Save and exit\n");
            System.out.print("Your choice: ");


            selection = input.nextInt();
            input.nextLine();
            switch (selection) {
                case 1:
                    System.out.println("1 - Search meanings of a slang\nEnter slang to search:");
                    String query1;
                    query1 = input.nextLine();
                    List<String> meanings = slangDictionary.searchSlang(query1);
                    if (meanings == null) {
                        System.out.println("Slang \"" + query1 + "\" is not found");
                    } else {
                        System.out.print(query1);
                        StringBuilder res = new StringBuilder();
                        res.append(": ");
                        for (String i : meanings) {
                            if (meanings.indexOf(i) != 0) res.append(" | ");
                            res.append(i);
                        }
                        System.out.println(res);
                        slangDictionary.saveSlangSearchHistory(query1, res);
                    }
                    break;

                case 2:
                    System.out.println("2 - Search slang by meaning\nEnter meanings to search:");
                    String query2;
                    query2 = input.nextLine();
                    List<String> slangs = slangDictionary.searchMeaning(query2);
                    if (slangs == null) {
                        System.out.println("Meanings \"" + query2 + "\" is not found");
                    } else {
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
                    System.out.println("3 - Show search history\nSearch history:");
                    slangDictionary.showHistory();
                    break;

                case 4:
                    System.out.println("4 - Add slang word\nEnter a slang:");
                    String query4_1 = input.nextLine();
                    System.out.println("Enter its meanings:");
                    String query4_2 = input.nextLine();
                    List<String> list4 = Arrays.asList(query4_2.split("\\|"));
                    slangDictionary.addSlang(query4_1, list4);
                    System.out.println("Operation is completed");
                    break;

                case 5:
                    System.out.println("5 - Edit a slang word\nEnter a Slang");
                    String query5 = input.nextLine();
                    slangDictionary.editSlang(query5);
                    System.out.println("Operation is completed");

                    break;

                case 6:
                    System.out.println("6 - Remove a slang word\nEnter a Slang");
                    String query6 = input.nextLine();
                    slangDictionary.removeSlang(query6);
                    break;

                case 7:
                    System.out.println("7 - Reset dictionary");
                    slangDictionary.resetDictionary();
                    break;

                case 8:
                    System.out.println("8 - Random slang word");
                    String r8 = slangDictionary.randomSlang();
                    System.out.println(slangDictionary.display(r8));
                    break;

                case 9:
                    slangDictionary.quiz1();
                    break;

                case 10:
                    slangDictionary.quiz2();
                    break;
                case 0:
                    slangDictionary.save("slang.txt");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid input. Try again");
            input.nextLine();
        }
        System.out.println("\nPress ENTER to continue");
        input.nextLine();


    }


    public static void main(String[] args) throws IOException {
        slangDictionary.load(Dictionary.slangDirectory);
        while(true)
        {
            run();
        }

    }
}
