import java.util.ArrayList;


/**
 Виталий, добрый день! Спасибо за оперативную проверку и подробные комментарии.

 Я исправил сигнатуру метода добавления подзадачи. Так как полю epicID в подзадаче уже должно быть присвоено значение
 в момент передачи в метод, я изменил конструктор подзадачи. Теперь в Мейне при создании подзадачи, сразу указываю
 ID эпика, к которому она относится.

 Хорошего дня! :)
 */

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task waterFlowers = new Task("Полить цветы", "Не забыть про кактус на кухне");
        Task washFloor = new Task("Помыть полы", "С новым средством");
        washFloor.setStatus(Status.IN_PROGRESS);
        taskManager.addTask(washFloor);
        taskManager.addTask(waterFlowers);

        Epic flatRenovation = new Epic("Сделать ремонт", "Нужно успеть за отпуск");
        Subtask flatRenovationSubtask1 = new Subtask("Поклеить обои",
                "Обязательно светлые!",
                flatRenovation.getId());
        Subtask flatRenovationSubtask2 = new Subtask("Установить новую технику",
                "Старую продать на Авито",
                flatRenovation.getId());
        taskManager.addEpic(flatRenovation);
        taskManager.addSubtask(flatRenovationSubtask1);
        taskManager.addSubtask(flatRenovationSubtask2);
        flatRenovationSubtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(flatRenovationSubtask1);

        Epic learnJava = new Epic("Пройти курс Java-разработки", "Заниматься 15 часов в неделю");
        Subtask learnJavaSubtask1 = new Subtask("Разобраться с ООП",
                "Что еще за наследование?", learnJava.getId());
        taskManager.addEpic(learnJava);
        taskManager.addSubtask(learnJavaSubtask1);
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