package ir.sahab.nimbo.moama.newstrendfinder.scraper;

import ir.sahab.nimbo.moama.newstrendfinder.site.SiteDao;
import ir.sahab.nimbo.moama.newstrendfinder.site.SiteDaoImp;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScraperPool implements Runnable {
    private ExecutorService executor;
    private SiteDao siteDao;
    private String databse;

    public ScraperPool(String database) {
        executor = Executors.newFixedThreadPool(10);
        siteDao = new SiteDaoImp(database);
        this.databse = database;
    }

    @Override
    public void run() {
        Queue<String> urls = siteDao.getUrls();
        for (String url : urls) {
            executor.execute(new Scraper(url, databse));
        }
    }


}
