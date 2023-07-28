# Тестовое задание Bell Integrator
Cucumber(под копотом Junit5) + Junit5 + Allure.  
По ТЗ было очень мало указаний как конкретно это сделать, а вариантов было очень много и сам API условно не маленький. 
Так как я ограничен по времени, местами написал костыли.
Из ответа API сравниваются поля current.wind_speed, current.temperature
location.country.

## Запуск
Запуск тестов производится через gradle task "cucumber", он же генерирует Allure-results.
А вот просмотреть отчет allure не так просто, придется установить консольную allure и 
запускать из терминала, установив перед этим.
allure serve
Либо можно перенести папку allure-results с отчетами в папку сборки (build/target/out), и дальше
запускать allure через соответствующие gradle таски.

## Задание
Зарегистрироваться на сервисе передачи данных о погоде - https://weatherstack.com/. Бесплатный аккаунт 1000 вызовов в месяц. Поэтому будьте внимательней - должны остаться варианты для запуска во время проверки выполнения задания. Документация по API – https://weatherstack.com/documentation.
Используя любой BDD фреймворк реализовать:
Позитивный тест:
1. Запросить текущую погоду минимум по четырем городам на свой выбор.
2. Распарсить результат, сравнить с ожидаемыми значениями из тестового набора (language, location и т.п.). Ожидаемые тестовые данные можно определить или задать для каждого города корректные (localtime, utc_offset и т.п.), либо можно задать\сгенерировать случайным образом с соблюдением формата (wind_speed, temperature и пр.).
3. Вывести расхождения по результату сравнения по каждому значению в лог.

Негативный тест:
Получить 4 варианта ошибок из списка API Errors (на выбор), сравнить с ожидаемым результатом.
Результат выполнения тестов должен быть в отчете Allure.

## Проблемы.
Cucumber решил запустить на junit5, но там есть некоторые проблемы. Вчастности с раннером,
я пока не разобрался как запускать раннер для тестов cucumber.