import manager.Managers;
import manager.TaskManager;
import status.Status;
import task.Task;
import task.Epic;
import task.Subtask;

/**
 * Виталий, добрый день!
 * <p>
 * При написании тестов мне пришлось пропустить 2 пункта из списка тестов в ТЗ:
 * <p>
 * - проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;
 * <p>
 * - проверьте, что объект Subtask нельзя сделать своим же эпиком;
 * <p>
 * Я не могу проверить данные сценарии, так как если я попытаюсь так сделать, у меня просто не скомпилируется код, запустить тесты не получится.
 * <p>
 * Наставник моей когорты сказал, что это из-за того что я храню в эпики объекты подзадач.
 * Он предложил пропустить эти 2 сценария, подождать что скажете вы.
 * <p>
 * Можно ли считать, что раз код не компилируется, то эти пункты уже "протестированы", так в работе программы такой ошибки не возникнет?
 **/

public class Main {

    private static final TaskManager inMemoryTaskManager = Managers.getDefault();

    public static void main(String[] args) {

        addTasks();
        printAllTasks();
        printViewHistory();
    }

    private static void addTasks() {
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
    }

    private static void printAllTasks() {
        System.out.println("Задачи:");
        for (Task task : Main.inMemoryTaskManager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : Main.inMemoryTaskManager.getEpics()) {
            System.out.println(epic);

            for (Task task : Main.inMemoryTaskManager.getEpicSubtasks(epic)) {
                System.out.println("--> " + task);
            }
        }

        System.out.println("Подзадачи:");
        for (Task subtask : Main.inMemoryTaskManager.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    private static void printViewHistory() {
        //просматриваем 11 задач, в истории должны отобразиться последние 10
        Main.inMemoryTaskManager.getTaskByID(1);
        Main.inMemoryTaskManager.getTaskByID(2);
        Main.inMemoryTaskManager.getEpicByID(3);
        Main.inMemoryTaskManager.getTaskByID(1);
        Main.inMemoryTaskManager.getSubtaskByID(4);
        Main.inMemoryTaskManager.getSubtaskByID(5);
        Main.inMemoryTaskManager.getSubtaskByID(6);
        Main.inMemoryTaskManager.getEpicByID(3);
        Main.inMemoryTaskManager.getSubtaskByID(4);
        Main.inMemoryTaskManager.getTaskByID(2);
        Main.inMemoryTaskManager.getSubtaskByID(6);

        System.out.println();
        System.out.println("История просмотров:");
        for (Task task : Main.inMemoryTaskManager.getHistory()) {
            System.out.println(task);
        }
    }
}