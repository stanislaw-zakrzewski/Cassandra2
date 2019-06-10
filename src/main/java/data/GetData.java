package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class GetData {

    /**
     * Download data from defined list of files. It contains results of English:
     * Premier League, Championship, League 1, League 2
     * from season 2010/2011 to 2018/2019
     * NOTE: can be increased in near future
     * @param outputFolder - path to existing folder where created files will be storage
     */
    public void downloadDefinedFiles(String outputFolder) {
        List<String> url = new ArrayList<>();
        url.add("http://www.football-data.co.uk/mmz4281/1819/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1819/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1819/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1819/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1718/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1718/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1718/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1718/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1617/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1617/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1617/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1617/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1516/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1516/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1516/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1516/E3.csv");
/*
        url.add("http://www.football-data.co.uk/mmz4281/1415/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1415/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1415/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1415/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1314/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1314/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1314/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1314/E3.csv");

       url.add("http://www.football-data.co.uk/mmz4281/1213/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1213/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1213/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1213/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1112/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1112/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1112/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1112/E3.csv");

        url.add("http://www.football-data.co.uk/mmz4281/1011/E0.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1011/E1.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1011/E2.csv");
        url.add("http://www.football-data.co.uk/mmz4281/1011/E3.csv");*/

        downloadMultipleFiles(url, outputFolder);
    }

    /**
     * Download data from given list of files and storage in it path
     * @param urls - list of urls of files to download
     * @param outputFolder - path to existing folder where created files will be storage
     */
    public void downloadMultipleFiles(List<String> urls, String outputFolder) {
        for (int i = 0; i < urls.size(); i++) {
            downloadSingeFile(urls.get(i), outputFolder + "/out" + i + ".csv");
        }
    }

    /**
     * Download single file from URLPtah to outputPath
     * @param pathToFile - url to file
     * @param pathToOutFile - path to existing folder where created files will be storage. Contains file name!!
     */
    private void downloadSingeFile(String pathToFile, String pathToOutFile) {
        URL website = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            website = new URL(pathToFile);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(pathToOutFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
