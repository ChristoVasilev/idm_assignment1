package tudelft.wis.idm_tasks;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Imdb_JDBCManager imdbJdbcManager = new Imdb_JDBCManager();
//        Connection connection = imdbJdbcManager.getConnection();
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        imdbJdbcManager.getTitlesPerYear(year).forEach(System.out::println);

    }
}