package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.action.ChargeListBean;
import hostelworld.action.HotelAccommocdatedStatisticBean;
import hostelworld.action.HotelListBean;
import hostelworld.action.MemberOrderListBean;
import hostelworld.action.PointListBean;
import hostelworld.action.RoomOrderListBean;
import hostelworld.logic.HotelManager;
import hostelworld.logic.RecordManager;
import hostelworld.logic.RoomSaleManager;
import hostelworld.logic.UserManager;
import hostelworld.logic.impl.HotelManagerImpl;
import hostelworld.logic.impl.RecordManagerImpl;
import hostelworld.logic.impl.RoomSaleManagerImpl;
import hostelworld.logic.impl.UserManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.HotelAccommocdatedStatistic;
import hostelworld.model.RoomOrder;
import hostelworld.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserManager um = new UserManagerImpl();
		HotelManager hotelManager = new HotelManagerImpl();
		HttpSession session = request.getSession(true);
		if (type.equals("manager")) {// 经理登录
			String result = um.login(id, password, false);
			out.print(result);
			// 设置HotelListBean
			HotelListBean hotelListBean = new HotelListBean();
			List hotelist = hotelManager.findNoApproved();// 找到所有状态待审批的客栈
			for (int i = 0; i < hotelist.size(); i++) {
				Hotel hotel = (Hotel) hotelist.get(i);
				System.out.println("第" + i + "个客栈为" + hotel.getName());
			}
			hotelListBean.setHotelList(hotelist);
			session.setAttribute("HotelList", hotelListBean);
			// 找到所有is_pay状态为0且is_member状态为1的订单（未结算的会员订单）
			RecordManager recordManager = new RecordManagerImpl();
			List<RoomOrder> roomOrders = recordManager.getUncalculatedOrder();
			System.out.println("找到所有待结算的会员订单：");
			for (int i = 0; i < roomOrders.size(); i++) {
				System.out.println("第" + i + "个订单为" + roomOrders.get(i).getSaleId());
			}
			RoomOrderListBean roomOrderListBean = new RoomOrderListBean();
			roomOrderListBean.setRoomOrderList(roomOrders);
			session.setAttribute("RoomOrderList", roomOrderListBean);
			// 找到所有会员历史订单
			RoomSaleManager roomSaleManager = new RoomSaleManagerImpl();
			List<RoomOrder> memberOrderList = roomSaleManager.getMemebrOrderList();
			MemberOrderListBean memberOrderListBean = new MemberOrderListBean();
			memberOrderListBean.setMemberOrderList(memberOrderList);
			session.setAttribute("MemberOrderList", memberOrderListBean);
			//找到各个酒店入住统计信息
			RoomSaleManager manager = new RoomSaleManagerImpl();
			List<HotelAccommocdatedStatistic> list = manager.getHotelAccommocdatedStatistics();

			for (HotelAccommocdatedStatistic hotelStatistic : list) {
				System.out.println(hotelStatistic.getHotelId() + " " + hotelStatistic.getHotelName() + " "
						+ hotelStatistic.getOrderNum());
			}
			HotelAccommocdatedStatisticBean hotelAccommocdatedStatisticBean = new HotelAccommocdatedStatisticBean();
			hotelAccommocdatedStatisticBean.init();
			hotelAccommocdatedStatisticBean.setHotelAccommocdatedStatistics(list);
			session.setAttribute("HotelAccommocdatedStatisticList", hotelAccommocdatedStatisticBean);
			
			session.setAttribute("manager", um.find(id).get(0));
			return;

		} else {// 会员登录

			String result = um.login(id, password, true);
			if (!result.equals("nouser")) {
				boolean cookieFound = false;
				Cookie cookie = null;
				Cookie[] cookies = request.getCookies();
				if (null != cookies) {
					// Look through all the cookies and see if the
					// cookie with the login info is there.
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("LoginCookie")) {
							cookieFound = true;
							break;
						}
					}
				}
				if (cookieFound) {
					// If the cookie exists update the value only
					if (!id.equals(cookie.getValue())) {
						cookie.setValue(id);
						response.addCookie(cookie);
					}
				} else {
					// If the cookie does not exist, create it and set value
					cookie = new Cookie("LoginCookie", id);
					cookie.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookie);
				}

				if (!result.equals("wrong")) {
					// create a session to show that we are logged in
					User user = (User) um.find(id).get(0);
					session.setAttribute("user", user);

					ChargeListBean chargeListBean = new ChargeListBean();
					List chargeList = um.getChargeRecord(id);
					chargeListBean.setChargeList(chargeList);
					session.setAttribute("ChargeList", chargeListBean);

					PointListBean pointListBean = new PointListBean();
					List pointList = um.getPointRecord(id);
					pointListBean.setPointList(pointList);
					session.setAttribute("PointList", pointListBean);
					System.out.println("PointListBean's size is " + pointListBean.getPointList().size());

					HotelListBean hotelListBean = new HotelListBean();
					List hotelist = hotelManager.find();
					hotelListBean.setHotelList(hotelist);
					session.setAttribute("HotelList", hotelListBean);

					RoomOrderListBean roomOrderListBean = new RoomOrderListBean();
					List roomOrderList = um.getRoomOrder(id);
					roomOrderListBean.setRoomOrderList(roomOrderList);
					for (int i = 0; i < roomOrderList.size(); i++) {
						RoomOrder roomOrder = (RoomOrder) roomOrderList.get(i);
					}
					session.setAttribute("RoomOrderList", roomOrderListBean);

				}
			}
			out.print(result);
		}

	}

}
