package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.RoomOrder;

public class SaleStatisticBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<RoomOrder> saleList;

	
	public List<RoomOrder> getSaleList() {
		return saleList;
	}

	
	public void setSaleList(List<RoomOrder> saleList) {
		this.saleList = saleList;
	}
	
	
	public void setSaleList(RoomOrder roomOrder, int index) {
		saleList.set(index, roomOrder);
	}
	
	public RoomOrder getSale(int index) {
		return (RoomOrder) saleList.get(index);
	}
	
}
