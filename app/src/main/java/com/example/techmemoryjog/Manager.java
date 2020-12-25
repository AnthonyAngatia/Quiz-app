package com.example.techmemoryjog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Manages the flow of data
//Follows a singleton design pattern
public class Manager {
    private static Manager ourInstance = null;
    private List<Course> mCourses = new ArrayList<>();

    public List<Course> getmCourses() {
        return mCourses;
    }

    public List<Question> getQuestions(int id){
        return ourInstance.getCourse(id).getmQuestions();
    }
    public Course getCourse(int id){
        return ourInstance.getmCourses().get(id);
    }

    public static Manager getInstance(){
        if(ourInstance == null) {
            ourInstance = new Manager();
            ourInstance.initializeCourses();//Initialized courses and questions
        }
        return ourInstance;
    }

    private void initializeCourses(){
        //get a list of all courses
        mCourses.add(initializeCourse1());
        mCourses.add(initializeCourse2());
        mCourses.add(initializeCourse3());
        mCourses.add(initializeCourse4());
    }


    private Course initializeCourse1() {
        return new Course("Angular", 1, initializeQuestionsCourse1());

    }
    private Course initializeCourse2() {
        return new Course("Android", 2, initializeQuestionsCourse2());
    }
    private Course initializeCourse3() {
        return new Course("Typescript", 3, initializeQuestionsCourse3());

    }
    private Course initializeCourse4() {
        return new Course("Java", 4, initializeQuestionsCourse4());

    }


