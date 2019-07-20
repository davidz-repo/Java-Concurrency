package mcsos;

class Disk {
	/* just a dummy array to write and read contents */
	static final int NUM_SECTORS = 1024;
	StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
	Disk () {}
	void write(int sector, StringBuffer data) throws InterruptedException {
		this.sectors[sector] = new StringBuffer(data);
		Thread.sleep(200);
	}
	StringBuffer read(int sector) throws InterruptedException {
		Thread.sleep(200);
		return sectors[sector];
	}
}
