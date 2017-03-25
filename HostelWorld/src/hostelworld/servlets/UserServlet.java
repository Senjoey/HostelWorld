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

import hostelworld.action.ChargeListBean;
import hostelworld.action.PointListBean;
import hostelworld.action.RoomOrderListBean;
import hostelworld.logic.UserManager;
import hostelworld.logic.impl.UserManagerImpl;
import hostelworld.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String type = request.getParameter("type");
		UserManager um = new UserManagerImpl();
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		if(type.equals("update")){
			User user = (User) session.getAttribute("user");
			String tel = request.getParameter("tel");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			user.setAddress(address);
			user.setAge(age);
			user.setTel(tel);
			boolean result = um.updateUser(user);
			if(result){
				session.setAttribute("user", user);
			}
			out.println(result);
		}
		if(type.equals("stop")){
			User user = (User) session.getAttribute("user");
			user.setState("stopped");
			boolean result = um.updateUser(user);
			if(result){
				session.setAttribute("user", user);
			}
			out.println(result);
		}
		if(type.equals("charge")){
			User user = (User) session.getAttribute("user");
			double money = Double.parseDouble(request.getParameter("money"));
			boolean result = um.charge(user, money);
			if(result){
				session.setAttribute("user", user);
				ChargeListBean chargeListBean = new ChargeListBean();
				List chargeList = um.getChargeRecord(user.getId());
				chargeListBean.setChargeList(chargeList);
				session.setAttribute("ChargeList", chargeListBean);
			}
			out.println(result);
		}
		//积分兑换，首先判断输入的积分数，如果积分数大于剩余积分，则将剩余积分全部兑换
		//积分兑换成功后的体现为用户积分数减少，会员卡余额增加
		if (type.equals("point")) {
			User user = (User) session.getAttribute("user");
			double point = Double.parseDouble(request.getParameter("point"));
			boolean result = um.point(user, point);
			if (result) {
				session.setAttribute("user", user);
				PointListBean poinListBean = new PointListBean();
				List pointList = um.getPointRecord(user.getId());
				poinListBean.setPointList(pointList);
				session.setAttribute("PointList", poinListBean);
			}
			out.println(result);
		}
		if(type.equals("search")){
			String id = request.getParameter("id");
			List list = um.find(id);
			if(list.isEmpty())
				out.print("nouser");
			else{
				User user = (User) list.get(0);
				session.setAttribute("saleuser", user);
				
				ChargeListBean chargeListBean = new ChargeListBean();
				List chargeList = um.getChargeRecord(user.getId());
				chargeListBean.setChargeList(chargeList);
				session.setAttribute("ChargeList", chargeListBean);
				out.print("true");
			}
		}
		if(type.equals("logout")){
			User user = null;
			session.setAttribute("user", user);
			RoomOrderListBean roomOrderListBean = new RoomOrderListBean();
			session.setAttribute("RoomOrderList", roomOrderListBean);
			
			ChargeListBean chargeListBean = new ChargeListBean();
			session.setAttribute("ChargeList", chargeListBean);
			PointListBean pointListBean = new PointListBean();
			session.setAttribute("PointList", pointListBean);
		}
	}

}
