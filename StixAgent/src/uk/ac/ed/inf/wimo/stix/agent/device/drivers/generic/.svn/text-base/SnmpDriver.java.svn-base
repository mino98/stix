package uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic;

import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;

public class SnmpDriver {
	
	private static Logger log = Logger.getLogger(SnmpDriver.class);

	public static void snmpSet(String strAddress, String snmpPort, String community, String strOID, int Value) {
		strAddress= strAddress + "/" + snmpPort;
		Address targetAddress = GenericAddress.parse(strAddress);
		Snmp snmp;

		try {
			String ipString = CommunicationManager.getCurrentEnvironmentNetworkIp();
			Address originAddress = GenericAddress.parse("udp:" + ipString + "/0");
			
			snmp = new Snmp(new DefaultUdpTransportMapping((UdpAddress) originAddress));
			snmp.listen();
			CommunityTarget target = new CommunityTarget();
			target.setCommunity(new OctetString(community));
			target.setAddress(targetAddress);
			target.setRetries(2);
			target.setTimeout(5000);
			target.setVersion(SnmpConstants.version1);

			PDU pdu = new PDU();
			pdu.add(new VariableBinding(new OID(strOID), new Integer32(Value)));
			pdu.setType(PDU.SET);

			ResponseEvent response = snmp.send(pdu, target);
			if (response.getResponse() == null) {
				log.debug("SNMP Response is null");
			} else {
				log.debug("SNMP Response is OK");
			}
		} catch(Exception e) { 
			log.error("Error while setting SNMP value", e);
		}
	}


	public static String snmpGet(String address, String snmpPort, String community, String strOID) {
		String str = null;
		try {
			OctetString communityOct = new OctetString(community);
			address = address + "/" + snmpPort;
			Address targetaddress = new UdpAddress(address);
			String ipString = CommunicationManager.getCurrentEnvironmentNetworkIp();
			Address originAddress = GenericAddress.parse("udp:" + ipString + "/0");

			TransportMapping transport = new DefaultUdpTransportMapping((UdpAddress) originAddress);
			transport.listen();


			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(communityOct);
			comtarget.setVersion(SnmpConstants.version1);
			comtarget.setAddress(targetaddress);
			comtarget.setRetries(2);
			comtarget.setTimeout(5000);

			PDU pdu = new PDU();
			ResponseEvent response;
			Snmp snmp;
			pdu.add(new VariableBinding(new OID(strOID)));
			pdu.setType(PDU.GET);
			snmp = new Snmp(transport);

			response = snmp.get(pdu,comtarget);
			if(response != null) {
				if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
					PDU pduresponse=response.getResponse();
					str=pduresponse.getVariableBindings().firstElement().toString();
					if(str.contains("=")) {
						int len = str.indexOf("=");
						str=str.substring(len+1, str.length());
					}
				}
			} else {
				log.error("SNMP get timeout: " + address + ":" + snmpPort);
			}
			snmp.close();
		} catch(Exception e) {
			log.error("SNMP get exception: " + address + ":" + snmpPort, e);
		}
		log.debug("SNMP get result: " + str);
		return str;
	}
}
