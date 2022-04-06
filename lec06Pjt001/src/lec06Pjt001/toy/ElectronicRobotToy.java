package lec06Pjt001.toy;

import lec06Pjt001.battery.Battery;

public class ElectronicRobotToy {
	
	private Battery battery;
	
	public ElectronicRobotToy() {
		// 그냥 장난감 로봇 껍데기만 생성
	}
	
	// setter로 사용자가 배터리 부착
	// setter로 DI
	public void setBattery(Battery battery) {

		this.battery = battery;
	}
	
}