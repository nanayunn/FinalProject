package serial;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;




public class SerialReadWrite implements SerialPortEventListener {
	HashMap<String, ObjectOutputStream>
	maps = new HashMap<>();

   CommPortIdentifier commPortIdentifier;
   CommPort comPort;
   InputStream in;
   BufferedInputStream bin;
   OutputStream out;
   String id;// "00000000";
   String data; // "0000000000000000";
   String msg; // id+data;
   

   public int battery;
   
   static Socket socket;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getData() {
      return data;
   }

   public void setData(String data) {
      this.data = data;
   }

   public void setMsg() {
      this.msg = getId()+getData();
   }
   
   public String getMsg() {
      return this.msg;
   }


	public int getBattery() {
		return battery;
	}
	
	public void setBattery(int battery) {
		this.battery = battery;
	}
	
	public SerialReadWrite() {
	   }

   public SerialReadWrite(String portName) throws Exception {
      commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
      System.out.println("Identifier Com Port!");
      connect();
      System.out.println("Connect Com Port!");
      new Thread(new Serialwrite()).start();
      System.out.println("Start Can Network!");
   }

   public void connect() throws Exception {

      if (commPortIdentifier.isCurrentlyOwned()) { // 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙構占� 占쌍댐옙占쏙옙
         System.out.println("Port is currently in use...");
      } else {
         comPort = commPortIdentifier.open(this.getClass().getName(), 2000);
         if (comPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) comPort;
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            serialPort.setSerialPortParams(9600, // 占쏙옙탉撻占�
                  SerialPort.DATABITS_8, // 占쏙옙占쏙옙占쏙옙 占쏙옙트
                  SerialPort.STOPBITS_1, // stop 占쏙옙트
                  SerialPort.PARITY_NONE); // 占싻몌옙티
            in = serialPort.getInputStream();
            bin = new BufferedInputStream(in);
            out = serialPort.getOutputStream();
         } else {
            System.out.println("This port is not SerialPort.");
         }
      }

   }

   @Override
   public void serialEvent(SerialPortEvent event) {
		//lb.setText("접속자:"+maps.size());
      switch (event.getEventType()) {
      case SerialPortEvent.BI:
      case SerialPortEvent.OE:
      case SerialPortEvent.FE:
      case SerialPortEvent.PE:
      case SerialPortEvent.CD:
      case SerialPortEvent.CTS:
      case SerialPortEvent.DSR:
      case SerialPortEvent.RI:
      case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
         break;
      case SerialPortEvent.DATA_AVAILABLE:
         byte[] readBuffer = new byte[128];

         try {

            while (bin.available() > 0) {
               int numBytes = bin.read(readBuffer);
            }

            String ss = new String(readBuffer);
            System.out.println("Receive Low Data:" + ss + "||");
       
            if(ss.charAt(1)=='U') {
               String data =parseData(ss);
              
            }
            
         } catch (Exception e) {
            e.printStackTrace();
         }
         break;
      }

   }

   public void send(String msg) {
      new Thread(new Serialwrite(msg)).start();
   }
   
   public void adminSend(String msg) {
      new Thread(new Serialwrite(msg)).start();
   }
   
//   public int[] setRanValue() {
//	   int[] batteryVal = new int[30];
//	   for(int i = 0;i<batteryVal.length;i++) {
//		   batteryVal[i] = (int)(Math.random()*100+1);
//		   for (int j = 0;j<i;j++) {
//			   if(batteryVal[j]==batteryVal[j]) {
//				   i--;
//				   break;
//			   }
//		   }
//	   }
//	   Arrays.sort(batteryVal);
//	   System.out.println(batteryVal);
//	   return batteryVal;
//   }
   
   class Serialwrite implements Runnable {

      String data;

      public Serialwrite() {
         this.data = ":G11A9\r";
      }

      public Serialwrite(String msg) {
         this.data = convertData(msg);
      }

      public String convertData(String msg) {
         String id = "00000001";
         //msg = msg.toUpperCase();
         System.out.println("convertData msg: "+msg);
         if(msg.length() == 1) {
            msg=id+"000000000000000"+msg;
         }else if(msg.length() == 2) {
            msg=id+"00000000000000"+msg;
         }else if(msg.length() == 3) {
            msg=id+"0000000000000"+msg;
         }else {
            System.out.println("msg가 null입니다.");
         }
         msg = "W28" + msg;

         char c[] = msg.toCharArray();
         int checkSum = 0;

         for (char ch : c) {
            checkSum += ch;
         }

         checkSum = (checkSum & 0xFF);

         String result = ":";
         result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
         System.out.println("result: "+result);
         return result;

      }

      @Override
      public void run() {

         byte[] outData = data.getBytes();

         try {
            out.write(outData);
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

   }

   public static String parseData(String ss) {
      StringBuilder sb = new StringBuilder(ss);
      String id= sb.substring(4, 12);
      String txt = sb.substring(25, 28);
      txt=Integer.parseInt(txt)+"";
      System.out.println("id:"+id+" "+ "txt : "+txt);
      return txt;
   }


   
   
  

}