# Иерархия классов на Delphi

## Общее

Утилита помогает создать иерархию классов на Delphi.

Утилита парсит необходимые области в зависимости от указанного параметра.

Возможные параметры области:

1. Ни одна
2. Interfaces
3. Implementation
4. Обе

## Сборка

```
> gradlew build
```

## Очистка

Для очистки папки `umls` используйте скрипт `clean.bat`:

```bat
> clean.bat
```

## Запуск

Для запуска необходимо передать следующие параметры:

1. Папка с файлами ввода
2. Папка для вывода
3. Тип области включения
4. Печатать ли все узлы или только корневые
4. Префикс классов
5. Имя модуля

Папка с файлами ввода:

* Относительный путь от папки `app`

Папка для вывода:

* *Папку для вывода желательно указать: umls*

Типы области включения:

1. none
2. iface
3. impl
4. both

Тип узлов:

1. root
2. all

Префикс классов:

* \- (минус) для отмены префикса


```bat
> gradlew run --args="<path_to_delphi_source> <path_to_output> <include_type> <root> <prefix> <module_name>"
```

Вызов без параметров выдаст подсказку:

```bat
java -jar app.jar <sources> <out> uses [-|prefix] [unit]
  <sources> путь к файлам Delphi
  <out>     путь к папке вывода
  uses      none|iface|impl|both
  root      root|all
  prefix    опциональный префикс модулей
  unit      опциональное имя модуля
```

Примеры:

```bat
> gradlew run --args="..\..\cef4delphi\source umls iface all - uCEFRequestHandler"
```

```bat
> gradlew run --args="..\..\cef4delphi\source umls iface all uCEF"
```