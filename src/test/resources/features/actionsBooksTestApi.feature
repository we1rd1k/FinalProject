
  Feature: Add And Delete Book Tests
    Scenario: Add Book Test
      Given Пользователь = "Test", Пароль = "Q!w2e3r4t5y6"
      When Отправляем Post запрос на "Account/v1/Login"
      Then Получаем userId
      Then Получаем token
      Then Очищаем коллекцию книг
      When Добавляем книгу в коллекцию с isbn = "9781449337711"
      Then Проверяем что книга с isbn = "9781449337711" успешно добавлена в коллекцию

    Scenario: Delete Book Test
      Given Пользователь = "Test", Пароль = "Q!w2e3r4t5y6"
      When Отправляем Post запрос на "Account/v1/Login"
      Then Получаем userId
      Then Получаем token
      Then Очищаем коллекцию книг
      When Добавляем книгу в коллекцию с isbn = "9781449337711"
      Then Проверяем что книга с isbn = "9781449337711" успешно добавлена в коллекцию
      When Получаем данные по книгам в Profile
      Then Получаем isbn книги для удаления
      When Отправляем Post запрос на "/BookStore/v1/Books" для удаления книги
      Then Проверяем, что книга успешно удалена