    private List<Question> initializeQuestionsCourse1(){
        HashMap<String, String> choice1  = new HashMap<>();
        choice1.put("@Event", "false");
        choice1.put("@EventEmitter", "false");
        choice1.put("@Output", "true");
        choice1.put("@Raise", "false");
        List<Question> questions = new ArrayList<>();
        String topic = "Topic 2";
        int time = 50;
        questions.add(new Question("Which decorator lets a child component expose an event to a parent component?", choice1,"Component Interaction", 10, time));
        HashMap<String, String> choice2  = new HashMap<>();
        choice2.put("Format", "false");
        choice2.put("Formatter", "false");
        choice2.put("@PipeTransform", "true");
        choice2.put("Pipe", "false");

        questions.add(new Question("Which interface is used when creating a custom pipe?", choice2,"Angular Pipes", 10, time));
        HashMap<String, String> choice3  = new HashMap<>();
        choice3.put("ng new my-app --routing", "true");
        choice3.put("ng new my-app --module", "false");
        choice3.put("ng generate my-app ", "false");
        choice3.put("ng generate my-app -- routing", "false");
        questions.add(new Question("What command will create a new Angular app with a root routing module?", choice3,"Angular Basics", 10, time));
        HashMap<String, String> choice4  = new HashMap<>();
        choice4.put("In the component class", "false");
        choice4.put("In the template", "true");
        choice4.put("In the component metadata", "false");
        choice4.put("In a service", "false");
        questions.add(new Question("Where would the following code likely be found in an Angular application?\n" +
                "\n" +
                "routerLink=\"/crisis-center\" ", choice4,"Angular Routing", 15, time));
        HashMap<String, String> choice5  = new HashMap<>();
        choice5.put("A property that is set in code to navigate to a specified route", "false");
        choice5.put("A route guard that protects the activated route", "false");
        choice5.put("A service that provides information about the active route path and parameters", "true");
        choice5.put("A directive used to navigate to a specified route", "false");
        questions.add(new Question("What is activated route", choice5, "Angular routing", 20, time));

        return questions;

    }
    private List<Question> initializeQuestionsCourse2() {
        HashMap<String, String> choice1  = new HashMap<>();
        choice1.put("Target SDK", "false");
        choice1.put("Minimum SDK", "true");
        choice1.put("Maximum SDK", "false");
        choice1.put("Compiler SDK", "false");

        List<Question> questions = new ArrayList<>();
        int time = 50;
        String topic = "Topic 1";
        questions.add(new Question("When creating a new phone and tablet Android project using the Android Create New Project wizard, which of the following does the wizard ask you to specify? ", choice1, "Getting Started Android Studio", 15, time));
        HashMap<String, String> choice2  = new HashMap<>();
        choice2.put("The Activity is destroyed and recreated.", "true");
        choice2.put("The Activity launches a background service to update the UI layout.", "false");
        choice2.put("The Activity's onConfigurationChanged method is called.", "false");
        choice2.put("The Activity is unchanged.", "false");
        questions.add(new Question("What is the default behavior of an Activity when the device is rotated between portrait and landscape orientations?", choice2,"Activity Lifecycle", 10, time));
        HashMap<String, String> choice3  = new HashMap<>();
        choice3.put("As an annotation on the Activity", "false");
        choice3.put("Activity's style resource", "false");
        choice3.put("Android Studio project setting", "false");
        choice3.put("Application manifest", "true");
        questions.add(new Question("In which of the following is an Activity's theme specified?", choice3, "Android File Structure & Layouts", 10, time));
        HashMap<String, String> choice4  = new HashMap<>();
        choice4.put("logcat", "false");
        choice4.put("adb", "true");
        choice4.put("ddms", "false");
        choice4.put("aapt", "false");
        questions.add(new Question(" Which Android SDK tool provides the capability to install applications, view logs, and copy files from an Android device?", choice4, "Advanced Android", 10, time));

        return questions;
    }
    private List<Question> initializeQuestionsCourse3(){
        HashMap<String, String> choice1  = new HashMap<>();
        choice1.put("@Event", "false");
        choice1.put("@EventEmitter", "false");
        choice1.put("@Output", "true");
        choice1.put("@Raise", "false");
        List<Question> questions = new ArrayList<>();
        String topic = "Topic 2";
        int time = 50;
        questions.add(new Question("Which decorator lets a child component expose an event to a parent component?", choice1,"Component Interaction", 10, time));
        HashMap<String, String> choice2  = new HashMap<>();
        choice2.put("Format", "false");
        choice2.put("Formatter", "false");
        choice2.put("@PipeTransform", "true");
        choice2.put("Pipe", "false");

        questions.add(new Question("Which interface is used when creating a custom pipe?", choice2,"Angular Pipes", 10, time));
        HashMap<String, String> choice3  = new HashMap<>();
        choice3.put("ng new my-app --routing", "true");
        choice3.put("ng new my-app --module", "false");
        choice3.put("ng generate my-app ", "false");
        choice3.put("ng generate my-app -- routing", "false");
        questions.add(new Question("What command will create a new Angular app with a root routing module?", choice3,"Angular Basics", 10, time));
        HashMap<String, String> choice4  = new HashMap<>();
        choice4.put("In the component class", "false");
        choice4.put("In the template", "true");
        choice4.put("In the component metadata", "false");
        choice4.put("In a service", "false");
        questions.add(new Question("Where would the following code likely be found in an Angular application?\n" +
                "\n" +
                "routerLink=\"/crisis-center\" ", choice4,"Angular Routing", 15, time));
        HashMap<String, String> choice5  = new HashMap<>();
        choice5.put("A property that is set in code to navigate to a specified route", "false");
        choice5.put("A route guard that protects the activated route", "false");
        choice5.put("A service that provides information about the active route path and parameters", "true");
        choice5.put("A directive used to navigate to a specified route", "false");
        questions.add(new Question("What is activated route", choice5, "Angular routing", 20, time));

        return questions;

    }
    private List<Question> initializeQuestionsCourse4() {
        HashMap<String, String> choice1  = new HashMap<>();
        choice1.put("Target SDK", "false");
        choice1.put("Minimum SDK", "true");
        choice1.put("Maximum SDK", "false");
        choice1.put("Compiler SDK", "false");

        List<Question> questions = new ArrayList<>();
        int time = 50;
        String topic = "Topic 1";
        questions.add(new Question("When creating a new phone and tablet Android project using the Android Create New Project wizard, which of the following does the wizard ask you to specify? ", choice1, "Getting Started Android Studio", 15, time));
        HashMap<String, String> choice2  = new HashMap<>();
        choice2.put("The Activity is destroyed and recreated.", "true");
        choice2.put("The Activity launches a background service to update the UI layout.", "false");
        choice2.put("The Activity's onConfigurationChanged method is called.", "false");
        choice2.put("The Activity is unchanged.", "false");
        questions.add(new Question("What is the default behavior of an Activity when the device is rotated between portrait and landscape orientations?", choice2,"Activity Lifecycle", 10, time));
        HashMap<String, String> choice3  = new HashMap<>();
        choice3.put("As an annotation on the Activity", "false");
        choice3.put("Activity's style resource", "false");
        choice3.put("Android Studio project setting", "false");
        choice3.put("Application manifest", "true");
        questions.add(new Question("In which of the following is an Activity's theme specified?", choice3, "Android File Structure & Layouts", 10, time));
        HashMap<String, String> choice4  = new HashMap<>();
        choice4.put("logcat", "false");
        choice4.put("adb", "true");
        choice4.put("ddms", "false");
        choice4.put("aapt", "false");
        questions.add(new Question(" Which Android SDK tool provides the capability to install applications, view logs, and copy files from an Android device?", choice4, "Advanced Android", 10, time));

        return questions;
    }





}
