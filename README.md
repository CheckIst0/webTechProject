# webTechProject
# Эндпоинты для CategoryController (/api/category)
1. /getAll - Получение списка всех категорий
2. /{id} - Получение категории по ID
3. /add - Добавление новой категории
4. /{id}/update - Полное обновление(изменение) категории
5. /{id}/delete - Удаление категории
6. /deleteAll - Удаление всех категорий

# Эндпоинты для MealController (/api/meal)
1. /addMeals - Добавление заказанных клиентом блюд

# Эндпоинты для MenuController (/api/menu)
1. /add - Добавление новой позиции в меню
2. /{id} - Получение позиции меню по ID записи в БД
3. /getByCategory/{categoryId} - Получение списка позиций меню с заданной категорией
4. /{id}/update - Полное обновление(изменение) позиции меню с указанным ID
5. /{id}/delete - Удаление позиции меню с указанным ID
6. /deleteAll - Удаление всех позиций в меню

# Эндпоинты для OrderController (/api/order)
1. /add - Добавление одного заказа
2. /{id} - Получение заказа по ID записи
3. /getAll - Получение всех заказов
4. /{id}/changeStatus - Изменение статуса заказа с указанным ID
5. /{id}/payment/success - Имитация успешной оплаты заказа
6. /{id}/payment/error - Имитация провальной оплаты заказа

# Эндпоинты для PaymentController (/api/payment)
1. /payForOrder/{id} - Оплата указанного заказа

# Эндпоинты для TableController (/api/table)
1. /getAll - Получение списка всех столов
2. /{id}/takeATable - Изменение статуса стола с указанным ID на "занято"
3. /{id}/freeTable - Изменение статуса стола с указанным ID на "свободно"
4. /add - Добавление столика
5. /{id}/delete - Удаление столика по ID
