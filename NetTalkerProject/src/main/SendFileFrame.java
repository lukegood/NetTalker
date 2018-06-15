package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class SendFileFrame extends JFrame {

	private JPanel contentPane;
	JTextArea textArea;
    private static Socket socket;
	private Scanner sin;
	private static Scanner choice;
	public static char ch;
	File file;
	JTextField textField;
	JTextArea textArea_1;
	JButton button_1;
	JLabel label;
	private JButton button;
	/**
	 * Launch the application.
	 */
	public void start() {

		try{
			GetServerMessage GSM = new GetServerMessage();
	        new Thread(GSM).start();
		}catch(Exception e) {
			System.out.println("Error"+e); //出错，则打印出错信息
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendFileFrame frame = new SendFileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private class GetServerMessage implements Runnable{
        public void run() {
            try {
                 /*通过这个内部类启动一个线程
                  * 不断读取服务器发来的消息
                  */
            	  File aimfile = new File("aimfile1.txt");
            	  BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            	  FileWriter fos = new FileWriter(aimfile,true);
            	  String message = null;
            	  //message = is.readLine();
                  while((message = is.readLine()) != null){
                	  //textArea.append(message);
                	  fos.write(message);
                	  fos.flush();
                	  //textArea.append("正在接收请稍后...");
                	  //fos.write("\r\n");
                  }
               } catch (Exception e) {
                  e.printStackTrace();
           }
       }
	}
	/**
	 * Create the frame.
	 */
	public SendFileFrame() {
		setTitle("Java\u804A\u5929\u5668-\u4F20\u9001\u6587\u4EF6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);        //激活自动换行功能 
		textArea.setWrapStyleWord(true);  // 激活断行不断字功能
		textArea.append("连接服务端...\n\r");
		//mtcf.lblNewLabel.setT
		//向本机的6666端口发出客户请求
		try {
			socket = new Socket("127.0.0.1",6666);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			textArea.append("连接成功!\n\r");
		}
		
		JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
		        jfc.showDialog(new JLabel(), "我要发送此文件");  
		        File file=jfc.getSelectedFile();  
		        if(file.isFile()){  
		            textArea.append("准备发送的文件:"+file.getAbsolutePath()+"\n\r");  
		        }  
		        GetServerMessage GSM = new GetServerMessage();
		        new Thread(GSM).start();
		        PrintWriter os=new PrintWriter(socket.getOutputStream(),true);
		        os.println("password:tiaf");
		        os.flush();
		        FileReader fis = new FileReader(file);
		        BufferedReader rf = new BufferedReader(fis);
                String readline;
                while((readline=rf.readLine())!=null)
                   {
                	   os.println(readline);
                	   os.flush();
                   }
                GetServerMessage GSM1 = new GetServerMessage();
    	        new Thread(GSM1).start();
    	        textArea.append("发送完成，感谢使用！\n\r");
			}catch(Exception e1) {
				System.out.println("Error"+e1); //出错，则打印出错信息
			} 
			}
		});
		
		JLabel label = new JLabel("\u63D0\u793A\u4FE1\u606F");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(label)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addComponent(button)
					.addContainerGap(170, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
