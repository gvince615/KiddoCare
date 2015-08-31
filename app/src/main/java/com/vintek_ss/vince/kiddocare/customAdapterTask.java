package com.vintek_ss.vince.kiddocare;

/**
 * Created by VINCE-LTPC on 12/6/2014.
 */
public class customAdapterTask {
    private String taskTitle;
    private String taskTime;
    private String taskDate;
    private String taskStatus;


    public customAdapterTask(String taskTitle, String taskTime, String taskStatus, String taskDate) {
        super();
        this.taskTitle = taskTitle;
        this.taskTime = taskTime;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
    }

    public String gettaskTitle() {
        return taskTitle;
    }


    public String gettaskTime() {
        return taskTime;
    }

    public String gettaskStatus() {
        return taskStatus;
    }

    public String gettaskDate() {
        return taskDate;
    }
}
