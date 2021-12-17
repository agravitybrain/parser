package scraper;


import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper{
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BEDS_SELECTOR = ".nhs_BedsInfo span";
    private static final String BATHS_SELECTOR = ".nhs_BathsInfo";
    private static final String GARAGES_SELECTOR = ".nhs_GarageInfo";

    @Override @SneakyThrows
    public Home parseHomeInfo(String url) {
        Document doc = Jsoup.connect(url).get();
        int price = getPrice(doc);
        double beds = getBeds(doc);
        double baths = getBaths(doc);
        double garages = getGarages(doc);
        return new Home(price, beds, baths, garages);
    }

    private static int getPrice(Document doc) {
        String priceText = doc.selectFirst(PRICE_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    private static double getBeds(Document doc) {
        String bedsText = doc.selectFirst(BEDS_SELECTOR).text().replaceAll("[^0-9]", "");
        return Double.parseDouble(bedsText);
    }

    private double getBaths(Document doc) {
        String bathsText = doc.selectFirst(BATHS_SELECTOR).text().replaceAll("[^0-9]", "");
        return Double.parseDouble(bathsText);
    }

    private double getGarages(Document doc) {
        String garagesText = doc.selectFirst(GARAGES_SELECTOR).text().replaceAll("[^0-9]", "");
        return Double.parseDouble(garagesText);
    }
}
