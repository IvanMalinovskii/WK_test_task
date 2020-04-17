package org.example.bsystem.servlets;

import org.example.bsystem.logic.services.GettingClientsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * process request for work with clients information
 */
@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {
    private GettingClientsService service;

    /**
     * initializes servlet fields
     * @param config servlet config
     * @throws ServletException servlet exception
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        service = new GettingClientsService();
    }

    /**
     * process get requests and writes a response string with a result
     * @param req http request
     * @param resp http response
     * @throws ServletException servlet exception
     * @throws IOException io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String contentType = req.getParameter("content");
        final GettingClientsService.ContentPair pair = service.getResult(contentType);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(pair.getContentType());
        PrintWriter writer = resp.getWriter();
        writer.write(pair.getContent());
    }
}
