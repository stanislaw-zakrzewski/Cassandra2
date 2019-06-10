package data;

import java.nio.file.Paths;

public class DataFetchApp {
    public static void main(String[] args) {
        GetData getData = new GetData();
        String path = Paths.get("").toAbsolutePath().resolve("src").resolve("main").resolve("resources").resolve("output").toString();
        getData.downloadDefinedFiles(path);
    }
}
