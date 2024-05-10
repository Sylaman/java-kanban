import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task waterFlowers = new Task("Полить цветы", "Не забыть про кактус на кухне");
        Task washFloor = new Task("Помыть полы", "С новым средством");
        washFloor.setStatus(Status.IN_PROGRESS);
        taskManager.addTask(washFloor);
        taskManager.addTask(waterFlowers);

        Epic flatRenovation = new Epic("Сделать ремонт", "Нужно успеть за отпуск");
        Subtask flatRenovationSubtask1 = new Subtask("Поклеить обои", "Обязательно светлые!");
        Subtask flatRenovationSubtask2 = new Subtask("Установить новую технику", "Старую продать на Авито");
        taskManager.addEpic(flatRenovation);
        taskManager.addSubtask(flatRenovation, flatRenovationSubtask1);
        taskManager.addSubtask(flatRenovation, flatRenovationSubtask2);
        flatRenovationSubtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(flatRenovationSubtask1);

        Epic learnJava = new Epic("Пройти курс Java-разработки", "Заниматься 15 часов в неделю");
        Subtask learnJavaSubtask1 = new Subtask("Разобраться с ООП", "Что еще за наследование?");
        taskManager.addEpic(learnJava);
        taskManager.addSubtask(learnJava, learnJavaSubtask1);
        learnJavaSubtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(learnJavaSubtask1);


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

        System.out.println();
        System.out.println("Тест после удаления: ");

        taskManager.deleteTaskByID(1703979884);
        System.out.println(taskManager.getTasks());

        taskManager.deleteEpicByID(-823653190);
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
    }
}