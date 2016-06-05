package testing;

import org.testng.annotations.Test;

import com.beust.testng.TestNG;

public class EntryPoint1 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 TestNG testng = new TestNG();
	     Class[] classes = new Class[]{DeleteCorpData.class};
	     testng.setTestClasses(classes);
	     testng.run();
	     //return -1;
}
}
