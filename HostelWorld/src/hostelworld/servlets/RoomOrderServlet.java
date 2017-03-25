package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.action.RoomListBean;
import hostelworld.action.RoomOrderListBean;
import hostelworld.logic.HotelManager;
import hostelworld.logic.RecordManager;
import hostelworld.logic.UserManager;
import hostelworld.logic.impl.HotelManagerImpl;
import hostelworld.logic.impl.RecordManagerImpl;
import hostelworld.logic.impl.UserManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.Room;
import hostelworld.model.RoomCart;
import hostelworld.model.RoomOrder;
import hostelworld.model.RoomSaleState;
import hostelworld.model.User;
import hostelworld.model.PlanRoom.RoomState;
import hostelworld.model.User.UserState;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/RoomOrderServlet")
public class RoomOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomOrderServlet() {
		super();
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
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		RecordManager recordManager = new RecordManagerImpl();
		UserManager um = new UserManagerImpl();

		// 根据酒店、入住日期和房间类型找到预售信息
		if (type.equals("find")) {
			String hotelId = request.getParameter("hotel");
			String date = request.getParameter("date");
			List<Room> list = recordManager.getList(hotelId, date, "bigBedroom");
			List<Room> listTwo = recordManager.getList(hotelId, date, "standardRoom");
			if (listTwo != null) {
				for (int i = 0; i < listTwo.size(); i++) {
					list.add(listTwo.get(i));
				}
			}
			List<Room> listThree = recordManager.getList(hotelId, date, "businessRoom");
			if (listThree != null) {
				for (int i = 0; i < listThree.size(); i++) {
					list.add(listThree.get(i));
				}
			}
			
			if (list == null) {
				out.print("noplan");
			} else {
				int all = list.size();
				for (int i = 0; i < all; i++) {
					RoomState roomState = recordManager.getRoomState(hotelId, list.get(i).getId(), date);
					if (roomState != RoomState.accommodating) {
						list.remove(i);
						i--;
						all--;
					}
				}
				RoomListBean roomListBean = new RoomListBean();
				roomListBean.setRoomList(list);
				session.setAttribute("RoomList", roomListBean);

				HotelManager hotelManager = new HotelManagerImpl();
				Hotel hotel = hotelManager.getHotel(hotelId);
				session.setAttribute("hotel", hotel);
				session.setAttribute("date", date);
				out.print("true");
			}
		} else if (type.equals("orderRoom")) {// 预定房间 跳到我的订单界面
			String hotelId = ((Hotel) session.getAttribute("hotel")).getHotelId();
			String date = (String) session.getAttribute("date");
			String roomId = request.getParameter("roomId");
			User user = (User) session.getAttribute("user");
			RoomState roomState = recordManager.getRoomState(hotelId, roomId, date);
			if (roomState != RoomState.accommodating) {
				out.print(roomState.toString());
				;
				return;
			}
			double balance = user.getBalance();
			// 查找房间价格
			Room room = recordManager.getRoom(hotelId, roomId, date);
			double price = room.getPrice();

			if (user.getState() != UserState.active) {
				out.print("noactive");
				return;
			}
			// 扣会员余额
			if (balance < price) {
				out.print("nobalance");
				return;
			} else {
				user.setBalance(balance - price);
				um.updateUser(user);
				session.setAttribute("user", user);// 减少会员的余额
				User manager = um.find("manager").get(0);// 增加总经理的余额
				manager.setBalance(manager.getBalance() + price);
				manager.setCharge(manager.getCharge() + price);// 增加总经理的充值 （
																// 已结算=充值-余额；已盈利=已结算*0.25；待结算=充值-已结算-盈利）
				um.updateUser(manager);

				RoomCart cart = new RoomCart();
				cart.init();
				cart.add(room, RoomState.ordered);
				// 添加一条销售记录 更新库存
				System.out.println("To add an order");
				RoomOrder roomOrder = recordManager.shopping(hotelId, cart, RoomSaleState.ordered.toString(), 1,
						user.getId(), 0, date, user.getId());
				boolean result = roomOrder != null;
				if (result) {
					System.out.println("Successfully add an order");
				} else {
					System.out.println("添加失败");
				}
				out.print(result);
				// 更新RoomOrderListBean
				RoomOrderListBean roomOrderListBean = (RoomOrderListBean) session.getAttribute("RoomOrderList");
				roomOrderListBean.getRoomOrderList().add(roomOrder);
				session.setAttribute("RoomOrderList", roomOrderListBean);
			}
		} else if (type.equals("deleteOrder")) {// 取消预定（传过来订单编号）
			String saleId = request.getParameter("saleId");
			// 增加会员卡余额
			RoomOrder roomOrder = recordManager.getRoomOrder(saleId);
			double price = roomOrder.getPrice();
			User user = (User) session.getAttribute("user");
			user.setBalance(user.getBalance() + price);
			um.updateUser(user);

			User manager = um.find("manager").get(0);// 减少总经理的余额
			manager.setBalance(manager.getBalance() - price);
			manager.setCharge(manager.getCharge() - price);
			um.updateUser(manager);
			// 删除销售记录 更新库存
			boolean result = recordManager.cancelShopping(saleId);
			out.println(result);
			// 更新RoomOrderListBean
			RoomOrderListBean roomOrderListBean = (RoomOrderListBean) session.getAttribute("RoomOrderList");
			List<RoomOrder> roomOrders = roomOrderListBean.getRoomOrderList();
			int index = 0;
			for (int i = 0; i < roomOrders.size(); i++) {
				if (roomOrders.get(i).getSaleId().equals(saleId)) {
					index = i;
				}
			}
			roomOrders.remove(index);
			roomOrderListBean.setRoomOrderList(roomOrders);
			session.setAttribute("RoomOrderList", roomOrderListBean);
		}
	}
}
