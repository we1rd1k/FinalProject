
  Feature: Login Test
    Scenario: Successful login
      Given Открываем страницу сайта
      When Переходим к приложению Book Store Application
      Then Проверяем, что находимся на странице магазина книг
      When Переходим на страницу авторизации
      Then Проверяем, что находимся на странице авторизации
      And Авторизуемся в систему с userName "Test", password "Q!w2e3r4t5y6", credentials = true

      Scenario: Failed login
        Given Открываем страницу сайта
        When Переходим к приложению Book Store Application
        Then Проверяем, что находимся на странице магазина книг
        When Переходим на страницу авторизации
        Then Проверяем, что находимся на странице авторизации
        And Авторизуемся в систему с userName "asd", password "asd", credentials = false
        And Проверяем наличие сообщения об ошибке: Invalid username or password!