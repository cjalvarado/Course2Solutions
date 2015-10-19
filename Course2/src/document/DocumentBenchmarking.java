package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	// Run each test more than once to get bigger numbers and less noise.
	public static final int TRIALS = 100;
	
	public static void main(String [] args) {
		String s = "data/warAndPeace.txt";
		
		int increment = 20000;
		String test = null;
		int numSteps = 20;
		int start = 50000;
		long startTime = 0;
		long endTime = 0;
		double seconds = 0;
		long startTime2 = 0;
		long endTime2 = 0;
		double seconds2 = 0;

		System.out.println("Size\tBasicDocument\tEfficientDocument");
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			test = getStringFromFile(s,numToCheck);
			//System.out.println(test);
		
			System.out.print(numToCheck);
			
			startTime2 = System.nanoTime();
			for(int i = 0;  i< TRIALS; i++) {
				//Document b = new EfficientDocument(test);
				Document b = new BasicDocument(test);
				b.getFleschScore();
			}
			endTime2 = System.nanoTime();
			seconds2 = (((double )endTime2 - startTime2) / 1000000000);
			System.out.print("\t" + seconds2);

			startTime = System.nanoTime();
			for(int i = 0;  i< TRIALS; i++) {
				Document b = new EfficientDocument(test);
				//Document b = new BasicDocument(test);
				b.getFleschScore();
			}
			endTime = System.nanoTime();
			seconds = (((double )endTime - startTime) / 1000000000);
			System.out.println(numToCheck + "\t" + seconds);

		}
	}
	
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
