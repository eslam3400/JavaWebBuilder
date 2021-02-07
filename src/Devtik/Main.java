package Devtik;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Operations op = new Operations();
        System.out.println("please enter the path where u wanna safe the project!");
        String projectPath = op.get.nextLine();
        System.out.println("please enter the name of the project");
        String projectName = op.get.nextLine();
        op.createProjectDir(projectPath,projectName);
        System.out.println("please enter the number of template u wanna");
        int templateNumber = op.get.nextInt();
        op.createProjectFiles(projectPath,projectName,templateNumber);
    }
}