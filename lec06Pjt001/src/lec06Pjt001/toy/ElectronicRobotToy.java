package lec06Pjt001.toy;

import lec06Pjt001.battery.Battery;

public class ElectronicRobotToy {
	
	private Battery battery;
	
	public ElectronicRobotToy() {
		// �׳� �峭�� �κ� �����⸸ ����
	}
	
	// setter�� ����ڰ� ���͸� ����
	// setter�� DI
	public void setBattery(Battery battery) {

		this.battery = battery;
	}
	
}