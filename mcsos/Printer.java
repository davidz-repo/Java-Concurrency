package mcsos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Printer {
	/* process 1 line at a time
	 * write the line into a file by the printer index
	 * processing time is 2750 ms per line */
	
	static  BufferedWriter laser(int index, StringBuffer data) throws IOException, InterruptedException {
		
		File file = new File("./PRINTER"+(index+1));
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data.toString()+'\n');
		Thread.sleep(2750);
		return bw;
	}
}
