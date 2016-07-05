/**
 * 
 */
package crawler.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import us.codecraft.webmagic.Page;

/**
 *************************
 * 
 ************************* 
 * @author kootain
 * @creation 2016年7月5日
 *
 */
public class ProcessorSina {
	public static void processor(Page page){
			page.putField("title",page.getHtml().xpath("//h1/text()").toString());
			page.putField("subtitle","");
			List<String> tmp = page.getHtml().css(".page-content .article").xpath("//p/text()").all();
			String content = StringUtils.join(tmp,"<br/>");
			page.putField("content",content);
			page.putField("link",page.getUrl().toString());
			String timeString = page.getHtml().css(".time-source").xpath("//span/text()").toString();
			Pattern timePattern = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日(\\d+):(\\d+)");
			Matcher m = timePattern.matcher(timeString);
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date newsTime = new Date();
			try {
				newsTime = sdf.parse(m.group(1)+"-"+m.group(2)+"-"+m.group(3)+" "+m.group(4)+":"+m.group(5)+":00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page.putField("newsTime", newsTime);
	}
}
