package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.logic.RoomSaleManager;
import hostelworld.logic.impl.RoomSaleManagerImpl;
import hostelworld.model.HotelAccommocdatedStatistic;

public class HotelAccommocdatedStatisticBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<HotelAccommocdatedStatistic> hotelAccommocdatedStatistics;
	
	private int memberAccommodatedOrderNum;
	
	private int unmemberOrderNum;
	
	public void init() {
		RoomSaleManager roomSaleManager = new RoomSaleManagerImpl();
		int[] nums = roomSaleManager.getAccommodatedNum();
		memberAccommodatedOrderNum = nums[0];
		unmemberOrderNum = nums[1];
	}

	public int getMemberAccommodatedOrderNum() {
		return memberAccommodatedOrderNum;
	}

	public int getUnmemberOrderNum() {
		return unmemberOrderNum;
	}

	public void setMemberAccommodatedOrderNum(int memberAccommodatedOrderNum) {
		this.memberAccommodatedOrderNum = memberAccommodatedOrderNum;
	}


	public void setUnmemberOrderNum(int unmemberOrderNum) {
		this.unmemberOrderNum = unmemberOrderNum;
	}


	public List<HotelAccommocdatedStatistic> getHotelAccommocdatedStatistics() {
		return hotelAccommocdatedStatistics;
	}

	public void setHotelAccommocdatedStatistics(List<HotelAccommocdatedStatistic> hotelAccommocdatedStatistics) {
		this.hotelAccommocdatedStatistics = hotelAccommocdatedStatistics;
	}
	
	public void setHotelAccommocdatedStatistics(HotelAccommocdatedStatistic hAccommocdatedStatistic, int index) {
		hotelAccommocdatedStatistics.set(index, hAccommocdatedStatistic);
	}
	
	public HotelAccommocdatedStatistic getHotelAccommocdatedStatistic(int index) {
		return (HotelAccommocdatedStatistic)hotelAccommocdatedStatistics.get(index);
	}
}
