package lec06Pjt001.toy;

import lec06Pjt001.battery.Battery;

public class ElectronicRadioToy {
	
	private Battery battery;
	
	// 생성자로도 DI, setter로도 DI -> 유연성 강하다 
	public ElectronicRadioToy(Battery battery) {
		this.battery = battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
}