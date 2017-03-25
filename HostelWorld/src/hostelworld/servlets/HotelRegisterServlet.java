package hostelworld.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hostelworld.logic.HotelManager;
import hostelworld.logic.impl.HotelManagerImpl;
import hostelworld.model.Hotel;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/HotelRegisterServlet")
public class HotelRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public HotelRegisterServlet() {
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
		Hotel hotel = new Hotel();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		hotel.init();
		hotel.setAddress(address);
		hotel.setName(name);
		hotel.setBankCard(card);
		hotel.setHotelId(id);
		hotel.setPassword(password);
		hotel.setTel(tel);
		HotelManager hotelManager = new HotelManagerImpl();
		boolean result = hotelManager.addHotel(hotel);
		System.out.println("Hotel Register: " + result + "  and the Hotel ID is " + id + "\n");
		PrintWriter out = response.getWriter();
		out.print(result);
		
		if(result){
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
		}
	}

}
