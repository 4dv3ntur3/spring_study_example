package lec06Pjt001.toy;

import lec06Pjt001.battery.Battery;
import lec06Pjt001.battery.NormalBattery;

public class ElectronicCarToy {
	
	private Battery battery;
	
	// 배터리 한 번 생기면 접근 불가 
	// 생성자로 DI
	public ElectronicCarToy() {
		battery = new NormalBattery();
	}
	
}