package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //서블릿이 호출되면 서비스 메소드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //쿼리 파라미터 조회
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");                  //단순 문자로 보내기 위함 -> content type
        response.setCharacterEncoding("utf-8");                 //문자를 인코딩 -> content type
        response.getWriter().write("hello " + username);    //write() 의 메세지는 HttpMessageBody 에 들어간다

    }
}
