
  Feature: Login Test
    Scenario: Successful login
      Given Пользователь = "Test", Пароль = "Q!w2e3r4t5y6"
      When Отправляем Post запрос на "Account/v1/Login"
      Then Проверяем, что авторизация успешна. Статус код 200

    Scenario: Failed login
      Given Пользователь = "asd", Пароль = "asd"
      When Отправляем Post запрос на "Account/v1/GenerateToken"
      Then Проверяем, что авторизация не успешна. Статус код 200, result = "User authorization failed."