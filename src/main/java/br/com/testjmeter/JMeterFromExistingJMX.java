package br.com.testjmeter;

import java.io.File;
import java.io.IOException;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JMeterFromExistingJMX {
	
	public static void main(String[] args) throws IOException {
		
		// JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();
		
		JMeterUtils.loadJMeterProperties("c:/tmp/bin/jmeter.properties");
		JMeterUtils.setJMeterHome("c:/tmp");
		JMeterUtils.initLocale();
		
		SaveService.loadProperties();
		
		File in = new File("C:/Users/renan/Documents/example.jmx");
		HashTree testPlanTree = SaveService.loadTree(in);
		
		jmeter.configure(testPlanTree);
	}

}
