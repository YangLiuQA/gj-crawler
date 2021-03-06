package com.gj.web.crawler;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;

import com.gj.web.crawler.pool.CrawlerThreadPool;
import com.gj.web.crawler.pool.CrawlerThreadPoolImpl;
import com.gj.web.crawler.pool.basic.URL;

/**
 * the executor of all the crawlers,
 * actually, it is an extension of CrawlerThreadPoolImpl
 * and a bridge between crawler component and other framework like 'Spring'
 * @author David
 *
 */
public class CrawlerExecutor implements CrawlerThreadPool,InitializingBean{
	/**
	 * Data Access Object for Crawler
	 */
	private CrawlerDao dao = null;
	private CrawlerThreadPool pool = CrawlerThreadPoolImpl.getInstance();

	public void open() {
		pool.open();
	}

	public boolean isOpen() {
		return pool.isOpen();
	}

	public void shutdown() {
		pool.shutdown();
	}

	public void execute(String cid) {
		pool.execute(cid);
	}
	public void execute(URL url) {
		pool.execute(url);
	}
	public Map<String, CrawlerApi> getCrawlers() {
		return pool.getCrawlers();
	}

	public void setCrawlers(Map<String, CrawlerApi> crawlers) {
		pool.setCrawlers(crawlers);
	}

	public void setPoolSize(Integer size) {
		pool.setPoolSize(size);
	}

	public Integer getPoolSize() {
		return pool.getPoolSize();
	}

	public void setMaxFree(Integer free) {
		pool.setMaxFree(free);
	}
	
	public Integer getMaxFree() {
		return pool.getMaxFree();
	}
	/**
	 * start to open the pool after setting the properties
	 */
	public void afterPropertiesSet() throws Exception {
		if(!pool.isOpen()){
			if(null != dao){
				Map<String,CrawlerApi> map = pool.getCrawlers();
				List<CrawlerApi> crawlers = dao.loadAll();
				for(int i = 0;i<crawlers.size();i++){
					CrawlerApi crawler = crawlers.get(i);
					map.put((String)crawler.getId(), crawler);
				}
			}
			pool.open();
		}
	}
}
