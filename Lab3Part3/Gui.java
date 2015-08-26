import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Gui extends JPanel implements Runnable{

	private BufferedReader in;
	private PrintWriter out;
	private static JFrame frame = new JFrame("Factory");
	private JTextField dataField = new JTextField(40);
	static public JTextArea messageArea = new JTextArea(8, 60);
	private JPanel actionButtons = new JPanel(new FlowLayout());
	private JButton sendButton = new JButton("Send");
	
	private JTextField prodName = new JTextField("Enter Producer Sleep");
	private JTextField consName = new JTextField("Enter Consumer Sleep");
	private JTextField prodSleepIn = new JTextField(20);
	private JTextField consSleepIn = new JTextField(20);
	
	public Gui(){
		//runClient();
				messageArea.setEditable(false);
				actionButtons.add(prodName);
		        actionButtons.add(prodSleepIn);
		        actionButtons.add(consName);
		        actionButtons.add(consSleepIn);

				frame.getContentPane().add(actionButtons, "North");
				frame.getContentPane().add(new JScrollPane(messageArea), "Center");
				
			
		        prodSleepIn.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		int num = Integer.parseInt(prodSleepIn.getText());
		        		BoundedBuffer.setProducerSleep(num);
		        		messageArea.append("Producer sleep = " + num);
		        		System.out.println("producer number changed to: " + num);
		        	}
		        });
		        
		        consSleepIn.addActionListener(new ActionListener(){
		        	public void actionPerformed(ActionEvent e){
		        		int num = Integer.parseInt(consSleepIn.getText());
		        		BoundedBuffer.setConsumerSleep(num);
		        		messageArea.append("Consumer sleep = " + num);
		        		System.out.println("Consumer number changed to: " + num);
		        		
		        	}
		        });
				
			}
			

			
			public static void makeFrame(){
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}

			
			public static void main(String[] args){
			Gui client = new Gui();
		
			//client.connectToServer();
			
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
}
