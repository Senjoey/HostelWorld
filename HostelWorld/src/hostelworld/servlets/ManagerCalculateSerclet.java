package hostelworld.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hostelworld.action.RoomOrderListBean;
import hostelworld.logic.RecordManager;
import hostelworld.logic.UserManager;
import hostelworld.logic.impl.RecordManagerImpl;
import hostelworld.logic.impl.UserManagerImpl;
import hostelworld.model.RoomOrder;
import hostelworld.model.User;

/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/ManagerCalculateServlet")
public class ManagerCalculateSerclet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerCalculateSerclet() {
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
		String saleId = request.getParameter("saleId");
		int k = Integer.parseInt(request.getParameter("order_index"));
		
		RecordManager recordManager = new RecordManagerImpl();
		RoomOrderListBean roomOrderListBean = (RoomOrderListBean)session.getAttribute("RoomOrderList");
		ArrayList<RoomOrder> toCal = new ArrayList<RoomOrder>();
		toCal.add(roomOrderListBean.getRoomOrder(k));
		recordManager.calculate(toCal);//结算
		roomOrderListBean.getRoomOrderList().remove(k);//移除
		UserManager userManager = new UserManagerImpl();
		User user = userManager.find("manager").get(0);
		session.setAttribute("manager", user);
		session.setAttribute("RoomOrderList", roomOrderListBean);
		out.println(true);
	}

}
