package mcsos;

class DiskManager {
	/* manages disk, and the file information */
	Disk disk[];
	DirectoryManager diretory_manager;
	static int freeSec = 0; // always give the next free sector
	
	DiskManager(int num_disk){
		disk = new Disk[num_disk];
		for(int i=0; i<num_disk; ++i) {
			disk[i] = new Disk();
		}
		diretory_manager = new DirectoryManager();
	}
	
	int getFreeSector() {
		freeSec += 10;
		return freeSec;
	}
	
}
