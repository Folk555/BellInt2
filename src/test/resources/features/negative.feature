@NegativeTests
Feature: Негативные тесты

  Scenario: тест с неверным api
    When посылаем get запрос, с невалидным api "http://api.weatherstack.com/incorrect"
    Then получаем ответ, где http статус 200, а поле error.code 103

  Scenario: тест с несуществующим городом
    When посылаем get запрос, с несуществующим городом "Constantinople"
    Then получаем ответ, где http статус 200, а поле error.code 615

  Scenario: тест с невалидным токеном
    When посылаем get запрос, с невалидным токеном "3b63c1405e3a96a73d5dee34c"
    Then получаем ответ, где http статус 200, а поле error.code 101

  Scenario: тест с пустым параметром query
    When посылаем get запрос, с несуществующим городом ""
    Then получаем ответ, где http статус 200, а поле error.code 601