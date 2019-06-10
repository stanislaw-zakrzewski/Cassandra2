package data;

import com.example.demo.model.Match;
import com.example.demo.service.MatchService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class DataInsertApp {

    public static String insert(MatchService service) {
        List<String> urls = new ArrayList<>();
        urls.add("http://www.football-data.co.uk/mmz4281/1819/E0.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1819/E1.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1819/E2.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1819/E3.csv");

        urls.add("http://www.football-data.co.uk/mmz4281/1718/E0.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1718/E1.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1718/E2.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1718/E3.csv");

        urls.add("http://www.football-data.co.uk/mmz4281/1617/E0.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1617/E1.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1617/E2.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1617/E3.csv");

        urls.add("http://www.football-data.co.uk/mmz4281/1516/E0.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1516/E1.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1516/E2.csv");
        urls.add("http://www.football-data.co.uk/mmz4281/1516/E3.csv");

        CSVParser parser = new CSVParser();

        for (String url : urls) {

            URL website;
            ReadableByteChannel rbc;
            FileOutputStream fos;
            try {
                File f = new File("file");
                website = new URL(url);
                rbc = Channels.newChannel(website.openStream());
                fos = new FileOutputStream(f);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();


                List<Match> matches = parser.parseMatchF(f);
                for (Match match: matches) {
                    service.saveOrUpdate(match);
                    System.out.println("added match");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "initialized";
    }


    private void listFilesForFolder(final File folder) {

    }
}
