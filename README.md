# Техническое задание проекта №4

Как человек обычно делает покупки? Если ему нужен не один продукт, а несколько, то очень вероятно, что сначала он составит список, чтобы ничего не забыть. Сделать это можно где угодно: на листе бумаги, в приложении для заметок или, например, в сообщении самому себе в мессенджере.   

А теперь представьте, что это список не продуктов, а полноценных дел. И не каких-нибудь простых вроде «помыть посуду» или «позвонить бабушке», а сложных — например, «организовать большой семейный праздник» или «купить квартиру». Каждая из таких задач может разбиваться на несколько этапов со своими нюансами и сроками. А если над их выполнением будет работать не один человек, а целая команда, то организация процесса станет ещё сложнее.  

### Трекер задач
Как системы контроля версий помогают команде работать с общим кодом, так и трекеры задач позволяют эффективно организовать совместную работу над задачами. Вам предстоит написать бэкенд для такого трекера. В итоге должна получиться программа, отвечающая за формирование модели данных для этой страницы:  
![1](https://github.com/Sylaman/Yandex-Practicum-Sprint-4/assets/109538896/34bb8b4d-dede-41a3-8c7f-e7af8c045253)

**💡Пользователь не будет видеть консоль вашего приложения. Поэтому нужно сделать так, чтобы методы не просто печатали что-то в консоль, но и возвращали объекты нужных типов.
Вы можете добавить консольный вывод для самопроверки в класcе `Main`, но на работу методов он влиять не должен.**

### Типы задач  

Простейший кирпичик трекера — **задача** (англ. task). У неё есть следующие свойства:  
1. **Название**, кратко описывающее суть задачи (например, «Переезд»).  
2. **Описание**, в котором раскрываются детали.  
3. **Уникальный идентификационный номер задачи**, по которому её можно будет найти.  
4. **Статус**, отображающий её прогресс. Вы будете выделять следующие этапы жизни задачи, используя `enum`:  
   - `NEW` — задача только создана, но к её выполнению ещё не приступили.  
   - `IN_PROGRESS` — над задачей ведётся работа.  
   - `DONE` — задача выполнена.  

Иногда для выполнения какой-нибудь масштабной задачи её лучше разбить на **подзадачи** (англ. subtask). Большая задача, которая делится на подзадачи, называется **эпиком** (англ. epic).   

Подытожим. В системе задачи могут быть трёх типов: обычные задачи, эпики и подзадачи. Для них должны выполняться следующие условия:  
* Для каждой подзадачи известно, в рамках какого эпика она выполняется.  
* Каждый эпик знает, какие подзадачи в него входят.  
* Завершение всех подзадач эпика считается завершением эпика.  

### Идентификатор задачи  
В трекере у каждого типа задач есть идентификатор. Это целое число, уникальное для всех типов задач. По нему находят, обновляют, удаляют задачи. При создании задачи менеджер присваивает ей новый идентификатор.  

Также советуем применить знания о методах `equals()` и `hashCode()`, чтобы реализовать идентификацию задачи по её `id`.  При этом две задачи с одинаковым `id` должны выглядеть для менеджера как одна и та же.  

**💡 Эти методы нежелательно переопределять в наследниках. Ваша задача — подумать, почему.**  

### Менеджер  

Кроме классов для описания задач, вам нужно реализовать класс для объекта-менеджера. Он будет запускаться на старте программы и управлять всеми задачами. В нём должны быть реализованы следующие функции:  
1. Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.  
2. Методы для каждого из типа задач(Задача/Эпик/Подзадача):  
    - Получение списка всех задач.  
    - Удаление всех задач.  
    - Получение по идентификатору.  
    - Создание. Сам объект должен передаваться в качестве параметра.  
    - Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.  
    - Удаление по идентификатору.
3. Дополнительные методы:  
    - Получение списка всех подзадач определённого эпика.  
4. Управление статусами осуществляется по следующему правилу:  
    - Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.  
    - Для эпиков:  
      * если у эпика нет подзадач или все они имеют статус `NEW`, то статус должен быть `NEW`.  
      * если все подзадачи имеют статус `DONE`, то и эпик считается завершённым — со статусом `DONE`.  
      * во всех остальных случаях статус должен быть `IN_PROGRESS`.  

### И ещё кое-что...  
- Проверка кода называется тестированием. Подробно вы изучите эту тему дальше в курсе. Тем не менее, сам процесс тестирования можно начать уже сейчас. Создайте в классе `Main` метод `static void main(String[] args)` и внутри него:  
  - Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.  
  - Распечатайте списки эпиков, задач и подзадач через `System.out.println(..)`.  
  - Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.  
  - И, наконец, попробуйте удалить одну из задач и один из эпиков.  
  - Воспользуйтесь дебаггером среды разработки, чтобы понять логику работы программы и отладить её.  
- Не оставляйте в коде мусор — превращённые в комментарии или ненужные куски кода. Это сквозной проект, на его основе вы будете делать **несколько следующих домашних заданий.**  
- Давайте коммитам осмысленные комментарии: порядок в репозитории и коде — ключ к успеху написания хороших программ.  

### Перед тем как приступить к задаче ответьте себе на вопросы:
* Как распределяется функциональность приложения между классами?  
* Как классы должны взаимодействовать друг с другом?  
* Как применить принципы ООП и построить систему наследования?  
* Какой способ выбрать для хранения задач?  
* Должен ли быть у приложения интерфейс?  

**Интересного вам программирования!**