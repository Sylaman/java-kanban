import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        taskManager.addTask(new Task("Убраться", "Протереть пыль и помыть полы"));
        taskManager.addTask(new Task("Полить цветы", "Не забыть про кактус на кухне"));

        Epic flatRenovation = new Epic("Сделать ремонт", "Нужно успеть за отпуск");
        Subtask flatRenovationSubtask1 = new Subtask("Поклеить обои", "Обязательно светлые!");

        taskManager.addEpic(flatRenovation);
        taskManager.addSubtask(flatRenovation, flatRenovationSubtask1);


        ArrayList<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }

        ArrayList<Epic> epics = taskManager.getEpics();
        for (Epic epic : epics) {
            System.out.println(epic);
        }

        ArrayList<Subtask> subtasks = taskManager.getSubtasks();
        for (Subtask subtask : subtasks) {
            System.out.println(subtask);
        }

        taskManager.deleteTasks();
        taskManager.deleteEpics();
        taskManager.deleteSubtasks();

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

    }
}