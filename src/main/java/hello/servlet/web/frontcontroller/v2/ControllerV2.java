package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    // 기존에는 void 였고 controller 가 forward 로 이동했는데,
    // 지금은 MyView 로 분리해서 넘긴다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
