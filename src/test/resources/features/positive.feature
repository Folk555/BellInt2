@PositiveTests
Feature: Позитивные тесты

  Scenario Outline: Запрос информации по городам
    When посылаем get запрос, в котором указываем "<city>"
    Then получаем ответ, где температура города между "-1" и "40" градусами
    And "<country>" соответствует ожидаемой стране города
    And скорость ветра соответствует случайно генерируемому числу
    Examples:
      | city             |country|
      | Saint Petersburg |Russia |
      | Paris            |France |
      | Washington       |USA    |
      | New Delhi        |India  |