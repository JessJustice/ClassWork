/**
 * This is the producer thread for the bounded buffer problem.
 *
 * Figure 6.12
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */


import java.util.*;

public class Producer implements Runnable
{
	private  Buffer<Date> buffer;
	
	public Producer(Buffer<Date> buffer) {
		this.buffer = buffer;
	}
	
	public void run()
	{
		Date message;
		
		while (true) {
		
			Gui.messageArea.append("Producer napping" + "\n");
			//SleepUtilities.nap();
			BoundedBuffer.getProducerSleep();
			// produce an item & enter it into the buffer
			message = new Date();      
			Gui.messageArea.append("Producer produced " + message + "\n");
			
			buffer.insert(message);
		}
	}
	
}