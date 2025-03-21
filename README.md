## Проект по автоматизации тестирования (Web UI, Backend, Mobile UI)
<a name="Ссылка"></a>

## :maple_leaf: Содержание:


- Технологии и инструменты
- Web UI
  - Список проверок, реализованных в тестах
  - Подходы к автоматизации
  - Окружение
  - Allure-отчет
  - Видео прохождения тестов

## :maple_leaf: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://developer.android.com/"><img src="images/logo/Android Studio.svg" width="50" height="50"  alt="Android Studio"/></a>
<a href="https://appium.io/"><img src="images/logo/Appium.svg" width="50" height="50"  alt="Appium"/></a>
<a href="https://appium.github.io/appium-inspector/2024.12/"><img src="images/logo/appiuninsp1.png" width="50" height="50"  alt="Appium Inspector"/></a>
<a href="https://www.vysor.io/"><img src="images/logo/vysor.png" width="50" height="50"  alt="Vysor"/></a>
<a href="https://ubuntu.com/"><img src="images/logo/Ubuntu1.png" width="50" height="50"  alt="Ubuntu"/></a>
<a href="https://www.docker.com/"><img src="images/logo/docker-mark-blue.png" width="50" height="50"  alt="Docker"/></a>    
</p>

`Java` - язык программирования. \
`Gradle` - используется как инструмент автоматизации сборки.  \
`JUnit5` - для выполнения тестов.\
`REST Assured` - для тестирования REST-API сервисов.\
`Jenkins` - CI/CD для запуска тестов удаленно.\
`Selenoid` - для удаленного запуска браузера в `Docker` контейнерах.\
`Android Studio tools`, `Appium`, `Appium Inspector`, `Vysor` - для запуска мобильных тестов локально на эмуляторе мобильных устройств.\
`Allure Report` - для визуализации результатов тестирования.\
`Ubuntu` - физический сервер для запуска автотестов.\
`Docker` - для запуска контейнеров на сервере.


# :maple_leaf: WEB UI

## :maple_leaf: Список проверок, реализованных в автотестах web

- [x] Авторизация. Проверка на пустой номер
- [x] Header. Изменения валюты. Переходы по вкладкам. 
- [x] Поиск. По тексу, по фото, неккоректный поиск
- [x] Карточка товара. Переход в отзывы. Переход в характеристики.
- [x] Корзина. Уменьшение количества товара, увеличение количества товара, переход на главную, удаление товара
- [x] Работа с файлами. Загрузка и скачивание 
- [x] Регистрация. Заполнение формы

## :maple_leaf: Подходы к автоматизации

- [x] Лямбда-степы. Использование лямбда-выражений для создания тестовых шагов позволило создать более чистый и понятный код. 
- [x] Генерация тестовых данных с помощью Java Faker. Для заполнения формы регистрации использовалась библиотека Java Faker, генерирующая реалистичные тестовые данные.
- [x] Кроссбраузерное тестирование с @ParameterizedTest. Использование аннотации @ParameterizedTest позволило автоматизировать тестирование на различных браузерах. Каждый тест прогоняется на последних версиях chrome, firefox и safari.
- [x] Работа с файлами. Реализована функциональность работы с файлами. Загрузка, скачивание и проверка корректности.

## :maple_leaf: Окружение
- [x] GitHub, Jenkins, сервер на Ubuntu, Docker, Selenoid
- [x] Браузер Safari был также залит в Selenoid, что удобнее и дешевле тестов на маках

## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в  Allure report</a>

###  Главное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure1.jpg">
</p>

###  Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/allure2.jpg">
</p>

## <img src="images/logo/Selenoid.svg" width="25" height="25"  alt="Selenoid"/></a> Видео прохождения тестов
<p align="center">
<img src="/images/video/form.gif" alt="video"/></a>
</p>

[Вернуться к оглавлению ⬆](#Ссылка)
