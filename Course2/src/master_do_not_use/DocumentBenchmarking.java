package master_do_not_use;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DocumentBenchmarking {

	public static final int TRIALS = 100;
	
	public static void main(String [] args) {
		String s = "data/warAndPeace.txt";
		
		int increment = 10000;
		String test = null;
		int numSteps = 20;
		int start = 50000;
		long startTime = 0;
		long endTime = 0;
		double seconds = 0;
		long startTime2 = 0;
		long endTime2 = 0;
		double seconds2 = 0;
		
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			test = getStringFromFile(s,numToCheck);
			//System.out.println(test);
		
			startTime = System.nanoTime();
			for(int i = 0;  i< TRIALS; i++) {
				Document b = new EfficientDocument(test);
				//Document b = new BasicDocument(test);
				b.getFleschScore();
			}
			endTime = System.nanoTime();
			seconds = ((double )endTime - startTime) / 1000000000;
			System.out.print(numToCheck + "\t" + seconds);
		
			startTime2 = System.nanoTime();
			for(int i = 0;  i< TRIALS; i++) {
				//Document b = new EfficientDocument(test);
				Document b = new BasicDocument(test);
				b.getFleschScore();
			}
			endTime2 = System.nanoTime();
			seconds2 = ((double )endTime2 - startTime2) / 1000000000;
			System.out.println("\t" + seconds2);
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
