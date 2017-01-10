import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main_opdracht3 {

    private static final Pattern pattern = Pattern.compile("^(?:\\+|(?:00))31(\\d{9})|(?:0(\\d{9}))$");
    private static PrintWriter pw = null;
    private static List<String> passedNumbers = new ArrayList<>();

    public static void main(String[] args)
    {
        try (Stream<String> stream = Files.lines(Paths.get("phone.txt"), StandardCharsets.ISO_8859_1)) {
            stream.forEachOrdered(Main_opdracht3::process);
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            pw = new PrintWriter("phone_out.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        passedNumbers.forEach(Main_opdracht3::writeToOutput);
        pw.flush();
        pw.close();
    }

    static void process(String string)
    {
        string = string.replaceAll("[ -]","");
        Matcher matcher = pattern.matcher(string);
        if(!matcher.find())
            return;

        String result = matcher.group(1) == null ? matcher.group(2): matcher.group(1);
        passedNumbers.add("0" + result);
    }

    static void writeToOutput(String string)
    {
        pw.println(string);
    }


}
