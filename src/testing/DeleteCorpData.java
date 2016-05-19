package testing;
/*
 *  Testing on 1 Mobility
 */
import java.io.FileReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;
/*
 *  Delete corporate data
 */
// Hi this is a test comment from Ranu likhar



public class DeleteCorpData {
	
	private WebDriver d1 = new FirefoxDriver(); 
	int cnt;
	String column;
	// starting Testcase 1
	 @Test(priority=1)
	 public void deleteCorporation() throws InterruptedException {
	 d1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 Actions ac = new Actions(d1);
	 WebElement
	 we = d1.findElement(By.xpath("//*[@id = 'main_menu_level1']/li[1]"));
	 System.out.println("here....");
	 ac.moveToElement(we).moveToElement(d1.findElement(By.xpath("//*[@id='main_"
	 		+ "menu_level1']/li[1]/div/div/div/div[1]/a[7]"))).click().build().
	 		perform();
	 System.out.println("here too....");
	
	 Select sel = new Select(d1.findElement(By.xpath("//*[@id='RowsPerPage']"))
			 );
	 sel.selectByVisibleText("All");
	
	 Thread.sleep(10000);
	 int size = 
	 d1.findElements(By.xpath(".//table[@id='device_list']/tbody/tr")).size();
	 System.out.println("row count= " + size);
	
	 for (int i = 2; i < size; i++) { 
	 String
	 status = d1.findElement(By.xpath("//table[@id='device_list']/tbody/tr[" 
	 + i + "]/td[8]/div/span")).getText();
	 System.out.println("Enrollment status " + status);
	 if (status.equals("Enrolled")) {
	 String devInfo = d1.findElement(By.xpath("//table[@id='device_list']/tbod"
	 		+ "y/tr[" + i + "]/td[3]/div[1]/a")).getText();
	 System.out.println("name is= " + devInfo);
	 d1.findElement(By.xpath("//table[@id='device_list']/tbody/tr[" + i + "]/td"
	 		+ "[3]/" + "div[1]/a")).click();
	
	 Actions ac1 = new Actions(d1);
	 WebElement
	 e = d1.findElement(By.xpath("//*[@id='device_query_action']/ul[2]/li/a"));
	 ac1.moveToElement(e).moveToElement(d1.findElement(By.xpath("//*[@id='devic"
	 		+ "e_query_action']/ul[2]/li/ul/fieldset/li[2]/a"))).click().build(
	 				).perform();
	
	 d1.switchTo().activeElement();
	 d1.findElement(By.xpath("//*[@id='DeleteCorporateDataComments']")).
	 sendKeys("For Testing Purpose Deleting corporate data");
	 d1.findElement(By.xpath("//*[@id='DeleteCorporateDataPassword']")).
	 sendKeys("smart@123");
	 d1.findElement(By.xpath("//*[@id='ScheduleDeleteCorporateData']")).click();
	
	
	
	 }
	 i++;
	 }
	 }
	 /*
	  *  CSV file compare
	  */
	@Test(priority = 0)
	public void exportTest() throws IOException {
		d1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions ac = new Actions(d1);
		WebElement we = d1.findElement(By.xpath("//*[@id='main_menu_level1']/li"
				+ "[1]"));
		// System.out.println("here....");
		ac.moveToElement(we)
				.moveToElement(d1.findElement(By.xpath("//*[@id='main_menu_leve"
						+ "l1']/li[1]/div/div/div/div[1]/a[7]")))
				.click().build().perform();
		// System.out.println("here too....");

		Select sel = new Select(d1.findElement(By.xpath("//*[@id='RowsPerPage']"
				+ "")));
		sel.selectByVisibleText("All");

		d1.findElement(By.xpath("//*[@id='pageHeading']/span[3]/a[1]")).click();
		Runtime.getRuntime().exec("D:\\RohitM-IMP\\JARS\\HitEnter.exe");

		CSVReader read = new CSVReader(
				new FileReader("C:\\Users\\rohitm\\Downloads\\Devices_18-May-16"
						+ "_04_19_PM_IST.csv"));
		String[] nextline;
		while ((nextline = read.readNext()) != null) {

			if (!nextline[0].contains("Friendly Name")) {
				String s = nextline[0]; 
				for (int i = 2; i <= getTableEntries(); i++) {

					if (s.equals(
							d1.findElement(By.xpath("//table[@id='device_list']"
									+ "/tbody/tr[" + (i) + "]/td[3]/div[1]/a"))
									.getText())) {
						System.out.println("Matching Other columns for this mat"
								+ "ched Friendly name " + nextline[0]
								+ " \n---------------------------------------");
						System.out.println("Friendly name Matched...");
						d1.findElement(By.xpath("//table[@id='device_list']/tbo"
								+ "dy/tr[" + i + "]/td[2]")).getText();
						String lastSeen = nextline[12];
														
						
						String compliant = nextline[8];
						String mode = nextline[9];
						String enrollmentStatus = nextline[10];
						String model = nextline[5];
						String platforms = nextline[3];
						String os = nextline[4];
						String empName = nextline[2];
						String ownership = nextline[1];
						String group = nextline[6];
						String location = nextline[7];

						if (lastSeen
								.equals(d1.findElement(By.xpath("//table[@id='d"
										+ "evice_list']/tbody/tr[" + i + "]/td["
												+ "2]"))
										.getText())) {
							System.out.println("Last Seen Matched....");
						}
						if (compliant.equals(d1
								.findElement(
										By.xpath("//table[@id='device_list']/tb"
												+ "ody/tr[" + i + "]/td[6]/div/"
														+ "span/a"))
								.getText())) {
							System.out.println("Compliance Matched....");
						}
						if (mode.equals(d1.findElement(By.xpath("//table[@id='d"
								+ "evice_list']/tbody/tr[" + i + "]/td[7]"))
								.getText())) {
							System.out.println("Mode is matching");
						}
						if (enrollmentStatus
								.equals(d1.findElement(By.xpath("//table[@id='d"
										+ "evice_list']/tbody/tr[" + i + "]/td["
												+ "8]"))
										.getText())) {
							System.out.println("Status is matching");
						}

						String text = d1.findElement(By.xpath("//table[@id='dev"
								+ "ice_list']/tbody/tr[" + i + "]/td[4]"))
								.getText();
						
						String[] a = text.split("\\n+");
						
						if (model.equals(a[1])) {
							System.out.println("Model is matching");
						}
						if (platforms.equals(a[0])) {
							System.out.println("Platform is matching");
						}
						// if(os.equals(a[2]) || a[2]==null || (a[2].equals("")
						// && os.equals("")))
						// {
						// System.out.println("OS_ver is matching");
						// }
						String text1 = d1.findElement(By.xpath("//table[@id='de"
								+ "vice_list']/tbody/tr[" + i + "]/td[5]"))
								.getText();
						
						String[] b = text1.split("\\n+");
						if (empName.equals(b[0])) {
							System.out.println("Employee name is matching");
						}
						String text2 = d1
								.findElement(By.xpath("//table[@id='device_list"
										+ "']/tbody/tr[" + i + "]/td[3]/div[1]"
										))
								.getText();
						

						String[] c = text2.split("\\n+");
						
						if (ownership.equals(c[1])) {
							System.out.println("Ownership is matching");
						}
						
						String[] d = c[2].split("(\\s)|(\\s)");
						
						if (group.equals(d[0])) {
							System.out.println("group is matching");
						}
						if (location.equals(d[2])) {
							System.out.println("location is matching");
						}
						System.out.println("-----------------------------------"
								+ "-----------------");

					} else {
						System.out.println("false");
						continue;
					}

				}
			}
		}

		
	}
	

	@BeforeTest
	public void setUp() {

		// d1.get("https://ap29.smartsourcingglobal.net/");

		d1.get("https://ssg:*ssg!@123@ap29.smartsourcingglobal.net/"); 

		d1.findElement(By.xpath("//*[@id='login_username']")).sendKeys("abhishe"
				+ "kj.ssg@gmail.com");
		d1.findElement(By.xpath("//*[@id='login_password']")).sendKeys("smart@1"
				+ "23");
		d1.findElement(By.xpath("//*[@id='login_form']/p[3]/input")).click();

		

	}
	/*
	 * Table Entries
	 */
	public int getTableEntries() {
		int size = d1.findElements(By.xpath(".//table[@id='device_list']/tbody/"
				+ "tr")).size();
		return size;
	}
}
