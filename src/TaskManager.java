import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    public void addTask(Task task) {
        if (tasks.containsValue(task)) {
            System.out.println("Такая задача уже существует!");
        } else {
            tasks.put(task.getId(), task);
        }
    }

    public void addEpic(Epic epic) {
        if (epics.containsValue(epic)) {
            System.out.println("Такой эпик уже существует!");
        } else {
            epics.put(epic.getId(), epic);
        }
    }

    public void addSubtask(Epic epic, Subtask subtask) {
        ArrayList<Subtask> subtaskList = epic.getSubtaskList();
        if (subtaskList.contains(subtask)) {
            System.out.println("В указанном эпике уже есть такая подзадача!");
        } else {
            epic.addSubtask(subtask);
            subtasks.put(subtask.getId(), subtask);
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
    }

    public void deleteSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
        }
    }
}