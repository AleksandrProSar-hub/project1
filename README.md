## Проект по автоматизации тестирования (Web UI, Mobile UI, Backend)
<a name="Ссылка"></a>

## :maple_leaf: Содержание:


* <b><a href="#tools">Технологии и инструменты</a></b>
* <b><a href="#web">Web UI</a></b>
* <a href="#casesweb">- Список проверок, реализованных в тестах</a>
* <a href="#webavto">- Подходы к автоматизации</a>
* <a href="#environment">- Окружение</a>
* <a href="#allweb">- Allure-отчет</a>
* <a href="#videoweb">- Видео прохождения тестов</a>
* <b><a href="#mob">Mobile UI</a></b>
* <a href="#casesmob">  - Список проверок, реализованных в тестах</a>
* <a href="#mobavto">  - Подходы к автоматизации</a>
* <a href="#allmob">  - Allure-отчет</a>
* <a href="#videomob">  - Видео прохождения тестов</a>
* <b><a href="#back">Backend</a></b>
* <a href="#casesback">  - Список проверок, реализованных в тестах</a>
* <a href="#backavto">  - Подходы к автоматизации</a>
* <a href="#allback">  - Allure-отчет</a>
    
<a id="tools"></a>
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
<a href="https://www.browserstack.com/"><img src="images/logo/browserstack.svg" width="50" height="50"  alt="Browserstack"/></a> 
</p>

`Java` - язык программирования. \
`Gradle` - используется как инструмент автоматизации сборки.  \
`JUnit5` - для выполнения тестов.\
`REST Assured` - для тестирования REST-API сервисов.\
`Jenkins` - CI/CD для запуска тестов удаленно.\
`Selenoid` - для удаленного запуска браузера в `Docker` контейнерах.\
`Android Studio tools`, `Appium`, `Appium Inspector`, `Vysor` - для запуска мобильных тестов локально на эмуляторе мобильных устройств.\
`Allure Report` - для визуализации результатов тестирования.\
`Browserstack` - запуск мобильных автотестов.\
`Ubuntu` - физический сервер для запуска автотестов.\
`Docker` - для запуска контейнеров на сервере.

<a id="web"></a>
# :maple_leaf: WEB UI
## :maple_leaf: Сайт для основных тестов
<p align="center">
<img title="Wildberries" src="images/logo/wildberries1.svg">
</p>

## :maple_leaf: Сайт для теста на заполнение формы регистрации
<p align="center">
<img title="Toolsqa" src="images/logo/toolsqa.png">
</p>

<a id="casesweb"></a>
## :maple_leaf: Список проверок, реализованных в автотестах web

- [x] Авторизация. Проверка на пустой номер
- [x] Header. Изменения валюты. Переходы по вкладкам. 
- [x] Поиск. По тексу, по фото, неккоректный поиск
- [x] Карточка товара. Переход в отзывы. Переход в характеристики.
- [x] Корзина. Уменьшение количества товара, увеличение количества товара, переход на главную, удаление товара
- [x] Работа с файлами. Загрузка и скачивание 
- [x] Регистрация. Заполнение формы

<a id="webavto"></a>
## :maple_leaf: Подходы к автоматизации

- [x] Лямбда-степы. Использование лямбда-выражений для создания тестовых шагов позволило создать более чистый и понятный код. 
- [x] Генерация тестовых данных с помощью Java Faker. Для заполнения формы регистрации использовалась библиотека Java Faker, генерирующая реалистичные тестовые данные.
- [x] Кроссбраузерное тестирование с @ParameterizedTest. Использование аннотации @ParameterizedTest позволило автоматизировать тестирование на различных браузерах. Каждый тест прогоняется на последних версиях chrome, firefox и safari.
- [x] Работа с файлами. Реализована функциональность работы с файлами. Загрузка, скачивание и проверка корректности.

<a id="environment"></a>
## :maple_leaf: Окружение
- [x] GitHub, Jenkins, сервер на Ubuntu, Docker, Selenoid
- [x] Браузер Safari был также залит в Selenoid, что удобнее и дешевле тестов на маках

<a id="allweb"></a>
## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в  Allure report</a>

###  Главное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure1.jpg">
</p>

###  Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/allure2.jpg">
</p>

<a id="videoweb"></a>
## <img src="images/logo/Selenoid.svg" width="25" height="25"  alt="Selenoid"/></a> Видео прохождения тестов
<p align="center">
<img src="/images/video/form.gif" alt="video"/></a>
</p>

<a id="mob"></a>
# :maple_leaf: MOBILE UI
## :maple_leaf: Приложение
<p align="center">
<img title="Wikipedia" src="images/logo/wiki.png">
</p>

<a id="casesmob"></a>
## :maple_leaf: Список проверок, реализованных в автотестах mobile

- [x] Поиск
- [x] Переход в Сохраненные
- [x] Переход в Найти
- [x] Переход в Правки
- [x] Переход в Ещё
- [x] Добавление статьи в список по умолчанию
- [x] Создание нового списка
- [x] Удаление списка
- [x] Изменение названия и описания списка
- [x] Добавление статьи в пользовательский список

<a id="mobavto"></a>
## :maple_leaf: Подходы к автоматизации

- [x] Степы. Использование аннотации step в page object. 
- [x] Browserstack. Тесты запускаются в облачной платформе с возможностью выбора мобильного устройства.

<a id="allmob"></a>
## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в  Allure report</a>
###  Главное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure1mob.jpg">
</p>

###  Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/allure2mob.jpg">
</p>

###  Browserstack

<p align="center">
<img title="Browserstack" src="images/screens/browserstack.jpg">
</p>

<a id="videomob"></a>
## <img src="images/logo/Selenoid.svg" width="25" height="25"  alt="Selenoid"/></a> Видео прохождения мобильных тестов
<p align="center">
<img src="/images/video/wiki.gif" alt="video"/></a>
</p>

<a id="back"></a>
# :maple_leaf: BACKEND
## :maple_leaf: Сайт
<p align="center">
<img title="Reqres" src="images/logo/reqres.png">
</p>

<a id="caseback"></a>
## :maple_leaf: Список проверок, реализованных в автотестах backend

- [x] Успешная регистрация
- [x] Неуспешная регистрация. Не указываем пароль
- [x] Успешный вход
- [x] Неуспешный вход
- [x] Получение данных со страницы пользователей №2
- [x] Запрашиваем данные по пользователю №2
- [x] Запрашиваем данные по несуществующему пользователю № 23
- [x] Обновляем пользователя №2

<a id="backavto"></a>
## :maple_leaf: Подходы к автоматизации

- [x] Lombok. Bспользуется для сокращения количества шаблонного кода.

<a id="allback"></a>
## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Отчет в  Allure report</a>
###  Главное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure1back.jpg">
</p>

###  Тесты

<p align="center">
<img title="Allure Tests" src="images/screens/allure2back.jpg">
</p>


[Вернуться к оглавлению ⬆](#Ссылка)
