package Devtik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WebProjectBuilder {
    Model db = new Model();
    Scanner get = new Scanner(System.in);

    public String projectPath;
    public String projectName;

    public WebProjectBuilder(){
        try{
            this.createProjectDir();
            this.createProjectMainFiles();
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void createProjectDir(){
        System.out.print("please enter the path: ");
        this.projectPath = this.get.next();

        System.out.print("please enter the name of the project: ");
        this.projectName = this.get.next();

        File file = new File(this.projectPath+'\\'+this.projectName);
        if (!file.mkdir()) {
            System.out.println("Error creating the project directory !");
            System.exit(0);
        }
    }

    public void createProjectMainFiles() throws IOException {
        // Index File
        File index = new File(this.projectPath+'\\'+this.projectName+"\\index.html");
        if (!index.createNewFile()){
            System.out.println("Error making the index.html");
            System.exit(0);
        } else {
            FileWriter writer = new FileWriter(this.projectPath+'\\'+this.projectName+'\\'+"index.html");
            writer.write(this.db.getContainerStart().replaceFirst("@ez_project_name",this.projectName));
            writer.close();
        }

        // Css Dir And Files
        File cssFolder = new File(this.projectPath+'\\'+this.projectName+"\\css");
        if (!cssFolder.mkdir()){
            System.out.println("Error creating css folder");
            System.exit(0);
        } else {
            File cssFile = new File(this.projectPath+'\\'+this.projectName+"\\css\\style.css");
            if (!cssFile.createNewFile()){
                System.out.println("Error creating style.css file");
                System.exit(0);
            }
        }

        // Js Dir And Files
        File jsFolder = new File(this.projectPath+'\\'+this.projectName+"\\js");
        if (!jsFolder.mkdir()){
            System.out.println("Error creating js folder");
            System.exit(0);
        } else {
            File jsFile = new File(this.projectPath+'\\'+this.projectName+"\\js\\index.js");
            if (!jsFile.createNewFile()){
                System.out.println("Error creating index.js file");
                System.exit(0);
            }
        }
    }

    public void addComponent(int _id) throws IOException {
        String[] component = this.db.getComponentData(_id);
        if (component[0].equals("navbar")){
            System.out.print("please enter the number of the nav links: ");
            String[] navLinks = new String[this.get.nextInt()];
            System.out.println("please enter the nav names u wanna insert and please make sure that link is one line separated with '-'");
            for (int i = 0; i < navLinks.length; i++) navLinks[i] = this.get.next();
            String finalNavLinks = "";
            for (String navLink : navLinks) finalNavLinks += component[4].replaceAll("@ez_nav_link", navLink)+"\n";
            FileWriter htmlFile = new FileWriter(this.projectPath+'\\'+this.projectName+"\\index.html",true);
            htmlFile.write(component[1].replaceAll("@ez_nav_title",this.projectName).replaceAll("@ez_nav_links",finalNavLinks));
            htmlFile.close();
            FileWriter cssFile = new FileWriter(this.projectPath+'\\'+this.projectName+"\\css\\style.css",true);
            cssFile.write(component[2]);
            cssFile.close();
            FileWriter jsFile = new FileWriter(this.projectPath+'\\'+this.projectName+"\\js\\index.js",true);
            jsFile.write(component[3]);
            jsFile.close();
        }
    }

    // A method that keep asking the user to add more components to the document
    public void addComponents() {
        while (true){
            System.out.print("please enter the id of the component that you wanna add: ");
            int componentID = this.get.nextInt();
            if (componentID == 0) {
                try{
                    this.endWebProjectBuilder();
                } catch (IOException e) {
                    System.out.println("Couldn't add the container end");
                }
                break;
            }
            else try {
                this.addComponent(componentID);
            } catch (IOException e) {
                System.out.println("an error happened while adding component!"+e.getMessage());
                System.exit(0);
            }
        }
    }

    public void endWebProjectBuilder() throws IOException {
        FileWriter htmlFile = new FileWriter(this.projectPath+'\\'+this.projectName+"\\index.html",true);
        htmlFile.write(this.db.getContainerEnd());
        htmlFile.close();
    }
}
