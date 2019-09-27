package br.com.testjmeter;

import java.io.File;
import java.io.IOException;

import org.apache.jmeter.JMeter;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JMeterFromExistingJMX2 {

	public static void main(String[] args) throws IOException {
		
		//JMeter Home
		 String jmeterHome = "c:/tmp/";
		 
		 // JMeter Engine
		 StandardJMeterEngine jmeter = new StandardJMeterEngine();
		 
		 // Initialize Properties, logging, locale, etc.
		 JMeterUtils.loadJMeterProperties(jmeterHome + "bin/jmeter.properties");
		 JMeterUtils.setJMeterHome(jmeterHome);
		 JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
		 JMeterUtils.initLocale();
		 
		 // Initialize JMeter SaveService
		 SaveService.loadProperties();
		 
		 // Load existing .jmx Test Plan
		 HashTree testPlanTree = SaveService.loadTree(new File("C:/Users/renan/Documents/example.jmx"));
		 
		 // Remove disabled test elements
		 JMeter.convertSubTree(testPlanTree);
		 
		 // Add summariser
		 Summariser summer = null;
		 String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		 if (summariserName.length() > 0) {
		   summer = new Summariser(summariserName);
		 }
		 
		 // Store execution results into a .jtl file
		 String logFile = jmeterHome + "/bin/test.jtl";
		 ResultCollector logger = new ResultCollector(summer);
		 logger.setFilename(logFile);
		 testPlanTree.add(testPlanTree.getArray()[0], logger);
		 
		 // Run JMeter Test
		 jmeter.configure(testPlanTree);
		 jmeter.run();
		
	}

}
