import manager.InMemoryTaskManager;
import manager.TaskManager;
import status.Status;
import task.Task;
import task.Epic;
import task.Subtask;

public class Main {

    public static void main(String[] args) {

        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task washFloor = new Task("Помыть полы", "С новым средством");
        inMemoryTaskManager.addTask(washFloor);

        Task washFloorToUpdate = new Task(washFloor.getId(), "Не забыть помыть полы",
                "Можно и без средства", Status.IN_PROGRESS);
        inMemoryTaskManager.updateTask(washFloorToUpdate);
        inMemoryTaskManager.addTask(new Task("Купить книги", "Список в заметках"));


        Epic flatRenovation = new Epic("Сделать ремонт", "Нужно успеть за отпуск");
        inMemoryTaskManager.addEpic(flatRenovation);
        Subtask flatRenovationSubtask1 = new Subtask("Поклеить обои", "Обязательно светлые!",
                flatRenovation.getId());
        Subtask flatRenovationSubtask2 = new Subtask("Установить новую технику", "Старую продать на Авито",
                flatRenovation.getId());
        Subtask flatRenovationSubtask3 = new Subtask("Заказать книжный шкаф", "Из темного дерева",
                flatRenovation.getId());
        inMemoryTaskManager.addSubtask(flatRenovationSubtask1);
        inMemoryTaskManager.addSubtask(flatRenovationSubtask2);
        inMemoryTaskManager.addSubtask(flatRenovationSubtask3);
        flatRenovationSubtask2.setStatus(Status.DONE);
        inMemoryTaskManager.updateSubtask(flatRenovationSubtask2);

        printAllTasks(inMemoryTaskManager);
        printViewHistory(inMemoryTaskManager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasks(epic)) {
                System.out.println("--> " + task);
            }
        }

        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    private static void printViewHistory(TaskManager manager) {
        //просматриваем 11 задач, в истории должны отобразиться последние 10
        manager.getTaskByID(1);
        manager.getTaskByID(2);
        manager.getEpicByID(3);
        manager.getTaskByID(1);
        manager.getSubtaskByID(4);
        manager.getSubtaskByID(5);
        manager.getSubtaskByID(6);
        manager.getEpicByID(3);
        manager.getSubtaskByID(4);
        manager.getTaskByID(2);
        manager.getSubtaskByID(6);

        System.out.println();
        System.out.println("История просмотров:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}