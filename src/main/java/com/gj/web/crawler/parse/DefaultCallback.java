package com.gj.web.crawler.parse;

import java.util.Map;
import java.util.Map.Entry;

public class DefaultCallback implements Callback{
	/**
	 * EN:suggest that if you do some time-consuming work in the method,
	 * you'd better to do in new thread
	 * CN:建议如果做一些耗时的工作(在callback方法中),最好在线程中完成(为了不阻塞队列)
	 */
	public void callback(ResultModel result) {
		Map<String,Object[]> inner = result.getInnerMap();
		System.out.println("-----------------------------");
		for(Entry<String,Object[]> entry : inner.entrySet()){
			String key = entry.getKey();
			Object[] value = entry.getValue();
			System.out.println(key +": "+value[0]);
		}
		System.out.println("-----------------------------");
	}

}
