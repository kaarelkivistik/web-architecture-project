package ee.kaarelkivistik.webarchitecture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by kaarel on 26.05.16.
 */

@Component
public class MyPageConfiguration {

    private ArrayList<Page> pages;

    @Bean
    public MyPageConfiguration pageConfiguration() {
        return new MyPageConfiguration();
    }

    public MyPageConfiguration() {
        pages = new ArrayList<>();

        pages.add(new Page("/service-requests", "Remondipäringud"));
        pages.add(new Page("/service-requests/new", "Remondipäringu koostamine"));
        pages.add(new Page("/logout", "Logi välja"));
        // pages.add(new Page("/test", "Testimine"));
    }

    public ArrayList<Page> getPages() {
        return pages;
    }
}

class Page {

    private final String route;
    private final String name;

    public Page(String route, String name) {
        this.route = route;
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public String getName() {
        return name;
    }

}