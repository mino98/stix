package uk.ac.ed.inf.wimo.stix.agent.device;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;

/**
 * Class that represents a physical device.
 * @author damon
 *
 */
public class Device {
	
	private static Logger log = Logger.getLogger(Device.class);

	private String name;
	private String description;
	private String type;
	private String address;
	private String location;
	private GenericDeviceDriver deviceDriver;
	private String stixControl;
	
	/* private Hashtable<String, String> otherProperties = new Hashtable<String, String>(); */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GenericDeviceDriver getDeviceDriver() {
		return deviceDriver;
	}

	public void setDeviceDriver(GenericDeviceDriver deviceDriver) {
		this.deviceDriver = deviceDriver;
	}

	public String getStixControl() {
		return stixControl;
	}

	public void setStixControl(String stixControl) {
		this.stixControl = stixControl;
	}

	public void setProperty(String name, String value) {
		if ("description".equals(name)) {
			setDescription(value);
		} else if ("type".equals(name)) {
			setType(value);
		} else if ("address".equals(name)) {
			setAddress(value);
		} else if ("location".equals(name)) {
			setLocation(value);				
		} else if ("driver".equals(name)) {
			GenericDeviceDriver driver = null;
			try {
				driver = (GenericDeviceDriver) Class.forName(value).newInstance();
				driver.setDevice(this);
				setDeviceDriver(driver);
				log.debug("Driver " + value + " for device " + name + " instantiated");
			} catch (Exception e) {
				log.error("Error loading driver: " + value, e);
				// TODO throw error?
			}
		} else if ("stixcontrol".equals(name)) {
			setStixControl(value);
		} else {
			GenericDeviceDriver driver = getDeviceDriver();
			if (driver != null) {
				driver.setProperty(name, value);
			} else {
				log.error("Error retrieving driver while setting " + name +
						" = " + value);
				// TODO throw error?
			}
		}
	}
	
	public String getProperty(String name) {
		if ("description".equals(name)) {
			return getDescription();
		} else if ("type".equals(name)) {
			return getType();
		} else if ("address".equals(name)) {
			return getAddress();
		} else if ("location".equals(name)) {
			return getLocation();				
		} else if ("driver".equals(name)) {
			return getDeviceDriver().getClass().getName();
		} else if ("stixcontrol".equals(name)) {
			return getStixControl();
		} else {
			GenericDeviceDriver driver = getDeviceDriver();
			if (driver != null) {
				return driver.getProperty(name);
			} else {
				log.error("Error retrieving retrieving device property " + name);
				// TODO throw error?
			}
		}	
		return null;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	
	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "Device ( "
	        + super.toString() + TAB
	        + "name = " + this.name + TAB
	        + "description = " + this.description + TAB
	        + "type = " + this.type + TAB
	        + "address = " + this.address + TAB
	        + "location = " + this.location + TAB
	        + "deviceDriver = " + this.deviceDriver + TAB
	        + "stixControl = " + this.stixControl + TAB
	        + " )";
	
	    return retValue;
	}
}
