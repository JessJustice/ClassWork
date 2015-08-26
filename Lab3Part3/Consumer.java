/**
 * This is the consumer thread for the bounded buffer problem.
 *
 * Figure 6.13
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.io.PrintWriter;
import java.util.*;

public class Consumer implements Runnable
{
	private  Buffer<Date> buffer;

   public Consumer(Buffer<Date> buffer) { 
      this.buffer = buffer;
   }
   
   public void run()
   {
   Date message;
   
   
     while (true)
      {
         Gui.messageArea.append("Consumer napping" + "\n");
	// SleepUtilities.nap(); 
	 BoundedBuffer.getConsumerSleep();
         
         // consume an item from the buffer
         Gui.messageArea.append("Consumer wants to consume." + "\n");
           
         message = buffer.remove();
      }
   }
   
}

