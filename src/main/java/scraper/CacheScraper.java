package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CacheScraper implements Scraper{
    @Override @SneakyThrows
    public Home parseHomeInfo(String url) {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        Statement statement = connection.createStatement();

        String query = String.format("select count(*) as count from homes where url='%s';", url);
        ResultSet rs = statement.executeQuery(query);
        int count = rs.getInt("count");
        if (count == 0) {
            Scraper scraper = new DefaultScraper();
            Home home = scraper.parseHomeInfo(url);
            query = String.format("INSERT INTO homes (url, price, beds, bathes, garages) VALUES ('%s', %d, %f, %f, %f);",
                    url, home.getPrice(), home.getBeds(), home.getBaths(), home.getGarages());
            statement.executeUpdate(query);
            return home;

        } else {
            query = String.format("select * from homes where url='%s'", url);
            rs = statement.executeQuery(query);
            return new Home(rs.getInt("price"), rs.getDouble("beds"),
                    rs.getDouble("bathes"), rs.getDouble("beds"));

        }
    }
}
