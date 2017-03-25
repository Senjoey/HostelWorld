package hostelworld.logic;

import java.util.List;

import hostelworld.model.RoomPlan;

public interface RoomPlanManager {

	/**
	 * 获得所有销售计划
	 * @return
	 */
	public List<RoomPlan> getAllPlan();
	
	/**
	 * 根据计划id获得销售计划
	 * @param id
	 * @return
	 */
	public List<RoomPlan> getPlan(String id);
	
	/**
	 * 增加销售计划
	 * @param roomPlan
	 * @return
	 */
	public boolean add(RoomPlan roomPlan);
	
	/**
	 * 修改销售计划
	 * @param roomPlan
	 * @return
	 */
	public boolean update(RoomPlan roomPlan);
	
	public String GetNewId();
	
}
