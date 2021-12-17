package scraper;

public class Main {
    public static void main(String[] args) {
//        DefaultScraper defaultScraper = new DefaultScraper();
//        String url = "https://www.newhomesource.com/plan/encore-wlh-taylor-morrison-austin-tx/1771471";
        String url = "localhost";
        Scraper cacheScraper = new CacheScraper();
        cacheScraper.parseHomeInfo(url);
    }
}
