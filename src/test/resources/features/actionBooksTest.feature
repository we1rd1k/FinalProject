
  Feature: Action with Books Test
    Scenario: Add book to profile
      Given Открываем страницу сайта
      When Переходим к приложению Book Store Application
      Then Проверяем, что находимся на странице магазина книг
      When Переходим на страницу авторизации
      Then Проверяем, что находимся на странице авторизации
      And Авторизуемся в систему с userName "Test", password "Q!w2e3r4t5y6", credentials = true
      When Переходим на страницу Book Store
      Then Открываем страницу с детальной информацией о книге
      And Получаем заглавие книги
      And Добавляем книгу в коллекцию
      Then Переходим на страницу Profile
      And Проверяем, что находимся на странице профиля
      And Проверяем, что книга находится в коллекции

    Scenario: Delete book
      Given Открываем страницу сайта
      When Переходим к приложению Book Store Application
      Then Проверяем, что находимся на странице магазина книг
      When Переходим на страницу авторизации
      Then Проверяем, что находимся на странице авторизации
      And Авторизуемся в систему с userName "Test", password "Q!w2e3r4t5y6", credentials = true
      When Переходим на страницу Book Store
      Then Открываем страницу с детальной информацией о книге
      And Получаем заглавие книги
      And Добавляем книгу в коллекцию
      Then Переходим на страницу Profile
      And Проверяем, что находимся на странице профиля
      And Проверяем, что книга находится в коллекции
      And Удаляем книгу из коллекции
      Then Проверяем, что книга отсутсвует в списке
