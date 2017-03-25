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
import hostelworld.logic.RoomManager;
import hostelworld.logic.impl.RoomManagerImpl;
import hostelworld.model.Room;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	  /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomManager sManager = new RoomManagerImpl();
		String type = request.getParameter("type");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		//更新
		if (type.equals("update")) {
			Room room = new Room();
			String id = request.getParameter("id");
			int price = Integer.parseInt(request.getParameter("price"));
			String roomType = request.getParameter("type");
			room.setId(id);
			room.setPrice(price);
			room.setType(roomType);
			boolean result = sManager.update(room);
			out.println(result);
			if (result) {
				RoomListBean roomListBean = new RoomListBean();
				List roomList = sManager.getRoomList();
				roomListBean.setRoomList(roomList);
				session.setAttribute("RoomList", roomListBean);
			}
		}
		//删除
		if (type.equals("delete")) {
			String id = request.getParameter("id");
			boolean result = sManager.delete(id);
			out.println(result);
			if (result) {
				RoomListBean roomListBean = new RoomListBean();
				List roomList = sManager.getRoomList();
				roomListBean.setRoomList(roomList);
				session.setAttribute("RoomList", roomListBean);
			}
		}
		//添加
		if (type.equals("add")) {
			Room room = new Room();
			String id = request.getParameter("id");
			int price = Integer.parseInt(request.getParameter("price"));
			room.setId(id);
			room.setPrice(price);
			boolean result = sManager.add(room);
			out.println(result);
			if (result) {
				RoomListBean roomListBean = new RoomListBean();
				List roomList = sManager.getRoomList();
				roomListBean.setRoomList(roomList);
				session.setAttribute("RoomList", roomListBean);
			}
		}
	}
	
}
