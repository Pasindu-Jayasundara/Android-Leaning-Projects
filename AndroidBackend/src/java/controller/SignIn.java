package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    static ArrayList<User> userList = new ArrayList<>();
    
    static{
        userList.add(new User(1,"Sahan","0740211671","123","Colombo"));
        userList.add(new User(2,"Kasun","0717900130","456","Gampaha"));
        userList.add(new User(3,"Nimal","0713309367","789","Colombo"));
        userList.add(new User(4,"Gayan","0779177299","234","Kandy"));
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        User fromJson = gson.fromJson(request.getReader(), User.class);
        
        boolean isSuccess = false;
        
        for (User user : userList) {
            if(user.getMobile().equals(fromJson.getMobile()) && user.getPassword().equals(fromJson.getPassword())){
                
                isSuccess = true;
                break;
            }
        }
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(isSuccess));

    }

}
