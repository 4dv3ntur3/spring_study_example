package lec06Pjt001.toy;

import lec06Pjt001.battery.Battery;

public class ElectronicRadioToy {
	
	private Battery battery;
	
	// �����ڷε� DI, setter�ε� DI -> ������ ���ϴ� 
	public ElectronicRadioToy(Battery battery) {
		this.battery = battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
}