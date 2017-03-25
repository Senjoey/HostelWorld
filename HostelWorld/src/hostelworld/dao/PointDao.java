package hostelworld.dao;

import java.util.List;

public interface PointDao {

	public List find(String userId);
	
	public boolean add(String userId, String date, double pointSum, double moneySum, double balance);
}
