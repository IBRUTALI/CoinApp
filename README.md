## План работ:  
### 1. Анализ и планирование (1-2 часа).   
 - Определить требования и функционал.  
 - Спроектировать архитектуру приложения.  
 - Изучить документацию API.
 - Проанализировать порядок действий и времязатраты.
### 2. Настройка проекта (0,5-1 часа).  
  - Создать проект в Android Studio. 
  - Настроить необходимые зависимости (например, Retrofit для работы с API, Navigation Component, Dagger2).  
  - Настроить файлы конфигурации (Gradle, манифест и т.д.).
### 3. Проектирование слоев (0,1-0,3 часа).
  - Разбить проект на слои (Data, Domain, Presentation, DI). 
  - Создать основные пакеты в Data, Domain, Presentation слоях. 
### 4. Создание модели данных (0,5-1 часа).  
  - Разработать утилитарный класс для определия возможных состояний модели данных (загрузка, ошибка, успех).
  - Определить структуру данных для криптовалют (например, класс Cryptocurrency).
  - Настроить модель для работы с API.
  - Разработать структуру для преобразования (маппинга) модели DTO в бизнес-модель.
### 5. Создание бизнес-логики для работы с моделями данных (0,1-0,2 часа).
  - Определить основные методы бизнес-логики в интерфейсе (репозиторий).
  - Реализовать бизнес-логику репозитория в его имплементации.
  - Проектирование UseCase'ов.
### 6. Разработка классов для внедрения зависимостей (0,2-0,4 часа).
  - Разработка модулей для внедрения AppComponent и контекста приложения.
  - Разработка модуля для внедрения репозиториев.
  - Разработка модуля для внедрения UseCase'ов.
  - Разработка модуля для внедрения Retrofit.
### 7. Реализация пользовательского интерфейса (UI).
  #### 7.1. Разработать вспомогательный класс для упрощения работы с фрагментами, от которого будут наследоваться остальные фрагменты (0,1 часа).
  #### 7.2. Добавление стилей для кнопок (активных, неактивных) и текста, добавление цветов, строковых и графических ресурсов (0,5-1 часа).
  #### 7.3. Экран списка криптовалют (1-2 часа).
  - Спроектировать ViewModel для работы с UseCase'ами
  - Создать XML макет для экрана списка криптовалют.
  - Добавить в макет Chips, RecyclerView, ProgressBar, группу для отображения видимости некоторых view в зависимости от результата сервера.
  - Создать XML макет для элементов списка.
  - Реализовать адаптер для списка криптовалют.
  - Настроить Glide для отображения изображения элементов списка.
  - Создать DiffUtil для правильного обновления списка.
  #### 7.4. Экран с деталями криптовалюты (0,5-1 часа).
  - Спроектировать ViewModel для работы с UseCase'ами
  - Создать XML макет для экрана деталей.
  - Добавить ProgressBar, который будет отображатся, когда данные грузятся.
  - Отобразить детальную информацию (иконка, описание, категории).
  #### 7.5. Реализовать навигацию между фрагментами.
### 8. Доработка DI-слоя (0,1-0,2 часа).
  - Создать ViewModelFactory.
  - Добавить различные расширения для работы с AppComponent от Dagger2 и ViewModel.
  - Прописать inject'ы активити и фрагментов, а также прописать все существующие ViewModels для корректной работы ViewModelFactory.
  - Создать класс Application для инициализации DaggerAppComponent.
### 9*. Доп задание добавить Pull to Refresh в экран списка криптовалют (0,1 часа).
  - Добавить на экран snackbar, появляющийся в случае ошибки сети.
### 10. Тестирование и отладка (1-2 часа).
  - Провести тестирование UI и функциональности.
  - Проверить работу на различных устройствах и версиях Android.
  - Исправить ошибки и оптимизировать приложение.
### 11. Финализация (1-2 часа).
  - Провести финальное тестирование.
  - Подготовить приложение к релизу (скинуть ссылку HR).

Итоговое время займет 6,7-13,3 часов.

Результат:    
<p align="center">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/5943f328-67b6-4076-9be5-459e105b52fa">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/292160a9-741a-475a-96e8-c610869fe392">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/ddf101ef-75fe-4843-88f0-8c036f16abcc">
</p>
<p align="center">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/fc52a643-5154-4439-bfd2-33e5b51e4c43">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/441a9cdc-d2e2-40ac-9847-2561d1a67684">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/1a2b6c07-da17-42ab-b77a-4e41c410372f">
</p>
<p align="center">
  <img align="center" width="30%" src="https://github.com/user-attachments/assets/922ca2a6-4d61-4608-a668-5b352a89201b">
</p>
