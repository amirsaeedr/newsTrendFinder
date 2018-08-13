package ir.sahab.nimbo.moama.newstrendfinder.metrics;

import org.apache.log4j.Logger;

import java.io.PrintStream;

import static java.lang.Thread.sleep;

public class Metrics {
    public static int numberOfUrlGetted = 0;
    public static int numberOfNull = 0;
    public static int numberOfDuplicate = 0;
    public static int numberOfDomainError = 0;
    public static long byteCounter = 0;
    public static int numberOFCrawledPage = 0;
    public static int numberOfLanguagePassed = 0;
    public static int numberOfPagesAddedToElastic = 0;
    public static int numberOfPagesAddedToHbase = 0;
    private static long lastTime = System.currentTimeMillis();
    private static Logger infoLogger = Logger.getLogger("info");
    private static int lastNumberOfLanguagePassed = 0;
    private static int lastNumberOfCrawledPage = 0;
    private static int lastNumberOfDuplicate = 0;
    private static int lastNumberOfDomainError = 0;
    private static int lastNumberOfUrlGetted = 0;
    private static int lastNumberOfPagesAddedToElastic = 0;
    private static int lastNumberOfPagesAddedToHbase = 0;


    public static void stat(PrintStream out) {
        int delta = (int) ((System.currentTimeMillis() - lastTime) / 1000);
        infoLogger.info("received MB     " + (byteCounter >> 20));
        infoLogger.info("num/rate of getted url     " + numberOfUrlGetted + "\t" + (double) (numberOfUrlGetted - lastNumberOfUrlGetted) / delta);
        infoLogger.info("num/rate of passed lang    " + numberOfLanguagePassed + "\t" + (double) (numberOfLanguagePassed - lastNumberOfLanguagePassed) / delta);
        infoLogger.info("num/rate of duplicate      " + numberOfDuplicate + "\t" + (double) (numberOfDuplicate - lastNumberOfDuplicate) / delta);
        infoLogger.info("num/rate of domain Error   " + numberOfDomainError + "\t" + (double) (numberOfDomainError - lastNumberOfDomainError) / delta);
        infoLogger.info("num/rate of crawl=         " + numberOFCrawledPage + "\t" + (double) (numberOFCrawledPage - lastNumberOfCrawledPage) / delta);
        infoLogger.info("num/rate of crawl=         " + numberOFCrawledPage + "\t" + (double) (numberOFCrawledPage - lastNumberOfCrawledPage) / delta);
        infoLogger.info("num/rate of hbase=         " + numberOfPagesAddedToHbase + "\t" + (double) (numberOfPagesAddedToHbase - lastNumberOfPagesAddedToHbase) / delta);
        infoLogger.info("num/rate of elastic=         " + numberOfPagesAddedToElastic + "\t" + (double) (numberOfPagesAddedToElastic - lastNumberOfPagesAddedToElastic) / delta);
        infoLogger.info(numberOFCrawledPage + "number of crawled pages");
//  infoLogger.info("rate of crawl=" + (double) (numberOFCrawledPage - lastNumberOfCrawledPage) / delta);
        lastNumberOfUrlGetted = numberOfUrlGetted;
        lastNumberOfDuplicate = numberOfDuplicate;
        lastNumberOfDomainError = numberOfDomainError;
        lastNumberOfLanguagePassed = numberOfLanguagePassed;
        lastNumberOfCrawledPage = numberOFCrawledPage;
        lastNumberOfPagesAddedToHbase = numberOfPagesAddedToHbase;
        lastNumberOfPagesAddedToElastic = numberOfPagesAddedToElastic;
        lastTime = System.currentTimeMillis();


    }

    static {
        new Thread(() -> {
            while (true) {
                try {
                    sleep(20000);
                    stat(System.out);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();

    }


}
