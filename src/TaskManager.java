import java.lang.reflect.Array;
import java.util.*;

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
            subtask.setEpicID(epic.getId());
            epic.addSubtask(subtask);
            subtasks.put(subtask.getId(), subtask);
        }
    }

    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            System.out.println("Ошибка обновления: задача не найдена!");
        } else {
            tasks.replace(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) {
            System.out.println("Ошибка обновления: эпик не найден!");
        } else {
            // если у эпика были подзадачи, удаляем их из мапы с подзадачами
            Epic oldEpic = epics.get(epic.getId());
            ArrayList<Subtask> oldEpicSubtaskList = oldEpic.getSubtaskList();
            for (Subtask subtask : oldEpicSubtaskList) {
                subtasks.remove(subtask.getId());
            }
            epics.replace(epic.getId(), epic);
            // если у обновленного эпика есть подзадачи, добавляем их в мапу подзадач
            ArrayList<Subtask> newEpicSubtaskList = epic.getSubtaskList();
            for (Subtask subtask : newEpicSubtaskList) {
                subtasks.put(subtask.getId(), subtask);
            }
            // обновляем статус эпика
            updateEpicStatus(epic);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId())) {
            System.out.println("Ошибка обновления: подзачада не найдена!");
        } else {
            int epicID = subtask.getEpicID();
            Subtask oldSubtask = subtasks.get(subtask.getId());
            subtasks.replace(subtask.getId(), subtask);
            // обновляем подзадачу в списке подзадач эпика и проверяем статус эпика
            Epic epic = epics.get(epicID);
            ArrayList<Subtask> subtaskList = epic.getSubtaskList();
            subtaskList.remove(oldSubtask);
            subtaskList.add(subtask);
            epic.setSubtaskList(subtaskList);
            updateEpicStatus(epic);
        }
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByID(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByID(int id) {
        return subtasks.get(id);
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

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        return epic.getSubtaskList();
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            epic.setStatus(Status.NEW);
        }
    }

    public void deleteTaskByID(int id) {
        tasks.remove(id);
    }

    public void deleteEpicByID(int id) {
        ArrayList<Subtask> epicSubtasks = epics.get(id).getSubtaskList();
        epics.remove(id);
        for (Subtask subtask : epicSubtasks) {
            subtasks.remove(subtask.getId());
        }
    }

    public void deleteSubtaskByID(int id) {
        Subtask subtask = subtasks.get(id);
        int epicID = subtask.getEpicID();
        subtasks.remove(id);
        // обновляем список подзадач и статус эпика
        Epic epic = epics.get(epicID);
        ArrayList<Subtask> subtaskList = epic.getSubtaskList();
        subtaskList.remove(subtask);
        epic.setSubtaskList(subtaskList);
        updateEpicStatus(epic);
    }

    // вспомогательный private метод для контроля статуса эпика при удалении или изменении подзадач
    private void updateEpicStatus(Epic epic) {
        ArrayList<Subtask> subtaskList = epic.getSubtaskList();
        if (subtaskList.isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            int count = 0;
            for (Subtask subtask : subtaskList) {
                if (subtask.getStatus().equals(Status.DONE)) {
                    count++;
                } else if (subtask.getStatus().equals(Status.IN_PROGRESS)) {
                    epic.setStatus(Status.IN_PROGRESS);
                    break;
                }
            }
            if (count == subtaskList.size()) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }
}