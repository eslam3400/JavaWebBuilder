package Devtik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateWebProject {
    Model db = new Model();
    Scanner get = new Scanner(System.in);

    public String projectPath;
    public String projectName;

    public CreateWebProject(){
        try{
            createProjectDir();
            createProjectMainFiles();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void createProjectDir(){
        System.out.print("please enter the path: ");
        this.projectPath = get.next();

        System.out.print("please enter the name of the project: ");
        this.projectName = get.nextLine();

        File file = new File(this.projectPath+'\\'+this.projectName);
        if (!file.mkdir()) {
            System.out.println("Error creating the project directory !");
            System.exit(0);
        }
    }

    public void createProjectMainFiles() throws IOException {
        // Index File
        File index = new File(this.projectPath+'\\'+this.projectName+"index.html");
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


}
