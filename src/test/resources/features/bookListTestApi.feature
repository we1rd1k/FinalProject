

  Feature: Book List Test
    Scenario: Get Books List
      When Получаем список книг отправляя Get запрос на "/BookStore/v1/Books"
      Then Проверяем что список книг соответствует json схеме, список не пустой