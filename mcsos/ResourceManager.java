package mcsos;

class ResourceManager {
	/* manages both printers and disks
	 * create a bit array to mark the free ones and busy ones
	 * can be requested for a free resource and put a lock on it
	 * after receive the release notice, release the lock */
	boolean isFree[];
	ResourceManager(int numberOfItems){
		isFree = new boolean[numberOfItems];
		for (int i=0; i<isFree.length; ++i)
			isFree[i] = true;
	}
	synchronized int request(){
		while (true) {
			for(int i=0; i<isFree.length; ++i) {
				if( isFree[i] ) {
					isFree[i] = false;
					return i;
				}
			}
			try {
				this.wait(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	synchronized void release( int index ) {
		isFree[index] = true;
		this.notify();
	}
}