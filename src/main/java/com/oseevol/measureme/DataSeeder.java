package com.oseevol.measureme;

import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.oseevol.measureme.domain.MeasureConfig;
import com.oseevol.measureme.domain.Website;
import com.oseevol.measureme.service.WebsiteService;


@Component
public class DataSeeder implements CommandLineRunner {

	private final WebsiteService websiteService;

    public DataSeeder(WebsiteService websiteService) {
		super();
		this.websiteService = websiteService;
	}

	@Override
    public void run(String... args) throws Exception {
        Iterable<Website> records = websiteService.findAll();

        if(records instanceof Collection && ((Collection<?>)records).isEmpty()){
            Website a = getInstanceFor("Wikipedia", "https://www.wikipedia.org", 43);
            websiteService.save(a);
            Website b = getInstanceFor("Yahoo", "https://www.yahoo.com", 75);
            websiteService.save(b);
            Website c = getInstanceFor("Google", "https://www.google.com", 120);
            websiteService.save(c);
            Website d = getInstanceFor("MSN", "https://www.msn.com", 150);
            websiteService.save(d);
            Website e = getInstanceFor("AOL", "https://www.aol.com", 250);
            websiteService.save(e);
        }
    }

    private Website getInstanceFor(String name, String url, int delay) {
        Website site = new Website();
        site.setName(name);
        site.setUrl(url);
        MeasureConfig conf = new MeasureConfig();
        conf.setWebsite(site);
        conf.setMeasureInterval(2);
        conf.setResponseThreshold(delay);
        site.setMeasureConfig(conf);
        return site;
    }
}
