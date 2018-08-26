package kr.theolddragon.springBootGettingStarted;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Application {

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);

    File docBase = new File("c:\\Project\\Study");
    System.out.println(docBase.getPath());

    Context context = tomcat.addContext("", docBase.getPath());

    HttpServlet servlet = new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>");
        writer.println("Hey, Tomcat");
        writer.println("</title></head>");
        writer.println("<body><h1>Hello Tomcat</h1></body>");
        writer.println("</html>");
      }
    };

    String servletName = "helloServlet";
    tomcat.addServlet("", servletName, servlet);
    context.addServletMappingDecoded("/hello", servletName);

    tomcat.start();
    tomcat.getServer().await();
  }
}
