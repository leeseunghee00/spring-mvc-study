package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);                  //code = 200 ... 코드로 지정하는 것보다 이렇게 명확히 알 수 있도록 하는 게 좋다.
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);       //code = 400

        //[response-headers] -> 직접 세팅하는 방식
        response.setHeader("Content-type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");   //내가 원하는 임의로 헤더를 만들 수도 있다.

        //[Header 편의 메서드] -> 자동으로 세팅되는 방식
        content(response);
        cookie(response);
        redirect(response);

        //[메시지 바디]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    /**
     * Content 편의 메서드
     */
    private void content(HttpServletResponse response) {
        //[Content-Type] text/plain;charset=utf-8
        //[Content-Length] 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //response.setContentLength(2); //(생략시 자동 생성)
    }

    /**
     * Cookie 편의 메서드
     */
    private void cookie(HttpServletResponse response) {
        //[Set-Cookie] myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    /**
     * Redirect 편의 메서드
     */
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        //위 두 코드를 sendRedirect 로 간단하게 호출할 수 있다.
        response.sendRedirect("/basic/hello-form.html");
    }
}
