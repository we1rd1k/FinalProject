#english

  Feature: bookList Test
    Scenario: Check existence of books list
      Given Открываем страницу сайта
      When Переходим к приложению Book Store Application
      Then Проверяем, что находимся на странице магазина книг
      And Проверяем, что количество книг в списке > 0