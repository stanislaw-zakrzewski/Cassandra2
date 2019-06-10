package data;

import com.example.demo.model.Match;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class CSVParser {
    public List<Match> parseMatchF(File file) {
        List<Match> matches = new LinkedList<>();
        BufferedReader br = null;
        String line;
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

        try {
            br = new BufferedReader(new FileReader(file));

            //Skip first line with definitions of columns
            br.readLine();

            //Parse all matches in file to matches on list returned by this function
            while ((line = br.readLine()) != null) {
                try {
                    // use comma as separator
                    String[] csvMatch = line.split(",");

                    Match match = new Match();

                    //Set all values for match
                    match.setLeagueDivision(csvMatch[0]);
                    match.setDate(dateParser.parse(csvMatch[1]));
                    match.setHomeTeam(csvMatch[2]);
                    match.setAwayTeam(csvMatch[3]);
                    match.setFullTimeHomeGoals(Integer.parseInt(csvMatch[4]));
                    match.setFullTimeAwayGoals(Integer.parseInt(csvMatch[5]));
                    match.setFullTimeResult(csvMatch[6]);
                    match.setHalfTimeHomeTeamGoals(Integer.parseInt(csvMatch[7]));
                    match.setHalfTimeAwayTeamGoals(Integer.parseInt(csvMatch[8]));
                    match.setHalfTimeResult(csvMatch[9]);
                    match.setReferee(csvMatch[10]);

                    //Add matches to list of matches
                    matches.add(match);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return matches;
    }

    public List<Match> parseMatch(String path) {
        List<Match> matches = new LinkedList<>();
        BufferedReader br = null;
        String line;
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

        try {
            br = new BufferedReader(new FileReader(path));

            //Skip first line with definitions of columns
            br.readLine();

            //Parse all matches in file to matches on list returned by this function
            while ((line = br.readLine()) != null) {
                try {
                    // use comma as separator
                    String[] csvMatch = line.split(",");

                    Match match = new Match();

                    //Set all values for match
                    match.setLeagueDivision(csvMatch[0]);
                    match.setDate(dateParser.parse(csvMatch[1]));
                    match.setHomeTeam(csvMatch[2]);
                    match.setAwayTeam(csvMatch[3]);
                    match.setFullTimeHomeGoals(Integer.parseInt(csvMatch[4]));
                    match.setFullTimeAwayGoals(Integer.parseInt(csvMatch[5]));
                    match.setFullTimeResult(csvMatch[6]);
                    match.setHalfTimeHomeTeamGoals(Integer.parseInt(csvMatch[7]));
                    match.setHalfTimeAwayTeamGoals(Integer.parseInt(csvMatch[8]));
                    match.setHalfTimeResult(csvMatch[9]);
                    match.setReferee(csvMatch[10]);

                    //Add matches to list of matches
                    matches.add(match);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return matches;
    }
}
