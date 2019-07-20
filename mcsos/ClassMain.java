package mcsos;

public class ClassMain {
	
	/* initiate the static resources outside the main method */
	final static int NUM_PRINTERS = 3;
	final static int NUM_DISKS = 2;
	static ResourceManager rmdisk = new ResourceManager(NUM_DISKS);
	static ResourceManager rmprinter = new ResourceManager(NUM_PRINTERS);
	static DiskManager dm = new DiskManager(NUM_DISKS);
	
	/* the driver for the program */
	public static void main(String[] args) {
		
		Thread u1 = new UserThread(1);
		Thread u2 = new UserThread(2);
		Thread u3 = new UserThread(3);
		Thread u4 = new UserThread(4);
		
		u1.setName("User 1");	
		u2.setName("User 2");
		u3.setName("User 3");
		u4.setName("User 4");
		
		u1.start();
		u2.start();
		u3.start();
		u4.start();
	}
}
