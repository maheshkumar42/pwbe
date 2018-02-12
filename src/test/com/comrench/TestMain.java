package test.com.comrench;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import main.com.comrench.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Class: TestMain
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to run the JUnit tests for the Main class. Details are as follows:
 * 1. testExecute_basicFuncationalExecutionFlow: This tests the end to end execution i.e. if proper input if provided 
 * then execution flows till end with no issues.
 * 2. testExecute_basicFuncationalOutputValidation: This test validates the output generated from the execution.
 * 3. testExecute_invalidInput: This test validates the behavior when invalid input is provided in the input statistics
 * file.
 * 4. testExecute_noArgument: This test validates the behavior of application when no arguments are provided during 
 * the execution.
 * 5. testExecute_BlankStringArgument: This test validates the behavior of application when argument is provided but
 * that is an empty string.
 * 
 * Date created: 20180211
 * 
 */

public class TestMain {

	private final static Logger logger = LogManager.getLogger(Main.class);
	private Main main;

	@Before
	public void setUp() throws Exception {
		main = new Main();
	}

	/**
	 * This tests the end to end execution i.e. if proper input if provided then execution flows till end with 
	 * no issues.
	 * @result It confirms that application ran its complete end to end cycle with no issues.
	 */
	
	@Test
	public void testExecute_basicFuncationalExecutionFlow() throws IOException{
		String configFileName = "etc/config.xml";
		File configFile = new File(configFileName);
		File inputFile = new File("input/inputStats.txt");
		if(configFile.exists() && configFile.canRead() && 
				inputFile.exists() && inputFile.canRead()){
			assertTrue(main.execute(new String[]{configFileName}));
		}else{
			throw new IOException("Input data for test is not valid.");
		}		
	}
	
	/**
	 * This test validates the output generated from the execution.
	 * @result It confirms that application ran its complete end to end cycle and then validates its output is correct.
	 */
	
	@Test
	public void testExecute_basicFuncationalOutputValidation() throws IOException {
		String configFileName = "etc/config.xml";
		File configFile = new File(configFileName);
		File inputFile = new File("input/inputStats.txt");		
		if(configFile.exists() && configFile.canRead() && 
				inputFile.exists() && inputFile.canRead()){
			main.execute(new String[]{configFileName});
		}else{
			throw new IOException();
		}
		String[] expectedOutputArray = {
				"School3              108",
				"Kitchener            108",
				"School2              3000",
				"School1              3000",
				"Waterloo             6000",
				"ON                   6108"
		};
		String expectedOutputString = Arrays.toString(expectedOutputArray);
		String[] actualOutput = new String[6];
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(main.getOutputFileName())))) {
			String line; int counter = 0;
			while ((line = bufferedReader.readLine()) != null && counter<6) {
				actualOutput[counter++] = line.trim();
			}
		} catch (IOException e) {
			logger.error("Exception while processing the file "+main.getOutputFileName());
			e.printStackTrace();			
		}
		String actualOutputString = Arrays.toString(actualOutput);
		assertTrue(expectedOutputString.equals(actualOutputString));
	}

	/**
	 * This test validates the behavior when invalid input is provided in the input statistics file.
	 * @result It confirms that application ran its complete end to end cycle when there was invalid input provided 
	 * and then validates its output is correct.
	 */
	
	@Test
	public void testExecute_invalidInput() throws IOException {
		String configFileName = "test/etc/invalidStatsConfig.xml";
		File configFile = new File(configFileName);
		File inputFile = new File("test/input/invalidStats.txt");		
		if(configFile.exists() && configFile.canRead() && 
				inputFile.exists() && inputFile.canRead()){
			main.execute(new String[]{configFileName});
		}else{
			throw new IOException();
		}
		String[] expectedOutputArray = {
				"School3              51",
				"Kitchener            51",
				"School2              3000",
				"School1              3000",
				"Waterloo             6000",
				"ON                   6051"
		};
		String expectedOutputString = Arrays.toString(expectedOutputArray);
		String[] actualOutput = new String[6];
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(main.getOutputFileName())))) {
			String line; int counter = 0;
			while ((line = bufferedReader.readLine()) != null && counter<6) {
				actualOutput[counter++] = line.trim();
			}
		} catch (IOException e) {
			logger.error("Exception while processing the file "+main.getOutputFileName());
			e.printStackTrace();			
		}
		String actualOutputString = Arrays.toString(actualOutput);
		assertTrue(expectedOutputString.equals(actualOutputString));
	}
	
	/**
	 * This test validates the behavior of application when no arguments are provided during the execution.
	 * @result It confirms that application terminated gracefully when there is no argument.
	 */
	
	@Test
	public void testExecute_noArgument() {
		assertFalse(main.execute(new String[]{}));
	}

	/**
	 * This test validates the behavior of application when argument is provided but that is an empty string.
	 * @result It confirms that application terminated gracefully when there is blank argument.
	 */
	
	@Test
	public void testExecute_BlankStringArgument() {
		assertFalse(main.execute(new String[]{"   "}));
	}
	
}
