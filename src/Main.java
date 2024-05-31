import Manager.InMemoryTaskManager;
import Status.Status;
import Task.Task;
import Task.Epic;
import Task.Subtask;

public class Main {

    public static void main(String[] args) {

        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Task washFloor = new Task("Помыть полы", "С новым средством");
        Task washFloorCreated = inMemoryTaskManager.addTask(washFloor);
        System.out.println(washFloorCreated);

        Task washFloorToUpdate = new Task(washFloor.getId(), "Не забыть помыть полы", "Можно и без средства",
                Status.IN_PROGRESS);
        Task washFloorUpdated = inMemoryTaskManager.updateTask(washFloorToUpdate);
        System.out.println(washFloorUpdated);


        Epic flatRenovation = new Epic("Сделать ремонт", "Нужно успеть за отпуск");
        inMemoryTaskManager.addEpic(flatRenovation);
        System.out.println(flatRenovation);
        Subtask flatRenovationSubtask1 = new Subtask("Поклеить обои", "Обязательно светлые!",
                flatRenovation.getId());
        Subtask flatRenovationSubtask2 = new Subtask("Установить новую технику", "Старую продать на Авито",
                flatRenovation.getId());
        inMemoryTaskManager.addSubtask(flatRenovationSubtask1);
        inMemoryTaskManager.addSubtask(flatRenovationSubtask2);
        System.out.println(flatRenovation);
        flatRenovationSubtask2.setStatus(Status.DONE);
        inMemoryTaskManager.updateSubtask(flatRenovationSubtask2);
        System.out.println(flatRenovation);
    }
}