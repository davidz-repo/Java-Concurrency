package mcsos;
import java.util.Hashtable;

class DirectoryManager {
	/* stores information on where the files are saved */
	Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();
	DirectoryManager() {}
	void enter(String fname, FileInfo file) {
		T.put(fname,  file);
		// System.out.println("File "+fname+"\n - saved at d."+file.diskNumber+".s."+file.startingSector+" len=<"+file.fileLength+">");
	}
	FileInfo lookup(String key) {
		return T.get(key);
	}
}
