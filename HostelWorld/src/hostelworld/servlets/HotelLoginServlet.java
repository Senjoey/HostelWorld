package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.action.HotelListBean;
import hostelworld.action.SaleStatisticBean;
import hostelworld.logic.HotelManager;
import hostelworld.logic.RoomSaleManager;
import hostelworld.logic.impl.HotelManagerImpl;
import hostelworld.logic.impl.RoomSaleManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.RoomOrder;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/HotelLoginServlet")
public class HotelLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelLoginServlet() {
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

		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		HotelManager hotelManager = new HotelManagerImpl();
		RoomSaleManager rManager = new RoomSaleManagerImpl();
		HttpSession session = request.getSession(true);

		String result = hotelManager.login(id, password);
			if (!result.equals("nohotel")) {
				if (!result.equals("wrong")) {
					Hotel hotel = (Hotel) hotelManager.find(id).get(0);
					session.setAttribute("hotel", hotel);
					SaleStatisticBean saleStatisticBean = new SaleStatisticBean();
					List<RoomOrder> saleList = rManager.findOrder(id);
					saleStatisticBean.setSaleList(saleList);
					session.setAttribute("SaleStatisticList", saleStatisticBean);
					
					Date today = new Date();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String day = df.format(today);	
					session.setAttribute("date", day);
					
					// 设置HotelListBean
					HotelListBean hotelListBean = new HotelListBean();
					List hotelist = hotelManager.findNoApproved();// 找到所有状态待审批的客栈
					hotelListBean.setHotelList(hotelist);
					session.setAttribute("HotelList", hotelListBean);
				}
			
			out.print(result);
		}
	}
}
