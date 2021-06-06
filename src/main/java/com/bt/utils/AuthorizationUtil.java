package com.bt.utils;

import com.bt.bean.Admin;
import com.bt.bean.Manager;
import com.bt.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationUtil {
    HttpServletRequest request;
    HttpServletResponse response;

    HttpSession session;
    Object client;

    public AuthorizationUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Object getClientSession() {
        session = request.getSession();
        return session.getAttribute("client");
    }

    public boolean hasRole(int role) {
        session = request.getSession();
        client = session.getAttribute("client");

        Admin admin;
        Manager manager;
        User user;

        if (role == 1) {
            try {
                admin = (Admin) client;
                if (admin.getRole().equals("1"))
                    return true;
            } catch (Exception e) {
                return false;
            }
        }

        if (role == 2) {
            try {
                manager = (Manager) client;
                if (manager.getRole().equals("2"))
                    return true;
            } catch (Exception e) {
                return false;
            }
        }

        if (role == 3) {
            try {
                user = (User) client;
                if (user.getRole().equals("3"))
                    return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}
