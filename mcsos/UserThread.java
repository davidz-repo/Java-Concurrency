package mcsos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class UserThread extends Thread{

	int i;
	
	UserThread(int i) {
		this.i = i;
	}
	
	public void run() {
		final String dir = System.getProperty("user.dir");
		File file = new File(dir+"/all/USER"+Integer.toString(this.i));
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String sav = ".sav", end = ".end", pri = ".pri";
			
			StringBuffer sb = new StringBuffer();
			sb.append(br.readLine());
			
			while ( !sb.toString().contentEquals("null") ) {
				
				String line = sb.toString();
				
				if(line.length() < 4) { // catch exceptions
					System.out.println("empty line - do nothing");
				}
				
				else if (line.substring(0, 4).contentEquals(sav)) {
					
					FileInfo fi = new FileInfo();
					
					String fname = line.substring(6); // get file name 
					fi.diskNumber = ClassMain.rmdisk.request(); // lock the free disk
					fi.startingSector = ClassMain.dm.getFreeSector(); // get free sector
					int writingSector = fi.startingSector;
					
					sb.setLength(0);
					sb.append(br.readLine());
					
					while (!sb.toString().substring(0,4).contentEquals(end)) { // write to disk
						ClassMain.dm.disk[fi.diskNumber].write(writingSector++, sb);
						sb.setLength(0);
						sb.append(br.readLine());
					}
					fi.fileLength = writingSector - fi.startingSector;
					
					ClassMain.dm.diretory_manager.enter(fname, fi); // save to dir
					
					ClassMain.rmdisk.release(fi.diskNumber);
				}
				
				else if (line.substring(0, 4).contentEquals(pri)) { // print saved file
					String fname = line.substring(7);
					Thread pt = new PrintJobThread(fname);
					pt.start();
				}
				else {
					System.out.println("not captured !!!" + line);
				}

				sb.setLength(0);
				sb.append(br.readLine());
			}
		} catch (FileNotFoundException ef) {
			ef.printStackTrace();
		} catch (IOException ei) {
			ei.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

