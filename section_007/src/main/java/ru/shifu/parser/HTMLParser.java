package ru.shifu.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The class scans the forum skl.ru, finds jobs java developer.
 * When you first start it finds all jobs from the beginning of the year.
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 31.12.2018
 */
public class HTMLParser {
    /**
     * Logger for info output.
     */
    private static final Logger LOGGER = LogManager.getLogger(StartParser.class);
    /**
     * Work with database.
     */
    private DBVacancy sql;
    /**
     * List of all new vacancies.
     */
    private List<Vacancy> vac = new ArrayList<>();
    /**
     * Loaded properties with connection options.
     */
    private Configurator configurator = new Configurator();
    /**
     * Start date of the search.
     * Last start date or beginning of year.
     */
    private  LocalDateTime maxDate;
    /**
     * Basic url for parsing.
     */
    private String url;


    public HTMLParser(Connection connection) {
        this.sql = new DBVacancy(connection);
        this.url = configurator.getValue("jsob.url");
        this.maxDate = this.sql.getMaxDate();
    }

    /**
     * Creates a document with a url.
     * @param url of html document.
     * @return document is based on the html page.
     */
    private Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            this.LOGGER.error("ERROR", e);
        }
        return doc;
    }

    /**
     * Method searches vacancies from url.
     * At start checks number of pages.
     * After that parses every page until date of last message in the topic going after start date.
     * Also, if the topic matches the date, it analyzes the first message by the publication date and the Java programmer's filter.
     * If all conditions are met, add to the job list.
     */
    public void parse() {
        String pageUrl;
        int pages =  getPages(this.url);
        LOGGER.info(String.format("Total pages to parse: %s", pages));

        for (int page = 1; page <= pages; page++) {

            pageUrl = String.format("%s/%s", this.url, page);
            LOGGER.info(String.format("Parsing page %s of %s pages.", page, pages));

            Document doc = getDoc(pageUrl);
            Element table = doc.select("table.forumTable").first();
            Elements tr = table.select("tr");

            tr.forEach(td -> td.select("td")
                    .stream().filter(filtered -> filtered.hasClass("postslisttopic"))
                    .forEach(col -> {
                        String vacRef = col.child(0).attr("href");
                        String data = td.select("td.altCol").get(1).text();
                        LocalDateTime vacDate = this.parsDate(data);
                        String vacName = col.child(0).text();
                        if (vacDate.isAfter(this.maxDate) && this.isValidName(vacName)) {
                            this.vac.add(new Vacancy(vacName, vacRef, vacDate));
                        }
                    })
            );
        }
        this.sql.addMultiVac(vac);
        this.vac.clear();
        LOGGER.info(String.format("Total vacancies found: %s.", vac.size()));
    }

    /**
     * Return local datetime.
     * @param date url representation of the datetime.
     * @return local datetime.
     */
    private LocalDateTime parsDate(String date) {
        String time = date.substring(date.indexOf(",") + 2);
        int hour = new Integer(time.split(":")[0].trim());
        int min = new Integer(time.split(":")[1].trim());
        LocalTime localTime = LocalTime.of(hour, min);

        date = date.substring(0, date.indexOf(",")).trim();
        LocalDate localDate = LocalDate.now();

        if (date.contains("вчера")) {
            localDate.minusDays(1);
        } else if (!date.contains("сегодня") && !date.contains("вчера")) {
            int year = new Integer("20" + date.substring(date.length() - 2));
            String strMonth = date.substring(2, 6).trim();
            int day = new Integer(date.substring(0, 2).trim());
            localDate = LocalDate.of(year, getMonth(strMonth), day);
        }
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Return month.
     * @param strMonth url representation of the month.
     * @return month.
     */
    private Month getMonth(String strMonth) {
        Month result = Month.JANUARY;
        if ("фев".equals(strMonth)) {
            result = Month.FEBRUARY;
        } else if ("мар".equals(strMonth)) {
            result = Month.MARCH;
        } else if ("апр".equals(strMonth)) {
            result = Month.APRIL;
        } else if ("май".equals(strMonth)) {
            result = Month.MAY;
        } else if ("июн".equals(strMonth)) {
            result = Month.JUNE;
        } else if ("июл".equals(strMonth)) {
            result = Month.JULY;
        } else if ("авг".equals(strMonth)) {
            result = Month.AUGUST;
        } else if ("сен".equals(strMonth)) {
            result = Month.SEPTEMBER;
        } else if ("окт".equals(strMonth)) {
            result = Month.OCTOBER;
        } else if ("ноя".equals(strMonth)) {
            result = Month.NOVEMBER;
        } else if ("дек".equals(strMonth)) {
            result = Month.DECEMBER;
        }
        return result;
    }
    /**
     * Method searches for the number of all pages in the section.
     * Only for sql.ru
     * @param url of page.
     * @return number of pages.
     * @throws IOException if can't get document.
     */
    private int getPages(String url)  {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements sortOptions = doc.getElementsContainingOwnText("Страницы:");
        String pages = sortOptions.text().trim();
        String[] p = pages.split("\\s");
        return Integer.parseInt(p[p.length - 1]);
    }

    /**
     * Checking the vacancy header for compliance with the programming language.
     * @param name vacancy header.
     * @return true if vacancy header contains "Java" else false.
     */
    private boolean isValidName(String name) {
        return Pattern.compile("\\b[Jj][Aa][Vv][Aa]\\b[^Jj]").matcher(name).find();
    }
}