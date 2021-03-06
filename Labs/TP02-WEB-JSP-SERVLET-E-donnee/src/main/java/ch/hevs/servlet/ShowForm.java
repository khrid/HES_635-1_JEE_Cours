package ch.hevs.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.Client;


public class ShowForm extends HttpServlet {

	private static final long serialVersionUID = -2342373925379831320L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		try {
			Bank banque = new Bank();
			List<Client> clients = banque.getClients();
			request.setAttribute("clients", clients);
			url = "form.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
