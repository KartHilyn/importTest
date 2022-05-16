package test;

import task.TaskManager;

/**
 * 走行プログラム
 * @author 太田　翔葵
 *
 */
public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        taskManager.schedule();
        taskManager.await();
        taskManager.shutdown();
    }

}
