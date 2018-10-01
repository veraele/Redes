import jpcap.*;
import jpcap.packet.*;
//import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

public class Receptor implements PacketReceiver {

	public Receptor()
	{
		
	}
	
	@Override
	public void receivePacket(Packet packet) {
		// TODO Auto-generated method stub
		
		if(packet instanceof IPPacket)
		{
			IPPacket ip=(IPPacket) packet;
			System.out.println("IP version: "+ ip.version +" source: "+ip.src_ip+" destination: "+ip.dst_ip
					+"\nlength: "+ip.length+" len: "+ip.len+"\n------------------");
		}
		if(packet instanceof ICMPPacket)
		{
			ICMPPacket icmp=(ICMPPacket) packet;
			System.out.println("ICMP source: "+icmp.src_ip+" destination: "+ icmp.dst_ip+" lenght:"+icmp.length+
					" len "+icmp.len+" protocol "+icmp.protocol+"\n---------------------" );
		}
		if(packet instanceof TCPPacket)
		{
			TCPPacket tcp=(TCPPacket) packet;
			System.out.println(" TCP Source: "+ tcp.src_ip +"\nDestination: "+tcp.dst_ip+"\nProtocolo: " + tcp.protocol
					+"length: "+tcp.length+"\nHeader: "+tcp.header+"\nData link: "+ tcp.datalink+"\n------------------");
			if(tcp.src_port==80||tcp.src_port==443||tcp.dst_port==80||tcp.dst_port==443)
				System.err.println("Paquetes que pasan por web "+ ++Sniffer.web);
			else if(tcp.src_port==21||tcp.src_port==22||tcp.dst_port==21||tcp.dst_port==22)
				System.err.println("Paquetes que pasan por ftp "+ ++Sniffer.ftp);
			else
				System.out.println("Paquetes otros "+ ++Sniffer.otros);
		}
	}

}
