package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.action.HotelListBean;
import hostelworld.action.RoomOrderListBean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/ManagerLogoutServlet")
public class ManagerLogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerLogoutServlet() {
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

		// 设置HotelListBean
		HotelListBean hotelListBean = new HotelListBean();
		session.setAttribute("HotelList", hotelListBean);
		RoomOrderListBean roomOrderListBean = new RoomOrderListBean();
		session.setAttribute("RoomOrderList", roomOrderListBean);
		out.print(true);
	}
}
