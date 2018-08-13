package ir.sahab.nimbo.moama.newstrendfinder.template;

import java.util.LinkedHashMap;

public class SiteTemplates {
    private static SiteTemplates ourInstance;

    public static void init() {
        ourInstance = new SiteTemplates();
    }

    public static SiteTemplates getInstance() {
        return ourInstance;
    }

    private LinkedHashMap<String, Template> siteTemplates = new LinkedHashMap<>();

    public LinkedHashMap<String, Template> getSiteTemplates() {
        return siteTemplates;
    }

    public Template getTemplateByName(String websiteName) {
        websiteName = websiteName.replace(" ", "_");
        return siteTemplates.get(websiteName);
    }
}