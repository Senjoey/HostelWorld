package hostelworld.action;

import java.io.Serializable;
import java.util.List;

import hostelworld.model.Point;

public class PointListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List poinList;
	
	public List getPointList() {
		return poinList;
	}
	
	public void setPointList(List pointList) {
		this.poinList = pointList;
	}
	
	public void setPointList(Point point, int index) {
		poinList.set(index, point);
	}
	
	public Point getPoint(int index) {
		return (Point) poinList.get(index);
	}
}
