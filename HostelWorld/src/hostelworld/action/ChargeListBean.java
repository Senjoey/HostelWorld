package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.Charge;

public class ChargeListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List chargeList;

	
	public List getChargeList() {
		return chargeList;
	}

	
	public void setChargeList(List chargeList) {
		this.chargeList = chargeList;
	}
	
	
	public void setChargeList(Charge charge, int index) {
		chargeList.set(index, charge);
	}
	
	public Charge getCharge(int index) {
		return (Charge) chargeList.get(index);
	}

	
}
