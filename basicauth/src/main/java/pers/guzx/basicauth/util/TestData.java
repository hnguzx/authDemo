package pers.guzx.basicauth.util;

import org.springframework.stereotype.Component;
import pers.guzx.basicauth.bean.ResourceBean;
import pers.guzx.basicauth.bean.RoleBean;
import pers.guzx.basicauth.bean.UserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestData {
    private List<UserBean> allUser;

    private List<UserBean> getAllUser() {
        if (null == allUser) {
            //资源
            ResourceBean mobileResource = new ResourceBean("1", "mobile");
            ResourceBean salaryResource = new ResourceBean("2", "salary");
            //用户组
            allUser = new ArrayList<>();
            // 角色所拥有的资源
            List<ResourceBean> adminResources = new ArrayList<>();
            adminResources.add(mobileResource);
            adminResources.add(salaryResource);

            List<ResourceBean> managerResources = new ArrayList<>();
            managerResources.add(salaryResource);
            //角色
            RoleBean adminRole = new RoleBean("1", "admin");
            adminRole.setResources(adminResources);
            RoleBean managerRole = new RoleBean("2", "manager");
            managerRole.setResources(managerResources);
            //角色组
            List<RoleBean> adminRoles = new ArrayList<>();
            adminRoles.add(adminRole);
            List<RoleBean> managerRoles = new ArrayList<>();
            managerRoles.add(managerRole);
            //用户
            UserBean user1 = new UserBean("1", "admin", "admin");
            user1.setUserRoles(adminRoles);
            user1.setResourceBeans(adminResources);
            UserBean user2 = new UserBean("2", "manager", "manager");
            user2.setUserRoles(managerRoles);
            user2.setResourceBeans(managerResources);
            UserBean user3 = new UserBean("3", "worker", "worker");
            allUser.add(user1);
            allUser.add(user2);
            allUser.add(user3);
        }
        return allUser;
    }

    public UserBean qeryUser(UserBean user) {
        List<UserBean> allUser = this.getAllUser();
        List<UserBean> userList = allUser.stream().filter(userBean -> userBean.getUserName().equals(user.getUserName())
                && userBean.getUserPass().equals(user.getUserPass())).collect(Collectors.toList());
        return userList.size() > 0 ? userList.get(0) : null;
    }
}
