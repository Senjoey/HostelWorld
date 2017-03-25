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
import hostelworld.logic.HotelManager;
import hostelworld.logic.impl.HotelManagerImpl;
import hostelworld.model.Hotel;
import hostelworld.model.Hotel.HotelState;

/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	 /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
    }
    
    /**
  	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// TODO Auto-generated method stub
  		response.getWriter().append("Served at: ").append(request.getContextPath());
  	}
  	
  	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String id = request.getParameter("hotelId");
		int index = Integer.parseInt(request.getParameter("hotel_index"));
		
		HotelManager hotelManager = new HotelManagerImpl();
		Hotel hotel = hotelManager.getHotel(id);
		
		if (type.equals("approve")) {//审批通过，修改hotel状态
			hotel.setState(HotelState.approved.toString());
		}
		else if (type.equals("disapprove")) {
			hotel.setState(HotelState.disapproved.toString());
		}
		hotelManager.updateHotel(hotel);//更新客栈状态
		HotelListBean hotelListBean = (HotelListBean) session.getAttribute("HotelList");
		hotelListBean.getHotelList().remove(index);//从HotelListBean中移除
		session.setAttribute("HotelList", hotelListBean);
		out.println(true);
	}
}
