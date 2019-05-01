package test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Localhost {
	public static void main(String[] args) {	
		try {
			InetAddress inetAddress = 
					InetAddress.getLocalHost();
			
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			System.out.println( hostname + ":" + hostAddress);
			
			byte[] addresses = inetAddress.getAddress();
			for( byte address : addresses ) {
				System.out.print( address & 0x000000ff );
				System.out.print( '.' );
			}
			System.out.print("\n-------------------------------\n");

			for( String IPAddress : hostIPAddresses() ) {
				System.out.println(IPAddress);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static List<String> hostIPAddresses() throws SocketException {
		List<String> result = new ArrayList<String>();

		for (NetworkInterface ifc : Collections.list(NetworkInterface.getNetworkInterfaces())) {

			// 활성화 된 NIC이 아니면 무시
			if (ifc.isUp() == false) {
				continue;
			}

			// Loopback(127.0.0.1)이면 무시
			if (ifc.isLoopback() == true) {
				continue;
			}

			//if (ifc.isVirtual() == true) {
			//	continue;
			//}
			
			for (InetAddress inetAddr : Collections.list(ifc.getInetAddresses())) {
				
				// Link Address(MAC Adress 무시)
				if (inetAddr.isLinkLocalAddress() == true) {
					continue;
				}
				
				result.add(inetAddr.getHostAddress());
			}
		}
		
		return result;
	}
}
