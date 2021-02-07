package Devtik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Operations {
    Scanner get = new Scanner(System.in);
    public void createProjectDir(String projectPath,String projectName){
        File file = new File(projectPath+'\\'+projectName);
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Project directory created successfully");
        }else{
            System.out.println("Sorry couldnot create project directory");
        }
    }

    public void createProjectFiles(String projectPath,String projectName,int templateNumber){
        //----------------------------------------- Index File ------------------------------------------
        try {
            File index = new File(projectPath+'\\'+projectName+'\\'+"index.html");
            if (index.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter(projectPath+'\\'+projectName+'\\'+"index.html");
                    Model db = new Model();
                    String data = db.getHTMLTemplate(templateNumber);
                    data = editNav(data,projectName);
                    data = editLandingPage(data);
                    myWriter.write(data);
                    myWriter.close();
                    System.out.println("File created: " + index.getName() + " & successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //----------------------------------------- Css Folder & Files ------------------------------------------
        File cssFolder = new File(projectPath+'\\'+projectName+'\\'+"css");
        boolean bool = cssFolder.mkdir();
        if(bool){
            try {
                File css = new File(projectPath+'\\'+projectName+'\\'+"css\\styles.css");
                if (css.createNewFile()) {
                    try {
                        FileWriter myWriter = new FileWriter(projectPath+'\\'+projectName+'\\'+"css\\styles.css");
                        Model db = new Model();
                        myWriter.write(db.getCssTemplate(templateNumber));
                        myWriter.close();
                        System.out.println("Css directory created successfully & File created: " + css.getName() + "Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("File already exists.");
                }
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }else{
            System.out.println("Sorry couldn’t create css directory");
        }
        //----------------------------------------- Js Folder & Files ------------------------------------------
        File jsFolder = new File(projectPath+'\\'+projectName+'\\'+"js");
        boolean bool0 = jsFolder.mkdir();
        if(bool0){
            try {
                File js = new File(projectPath+'\\'+projectName+'\\'+"js\\scripts.js");
                if (js.createNewFile()) {
                    try {
                        FileWriter myWriter = new FileWriter(projectPath+'\\'+projectName+'\\'+"js\\scripts.js");
                        Model db = new Model();
                        myWriter.write(db.getJsTemplate(templateNumber));
                        myWriter.close();
                        System.out.println("Js directory created successfully & File created: " + js.getName() + "Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("File already exists.");
                }
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }else{
            System.out.println("Sorry couldn’t create js directory");
        }
    }

    public String editNav(String htmlData,String title){
        System.out.println("Please enter the number of nav links u gonna insert ");
        String[] navLinks = new String[get.nextInt()];
        System.out.println("please enter the nav names u wanna insert and please make sure that link is one word or multi words separated with '-'");
        for (int i = 0; i < navLinks.length; i++) navLinks[i] = get.next();
        htmlData = htmlData.replaceAll("@project_name",title);
        String liTemplate = "<li class=\"nav-item\"><a class=\"nav-link js-scroll-trigger\" href=\"pages/@link_data.html\">@link_data</a></li>";
        String finalNavLink = "";
        for (String navLink : navLinks) finalNavLink += liTemplate.replaceAll("@link_data", navLink);
        htmlData = htmlData.replaceAll("@nav_content",finalNavLink);
        return htmlData;
    }

    public String editLandingPage(String htmlData){
        String test = get.nextLine();
        System.out.println("Please enter the intro title");
        String title = get.nextLine();
        System.out.println("Please enter the intro paragraph");
        String paragraph = get.nextLine();
        System.out.println("Please enter the intro button title");
        String buttonTitle = get.nextLine();
        htmlData = htmlData.replaceAll("@landing_h",title);
        htmlData = htmlData.replaceAll("@landing_p",paragraph);
        htmlData = htmlData.replaceAll("@landing_btn_content",buttonTitle);
        return htmlData;
    }
}
