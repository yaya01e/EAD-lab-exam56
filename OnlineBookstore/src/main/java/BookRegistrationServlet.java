import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class BookRegistrationServlet extends HttpServlet {
    private final BookDAO bookDAO;

    // THIS constructor will be called by Spring
    public BookRegistrationServlet(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title  = req.getParameter("title");
        String author = req.getParameter("author");
        double price;

        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price");
            return;
        }

        Book book = new Book(title, author, price);
        boolean success = bookDAO.addBook(book);

        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            if (success) {
                out.println("<h3 style='color:green;'>✅ Book Registered Successfully!</h3>");
            } else {
                out.println("<h3 style='color:red;'>❌ Failed to register book.</h3>");
            }
        }
    }
}
