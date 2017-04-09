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

import hostelworld.action.SaleStatisticBean;
import hostelworld.logic.RecordManager;
import hostelworld.logic.RoomSaleManager;
import hostelworld.logic.impl.RecordManagerImpl;
import hostelworld.logic.impl.RoomSaleManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.PlanRoom;
import hostelworld.model.Room;
import hostelworld.model.RoomCart;
import hostelworld.model.RoomOrder;
import hostelworld.model.RoomSaleState;
import hostelworld.model.Hotel.HotelState;
import hostelworld.model.PlanRoom.RoomState;

/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
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

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();

		Hotel hotel = (Hotel) session.getAttribute("hotel");// 获取当前的客栈
		String date = (String) session.getAttribute("date");// 获取当天日期

		// 查询当前客栈是否通过经理审批
		if (hotel.getState() != HotelState.approved) {
			out.print("noapproved");
			return;
		}

		String type = request.getParameter("type");
		RecordManager rManager = new RecordManagerImpl();
		RoomSaleManager roomSaleManager = new RoomSaleManagerImpl();

		if (type.equals("checkRoom")) {// 查询房间
			RoomCart cart = (RoomCart) session.getAttribute("salecart");
			cart = new RoomCart();
			cart.init();
			String roomId = request.getParameter("room_id");
			System.out.println("The hotel is " + hotel.getHotelId() + " the roomId is " + roomId + " the date is " + date);
			Room room = rManager.getRoom(hotel.getHotelId(), roomId, date);
			if (room == null) {
				out.print("noroom");
			} else {
				RoomState roomState = rManager.getRoomState(hotel.getHotelId(), roomId, date);
				cart.add(room, roomState);
				cart.setHidePayPart(true);
				cart.setHideEditTenantName(true);
				if (roomState == RoomState.accommodating) {
					cart.setHidePayPart(false);
					cart.setHideEditTenantName(false);
				}
				if (roomState == RoomState.ordered || roomState == RoomState.left) {
					RoomOrder roomOrder = rManager.getRoomOrder(hotel.getHotelId(), roomId, date);// 找到订单
					System.out.println("房客信息： " + roomOrder.getTenantName());

					cart.addRoomOrder(roomOrder);
				}
				if (roomState == RoomState.ordered) {
					cart.setHideEditTenantName(false);
				}

				if (roomState == RoomState.accommodated) {
					RoomOrder roomOrder = rManager.getRoomOrder(hotel.getHotelId(), roomId, date);// 找到订单
					cart.setHideEditTenantName(false);
					cart.addRoomOrder(roomOrder);
				}

				System.out.println("Is the cart's roomOrder empty? " + cart.getRoomOrders().isEmpty());
				session.setAttribute("salecart", cart);// 库存相关
				out.print(roomState.toString());
			}
		} else if (type.equals("delete")) {// 从登记列表中删去房间
			RoomCart cart = (RoomCart) session.getAttribute("salecart");
			int index = Integer.parseInt(request.getParameter("room_index"));
			String roomId = ((PlanRoom) cart.getPlanRooms().get(index)).getId();

			Room room = rManager.getRoom(hotel.getHotelId(), roomId, date);
			cart.delete(room);
			RoomOrder roomOrder = rManager.getRoomOrder(hotel.getHotelId(), roomId, date);
			// 判断是否有该订单
			if (roomOrder != null) {
				cart.deleteRoomOrder(roomOrder);
			}
			// 判断cart是否为空
			if (cart.getPlanRooms().isEmpty()) {
				cart.setHidePayPart(true);
			}
			session.setAttribute("salecart", cart);
			out.println(cart.getTotal());
		} else if (type.equals("modify")) {// 修改房间的状态
			RoomCart cart = (RoomCart) session.getAttribute("salecart");
			int index = Integer.parseInt(request.getParameter("room_index"));
			String roomId = ((PlanRoom) cart.getPlanRooms().get(index)).getId();

			RoomState currentState = rManager.getRoomState(hotel.getHotelId(), roomId, date);
			RoomState toState = null;
			cart.setHideEditTenantName(true);
			if (currentState == RoomState.accommodating) {
				toState = RoomState.accommodated;
				cart.setHideEditTenantName(false);
			} else if (currentState == RoomState.accommodated) {
				toState = RoomState.left;
			} else if (currentState == RoomState.ordered) {
				RoomOrder roomOrder = rManager.getRoomOrder(hotel.getHotelId(), roomId, date);// 找到订单
				cart.addRoomOrder(roomOrder);
				toState = RoomState.accommodated;
				// 顺便修改sale表
				roomOrder.setSaleState(toState.toString());
				rManager.updateSaleState(roomOrder);
				//修改对应SaleStatisticBean
				SaleStatisticBean saleStatisticBean = new SaleStatisticBean();
				List<RoomOrder> saleList = roomSaleManager.findOrder(hotel.getHotelId());
				saleStatisticBean.setSaleList(saleList);
				session.setAttribute("SaleStatisticList", saleStatisticBean);
				cart.setHideEditTenantName(false);
			} else {
				toState = RoomState.left;
			}
			PlanRoom planRoom = rManager.modifyRoom(hotel.getHotelId(), roomId, toState.toString(), date);
			cart.modify(planRoom);
			session.setAttribute("salecart", cart);
			out.println(cart.getTotal());
		} else if (type.equals("pay")) {// 确认付款，修改房间销售记录的状态
			String paytype = request.getParameter("paytype");
			RoomCart cart = (RoomCart) session.getAttribute("salecart");
			// 付款方式paytype为非会员
			int isMember = 0;// 非会员
			RoomOrder roomOrder = rManager.shopping(hotel.getHotelId(), cart, RoomSaleState.accommodated.toString(), isMember,
					null, 0, date, request.getParameter("tenantName"));// 添加一条销售记录
			//修改对应SaleStatisticBean
			SaleStatisticBean saleStatisticBean = new SaleStatisticBean();
			List<RoomOrder> saleList = roomSaleManager.findOrder(hotel.getHotelId());
			saleStatisticBean.setSaleList(saleList);
			session.setAttribute("SaleStatisticList", saleStatisticBean);
			out.print(true);
			cart = new RoomCart();
			cart.init();
			cart.addRoomOrder(roomOrder);
			session.setAttribute("salecart", cart);
			// 付款方式paytype为会员
		} else if (type.equals("editTenantName")) {// 修改入住人的名字
			RoomCart cart = (RoomCart) session.getAttribute("salecart");
			int index = Integer.parseInt(request.getParameter("room_index"));
			String roomId = ((PlanRoom) cart.getPlanRooms().get(index)).getId();
			// 判断是否已创立订单，如无，则不做任何操作
			RoomOrder roomOrder = rManager.getRoomOrder(hotel.getHotelId(), roomId, date);
			// 判断是否有该订单
			if (roomOrder == null) {
				out.print(false);
				return;
			} else {
				// 若已有订单记录，则修改房客信息
				roomOrder.setTenantName(request.getParameter("tenantName"));
				out.print(rManager.updateTenantName(roomOrder));
				//修改对应SaleStatisticBean
				SaleStatisticBean saleStatisticBean = new SaleStatisticBean();
				List<RoomOrder> saleList = roomSaleManager.findOrder(hotel.getHotelId());
				saleStatisticBean.setSaleList(saleList);
				session.setAttribute("SaleStatisticList", saleStatisticBean);
			}
		}
	}
}
