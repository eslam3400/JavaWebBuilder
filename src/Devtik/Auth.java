package Devtik;

import java.util.Scanner;

public class Auth {

    public String username;

    public Auth(){
        getUser();
    }

    public void getUser(){
        Scanner get = new Scanner(System.in);

        System.out.print("please enter your username: ");
        String username = get.next();

        System.out.print("please enter your password: ");
        String password = get.next();

        Model db = new Model();
        this.username = db.auth(username,password);

        if (this.username == null) {
            System.out.println("\nusername or password are wrong please check your data and try again..");
            getUser();
        } else System.out.println("welcome back " + this.username + " ♥");
    }
}