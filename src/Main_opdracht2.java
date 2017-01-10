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

public class Main_opdracht2 {

    private static final Pattern pattern = Pattern.compile("^[^#]([\\d\\-.]+)");
    private static PrintWriter pw = null;
    private static List<String> passedDoubles = new ArrayList<>();

    public static void main(String[] args)
    {
        try (Stream<String> stream = Files.lines(Paths.get("data.txt"), StandardCharsets.ISO_8859_1)) {
            stream.forEachOrdered(Main_opdracht2::process);
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            pw = new PrintWriter("out.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        passedDoubles.forEach(Main_opdracht2::writeToOutput);
        pw.flush();
        pw.close();
    }

    static void process(String string)
    {
        Matcher matcher = pattern.matcher(string);
        if(!matcher.find())
            return;

        String result = matcher.group(0);
        double dResult = Double.parseDouble(result);
        if(dResult > -2.0 && dResult < 3.0)
        {
            passedDoubles.add(result);
        }
    }

    static void writeToOutput(String string)
    {
        pw.println(string);
    }


}
