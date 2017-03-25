package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.logic.RoomPlanManager;
import hostelworld.logic.impl.RoomPlanManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.PlanRoom;
import hostelworld.model.RoomPlan;
import hostelworld.model.Hotel.HotelState;

/**
 * Servlet implementation class PlanServlet
 */
@WebServlet("/RoomPlanServlet")
public class RoomPlanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomPlanServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("嘤嘤嘤");
		RoomPlanManager roomPlanManager = new RoomPlanManagerImpl();
		String type = request.getParameter("type");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		Hotel hotel = (Hotel) session.getAttribute("hotel");
		// 查询当前客栈是否通过经理审批
		if (hotel.getState() != HotelState.approved) {
			out.print("noapproved");
			return;
		}
		if (type.equals("add")) {
			RoomPlan plan = new RoomPlan();
			String planId = roomPlanManager.GetNewId();

			System.out.println("hotelId: In Servlet: " + hotel.getHotelId());
			String date = request.getParameter("roomPlanDate");// 获取输入日期
			System.out.println("PlanDate: " + date);
			plan.setPlanId(planId);
			plan.setHotelId(hotel.getHotelId());
			plan.setDate(date);
			plan.initRoom();
			plan.initToday();
			String[] prices = request.getParameter("prices").split(",");
			for (int i = 0; i < plan.getRooms().size(); i++) {
				PlanRoom planRoom = plan.getRooms().get(i);
				planRoom.setPrice(Integer.parseInt(prices[i]));
			}
			boolean result = roomPlanManager.add(plan);
			if (result) {
				out.print(true);
			}
		}
	}

}
