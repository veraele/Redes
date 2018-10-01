
import java.io.IOException;
import jpcap.*;

public class Sniffer implements Runnable {

	boolean isRunnig;
	NetworkInterface [] dispositivos;
	JpcapCaptor capturador;
	public static int web,ftp,otros;
	public Sniffer()
	{
		isRunnig=true;
		dispositivos=JpcapCaptor.getDeviceList();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sniffer s= new Sniffer();
		s.run();
	}

	@Override
	public void run() {
		try{
			capturador=JpcapCaptor.openDevice(dispositivos[0],65535, false,20);
			while(isRunnig)
			{
				capturador.processPacket(1, new Receptor());
			}
			capturador.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}
