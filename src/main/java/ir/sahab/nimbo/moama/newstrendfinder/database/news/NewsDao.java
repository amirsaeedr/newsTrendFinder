package ir.sahab.nimbo.moama.newstrendfinder.database.news;

public interface NewsDao {

    boolean addNews(News news);

//    ArrayList<String> search(String field, String text);
//
//    ArrayList<String> getLatestNews(String siteName);
//
//    int getNewsFromADay(String siteName, String date);

    void executeUpdate(String query);
}
