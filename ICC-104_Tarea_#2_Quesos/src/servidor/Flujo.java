package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Enumeration;


//


public class Flujo extends Thread {
	Socket nsfd;
	DataInputStream FlujoLectura;
	DataOutputStream FlujoEscritura;
	
	public Flujo (Socket sfd) {
		nsfd = sfd;
		try{//
			FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
		}catch(IOException ioe){
			System.out.println("IOException(Flujo): "+ioe);
		}
	}
	public void run()
	{
		while(true)
		{
			try
			{
				String linea = FlujoLectura.readUTF();
				if (!linea.equals(""))
				{
					try {
						File fileIn = new File(linea+".txt");
						FileInputStream fis;
						fis = new FileInputStream(fileIn);
						BufferedReader br = new BufferedReader(new InputStreamReader (fis));
						
						File fileOut = new File(linea+"_respaldo.txt");
						FileOutputStream fos;
						fos = new FileOutputStream(fileOut);
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (fos));
						
						try {
							String texto;
							while ((texto = br.readLine()) != null) {
								
								try
								{
									
									bw.append(texto);
									System.out.println(texto);
									bw.newLine();
								}
								catch (IOException ioe)
								{
									System.out.println("Error: "+ioe);
								}
							}
							bw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch(IOException ioe)
			{
				
			}
		}
	}
	

}
