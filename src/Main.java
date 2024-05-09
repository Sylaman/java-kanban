import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        taskManager.addTask(new Task("Убраться", "Протереть пыль и помыть полы"));
        taskManager.addTask(new Task("Полить цветы", "Не забыть про кактус на кухне"));

        ArrayList<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }

    }
}