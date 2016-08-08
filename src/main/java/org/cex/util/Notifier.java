package org.cex.util;

import org.apache.log4j.Logger;
import org.cex.service.ItemServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Notifier {
	final static Logger logger = Logger.getLogger(Notifier.class);

	public static void main(String args[]) {

		ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/dispatcher-servlet.xml");

		ItemServiceImpl itemService = context.getBean(ItemServiceImpl.class);


	}
}
