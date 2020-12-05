package servlets;

import services.Helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private Helper helper;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        helper.render(req, resp, "main.ftl",new HashMap<>());
    }

    @Override
    public void init() {
        helper = new Helper();
    }
}