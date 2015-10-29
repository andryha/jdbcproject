# Написать консольное приложение для ведения гостевой книги #

### 1. Создать таблицу posts с полями ###

* 1. ID - число первичный ключ
* 2. postDate - дата
* 3. postMessage - текст записи в блоге

### 2. Написать контроллер класс реализующий интерфейс ###

* public interface GuestBookController{
    void addRecord(String message);
    List<Record> getRecords(); //Record {id, postDate, message}
  }

### 3. Написать консольное приложение с двумя командами add, list ###