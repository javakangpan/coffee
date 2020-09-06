package com.liting.tomcat;

public abstract class Myservlet {
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);
    public void service(MyRequest myRequest, MyResponse myResponse) {
    }
}
