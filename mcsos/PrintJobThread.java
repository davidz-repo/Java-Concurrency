package mcsos;

import java.io.BufferedWriter;
import java.io.IOException;

class PrintJobThread extends Thread{
	/* just a thread to process the printing request */
	private FileInfo info;
	PrintJobThread (String filename) {
		this.info = ClassMain.dm.diretory_manager.lookup(filename);
	}
	public void run() {
		int printer = ClassMain.rmprinter.request();
		BufferedWriter bw;
		for( int i=0; i < info.fileLength; ++i) {
			try {
				bw = sendContent(printer, i);
				if(bw!=null)
					bw.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		ClassMain.rmprinter.release(printer);
	}
	
	private BufferedWriter sendContent(int printer, int i) throws IOException, InterruptedException {
		BufferedWriter bw = Printer.laser(printer, ClassMain.dm.disk[info.diskNumber].read(info.startingSector+i));
		return bw;
	}
}
