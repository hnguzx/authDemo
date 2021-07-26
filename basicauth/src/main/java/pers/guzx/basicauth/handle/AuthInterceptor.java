package pers.guzx.basicauth.handle;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.guzx.basicauth.bean.UserBean;
import pers.guzx.basicauth.util.MyConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/16 15:52
 * @describe
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、不需要登录就可以访问的路径
        String requestURI = request.getRequestURI();
        if (requestURI.contains(".") || requestURI.startsWith("/" +
                MyConstants.RESOURCE_COMMON + "/")) {
            return true;
        }
        //2、未登录用户，直接拒绝访问
        if (null ==
                request.getSession().getAttribute(MyConstants.FLAG_CURRENTUSER)) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("please login first");
            return false;
        } else {
            UserBean currentUser = (UserBean)
                    request.getSession().getAttribute(MyConstants.FLAG_CURRENTUSER);
            //3、已登录用户，判断是否有资源访问权限
            if (requestURI.startsWith("/" + MyConstants.RESOURCE_MOBILE + "/") &&
                    currentUser.havaPermission(MyConstants.RESOURCE_MOBILE)) {
                return true;
            } else if
            (requestURI.startsWith("/" + MyConstants.RESOURCE_SALARY + "/")
                            &&
                            currentUser.havaPermission(MyConstants.RESOURCE_SALARY)) {
                return true;
            } else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("no auth to visit");
                return false;
            }
        }
    }

}
